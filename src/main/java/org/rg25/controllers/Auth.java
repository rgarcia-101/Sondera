package org.rg25.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rg25.auth.*;
import org.rg25.entity.User;
import org.rg25.persistance.GenericDao;
import org.rg25.util.PropertiesLoader;
import org.apache.commons.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;


@WebServlet(
        urlPatterns = {"/auth"}
)
/**
 * Inspired by: https://stackoverflow.com/questions/52144721/how-to-get-access-token-using-client-credentials-using-java-code
 */

public class Auth extends HttpServlet implements PropertiesLoader {
    Properties properties;
    String CLIENT_ID;
    String CLIENT_SECRET;
    String OAUTH_URL;
    String LOGIN_URL;
    String REDIRECT_URL;
    String REGION;
    String POOL_ID;
    Keys jwks;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        super.init();
        loadProperties();
        loadKey();
    }

    /**
     * Gets the auth code from the request and exchanges it for a token containing user info.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authCode = req.getParameter("code");
        String userName = null;

        if (authCode == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);

        } else {
            HttpRequest authRequest = buildAuthRequest(authCode);
            try {
                TokenResponse tokenResponse = getToken(authRequest);
                User user = validate(tokenResponse);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            } catch (IOException e) {
                logger.error("Error getting or validating the token: " + e.getMessage(), e);
            } catch (InterruptedException e) {
                logger.error("Error getting token from Cognito oauth url " + e.getMessage(), e);
            }
        }
        resp.sendRedirect("home");
    }

    /**
     * Sends the request for a token to Cognito and maps the response
     * @param authRequest the request to the oauth2/token url in cognito
     * @return response from the oauth2/token endpoint which should include id token, access token and refresh token
     * @throws IOException
     * @throws InterruptedException
     */
    private TokenResponse getToken(HttpRequest authRequest) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<?> response = null;

        response = client.send(authRequest, HttpResponse.BodyHandlers.ofString());


        logger.debug("Response headers: " + response.headers().toString());
        logger.debug("Response body: " + response.body().toString());

        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(response.body().toString(), TokenResponse.class);
        logger.debug("Id token: " + tokenResponse.getIdToken());

        return tokenResponse;

    }

    /**
     * Get values out of the header to verify the token is legit. If it is legit, get the claims from it, such
     * as username.
     * @param tokenResponse
     * @return
     * @throws IOException
     */
    private User validate(TokenResponse tokenResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CognitoTokenHeader tokenHeader = mapper.readValue(CognitoJWTParser.getHeader(tokenResponse.getIdToken()).toString(), CognitoTokenHeader.class);

        // Header should have kid and alg- https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-the-id-token.html
        String keyId = tokenHeader.getKid();
        String alg = tokenHeader.getAlg();

        // todo pick proper keys
        // Use Key's N and E
        BigInteger modulus = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(jwks.getKeys().get(0).getN()));
        BigInteger exponent = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(jwks.getKeys().get(0).getE()));

        // TODO consider exceptions
        // Create a public key
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException e) {
            logger.error("Invalid Key Error " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm Error " + e.getMessage(), e);
        }

        // get an algorithm instance
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);

        // Verify ISS field of the token to make sure it's from the Cognito source
        String iss = String.format("https://cognito-idp.%s.amazonaws.com/%s", REGION, POOL_ID);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(iss)
                .withClaim("token_use", "id") // make sure you're verifying id token
                .build();

        // Verify the token
        DecodedJWT jwt = verifier.verify(tokenResponse.getIdToken());

        logger.debug("available claims: " + jwt.getClaims());

        GenericDao<User> userDao = new GenericDao<>(User.class);
        // TODO handle info
        User user;
        User testUser = new User();
        testUser.setUsername(jwt.getClaim("cognito:username").asString());
        testUser.setFirstName(jwt.getClaim("given_name").asString());
        testUser.setLastName(jwt.getClaim("family_name").asString());
        testUser.setEmail(jwt.getClaim("email").asString());

        // Is in the database
        if (!userDao.getByProperty("username", testUser.getUsername()).isEmpty()) {
            user = userDao.getByProperty("username", testUser.getUsername()).get(0);
            testUser.setId(user.getId());
            if (user.getZone() != null) testUser.setZone(user.getZone());
            else testUser.setZone("US/Central");
            logger.info("current test user: " + testUser);

            // Checking if user data matches
            if (!user.equals(testUser)) {
                logger.debug("issue with user info, updating info");
                user.setEmail(testUser.getEmail());
                user.setUsername(testUser.getUsername());
                user.setFirstName(testUser.getFirstName());
                user.setLastName(testUser.getLastName());
                userDao.update(user);
                testUser.setId(userDao.insert(testUser));
            }
        }
        // Not in the database
        else {
            logger.debug("must insert testuser: " + testUser);
            testUser.setId(userDao.insert(testUser));
        }
        user = testUser;
        return user;
    }

    /** Create the auth url and use it to build the request.
     *
     * @param authCode auth code received from Cognito as part of the login process
     * @return the constructed oauth request
     */
    private HttpRequest buildAuthRequest(String authCode) {
        String keys = CLIENT_ID + ":" + CLIENT_SECRET;

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("client-secret", CLIENT_SECRET);
        parameters.put("client_id", CLIENT_ID);
        parameters.put("code", authCode);
        parameters.put("redirect_uri", REDIRECT_URL);

        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(OAUTH_URL))
                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic " + encoding)
                .POST(HttpRequest.BodyPublishers.ofString(form)).build();
        return request;
    }

    /**
     * Gets the JSON Web Key Set (JWKS) for the user pool from cognito and loads it
     * into objects for easier use.
     *
     * JSON Web Key Set (JWKS) location: https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json
     * Demo url: https://cognito-idp.us-east-2.amazonaws.com/us-east-2_XaRYHsmKB/.well-known/jwks.json
     *
     * @see Keys
     * @see KeysItem
     */
    private void loadKey() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jwksURL = new URL(String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", REGION, POOL_ID));
            File jwksFile = new File("jwks.json");
            FileUtils.copyURLToFile(jwksURL, jwksFile);
            jwks = mapper.readValue(jwksFile, Keys.class);
            logger.debug("Keys are loaded. Here's e: " + jwks.getKeys().get(0).getE());
        } catch (IOException ioException) {
            logger.error("Cannot load json..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading json" + e.getMessage(), e);
        }
    }

    /**
     * Read in the cognito props file and get/set the client id, secret, and required urls
     * for authenticating a user.
     */
    private void loadProperties() {
        try {
            properties = loadProperties("/cognito.properties");
            CLIENT_ID = properties.getProperty("client.id");
            CLIENT_SECRET = properties.getProperty("client.secret");
            OAUTH_URL = properties.getProperty("oauthURL");
            LOGIN_URL = properties.getProperty("loginURL");
            REDIRECT_URL = properties.getProperty("redirectURL");
            REGION = properties.getProperty("region");
            POOL_ID = properties.getProperty("poolId");
        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }
    }
}

