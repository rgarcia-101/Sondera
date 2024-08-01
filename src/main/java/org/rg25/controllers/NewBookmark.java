package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rg25.entity.Bookmark;
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
import java.time.LocalDateTime;

/**
 * Controller for making new bookmarks
 */
@WebServlet(
        urlPatterns = {"/newBookmark"}
)
public class NewBookmark extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Bookmark> bookmarkDao = new GenericDao<>(Bookmark.class);

    private ServletUtil util = new ServletUtil();
    /**
     * Handles get requests, will make a new bookmark
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
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // No user, default to error page
        if (session.getAttribute("user") == null) {
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error");
            dispatch.forward(req, resp);
            return;
        }


        Bookmark bookmark = new Bookmark("New Bookmark"," ","",user);

        int id = bookmarkDao.insert(bookmark);

        session.setAttribute("title", "Edit Bookmark");

        resp.sendRedirect("bookmarkEditor?id=" + id);
    }
}
