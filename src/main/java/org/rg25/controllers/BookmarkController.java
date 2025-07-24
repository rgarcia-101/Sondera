package org.rg25.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.rg25.entity.Bookmark;
import org.rg25.entity.User;
import org.rg25.util.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import org.rg25.persistance.GenericDao;

/**
 * Controller for bookmark editing
 */
@WebServlet(
        urlPatterns = {"/bookmarkEditor"}
)
public class BookmarkController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Bookmark> bookmarkDao = new GenericDao<>(Bookmark.class);
    private ServletUtil util = new ServletUtil();

    /**
     * Handles get requests
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO condense?
        String bookmarkId = req.getParameter("id");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");


        if (!util.canAcceptRequest(user, bookmarkId, req, resp, getServletContext())) return;
        Bookmark bookmark = bookmarkDao.getById(Integer.parseInt(bookmarkId));
        if (bookmark == null) {
            req.setAttribute("errReason", "null");
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }

        if (!user.equals(bookmark.getUser())) {
            logger.debug("User does not match!");
            req.setAttribute("errReason", "wrongUser");
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }

        // Format the zone to match the user's
        util.formatZone(user, bookmark);

        logger.info("Sending to bookmark editor...");
        String url = "/bookmarkEditor.jsp";
        req.getSession().setAttribute("object", bookmark);
        req.getSession().setAttribute("callback", "bookmarks");
        session.setAttribute("title", "Edit Bookmark");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);
    }


    // TODO validate before saving

    /**
     * Handles put requests, updates a record
     * @param req   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            logger.error("No user detected!");
            throw new ServletException();
        }

        buffer = util.readRequest(req);

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        try {
            JsonObject content = JsonParser.parseString(buffer.toString()).getAsJsonObject();
            logger.info("incoming json: " + content.toString());

            String description = content.get("description").getAsString();
            int id = content.get("id").getAsInt();
            String title = content.get("bookmarkTitle").getAsString();
            String link = content.get("link").getAsString();
            Bookmark bookmark = bookmarkDao.getById(id);
            if (bookmark.getUrl().equals(link) && bookmark.getTitle().equals(title) &&
                    bookmark.getDescription().equals(description)) {
                util.approveEquivalentJson(writer);
                return;
            }

            //TODO improve url verification
            if (!link.contains("http://") && !link.contains("https://")) {
                link = "https://" + link;
            }


            if (user.getId() == bookmark.getUser().getId()) {
                logger.info("Attempting to update..." + bookmark);
                bookmark.setTitle(title);
                bookmark.setDescription(description);
                bookmark.setUrl(link);
                bookmark.setUpdated(util.getDateTime());
                bookmarkDao.update(bookmark);
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("responseCode", "0");
                writer.print(jsonResponse.toString());
                writer.flush();
            }
        } catch (JSONException e) {
            util.denyJson(writer);
            logger.error("Could not parse JSON! " + e);
        } catch (Exception e) {
            util.denyJson(writer);
            logger.error("Something went wrong saving a note! " + e);
        }
    }
}
