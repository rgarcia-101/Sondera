package org.rg25.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.rg25.entity.Date;
import org.rg25.entity.User;
import org.rg25.persistance.GenericDao;
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

/**
 * Controller for date editor
 */
@WebServlet(
        urlPatterns = {"/dateEditor"}
)
public class DatesController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Date> dateDao = new GenericDao<>(Date.class);
    private ServletUtil util = new ServletUtil();

    /**
     * Handles get requests, shows date home
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateId = req.getParameter("id");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");


        if (!util.canAcceptRequest(user, dateId, req, resp, getServletContext())) return;
        Date date = dateDao.getById(Integer.parseInt(dateId));
        if (date == null) {
            req.setAttribute("errReason", "null");
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        if (!user.equals(date.getUser())) {
            logger.debug("User does not match!");
            req.setAttribute("errReason", "wrongUser");
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }

        String url = "/dateEditor.jsp";
        req.getSession().setAttribute("object", date);
        req.getSession().setAttribute("callback", "dates");
        session.setAttribute("title", "Edit Date");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);
    }

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
     * @throws ServletException
     * @throws IOException
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

            String description = content.get("textContent").getAsString();
            int id = content.get("id").getAsInt();
            String title = content.get("dateTitle").getAsString();
            String setDate = content.get("date").getAsString();
            Date date = dateDao.getById(id);
            if (date.getContent().equals(description) && date.getTitle().equals(title) &&
                    date.getDate().equals(setDate)) {
                util.approveEquivalentJson(writer);
                return;
            }



            if (user.getId() == date.getUser().getId()) {
                logger.info("Attempting to update..." + date);
                date.setTitle(title);
                date.setContent(description);
                date.setDate(setDate);
                date.setUpdated(util.getDateTime());
                dateDao.update(date);
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
            logger.error("Something went wrong saving a date! " + e);
        }
    }
}
