package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.rg25.entity.*;
import org.rg25.persistance.GenericDao;

/**
 * Controller to handle deletes
 */
@WebServlet(
        urlPatterns = {"/delete"}
)
public class DeleteController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles post requests, deletes a record
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException servlet exception
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received Post");
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        // TODO more verification before delete?
        if (user == null) {
            logger.error("No user detected!");
            throw new ServletException();
        }
        // TODO remove this
//        Object object = session.getAttribute("object");
//        String callback = (String) session.getAttribute("callback");
//        if (object == null || callback == null) {
//            logger.error("No object detected!");
//            throw new ServletException();
//        }

        String idReq = req.getParameter("objId");
        String type = req.getParameter("objType");
        String callback = "";
        if (idReq == null || idReq.isEmpty() || type == null || type.isEmpty()) {
            // TODO handle malformed inputs
        }
        int id = Integer.parseInt(idReq);
        logger.info("deleting object");
        GenericDao<DataEntry> dao;
        switch (type) {
            case "note" :
                dao = new GenericDao(Note.class);
                Note note = dao.getById(id);
                logger.debug("getuser is " + note.getUser().getFirstName() + " RAW: " + note.getUser());
                logger.debug("user is " + user.getFirstName() + " RAW: " + user);
                if (!isValidUser(note, user)) {
                    logger.debug("Could not delete!");
                    throw new ServletException();
                }
                callback = "notes";
                dao.delete(note);
                break;
            case "date":
                dao = new GenericDao(Date.class);
                Date date = dao.getById(id);
                if (!isValidUser(date, user)) throw new ServletException();
                callback = "dates";
                dao.delete(date);
                break;
            case "todo":
                dao = new GenericDao(Todo.class);
                Todo todo = dao.getById(id);
                if (!isValidUser(todo, user)) throw new ServletException();
                callback = "todos";
                dao.delete(todo);
                break;
            case "bookmark":
                dao = new GenericDao(Bookmark.class);
                Bookmark bookmark = dao.getById(id);
                if (!isValidUser(bookmark, user)) {
                    logger.debug("Could not delete!");
                    throw new ServletException();
                }
                callback = "bookmarks";
                dao.delete(bookmark);
                break;
            default:
                // TODO handle improper request
                throw new ServletException();
        }

//        dao = new GenericDao<>(object.getClass());

//        dao.delete(object);

        session.setAttribute("object", null);
        session.setAttribute("callback", null);
        logger.info("deleted object");
        resp.sendRedirect(callback);
    }

    private boolean isValidUser(DataEntry entry, User user) {
        return entry.getUser().equals(user);
    }


    // TODO use delete?

    /**
     * Handles delete requests, not currently used
     * @param req   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
    }
}
