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

import org.rg25.entity.User;
import org.rg25.persistance.GenericDao;

/**
 * Controller to handle deletes
 */
@WebServlet(
        urlPatterns = {"/delete"}
)
public class DeleteController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao dao;

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
        Object object = session.getAttribute("object");
        String callback = (String) session.getAttribute("callback");
        if (object == null || callback == null) {
            logger.error("No object detected!");
            throw new ServletException();
        }

        dao = new GenericDao<>(object.getClass());

        logger.info("deleting object");
        dao.delete(object);

        session.setAttribute("object", null);
        session.setAttribute("object", null);
        logger.info("deleted object");
        resp.sendRedirect(callback);
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
