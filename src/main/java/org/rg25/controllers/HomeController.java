package org.rg25.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rg25.entity.*;
import org.rg25.persistance.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Controller for dashboard
 */
@WebServlet(
        urlPatterns = {"/home"}
)
public class HomeController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<Note> noteDao = new GenericDao<>(Note.class);
    GenericDao<Todo> todoDao = new GenericDao<>(Todo.class);
    GenericDao<Date> dateDao = new GenericDao<>(Date.class);
    GenericDao<Bookmark> bookmarkDao = new GenericDao<>(Bookmark.class);


    /**
     * Handles get requests, displays the dashboard
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

        // No user, default to timeout page
        if (session.getAttribute("user") == null) {
            logger.debug("user is null, redirecting to home page");
            resp.sendRedirect(req.getContextPath());
            return;
        }
        User user = (User) session.getAttribute("user");


        // TODO find out why deleting this (and on the other controllers) breaks everything??
        session.setAttribute("object", null);
        session.setAttribute("callback", null);

        List<Note> noteList = noteDao.getByPropertySorted("user", user, "updated",0, false);
        List<Bookmark> bookmarkList = bookmarkDao.getByPropertySorted("user", user, "updated",0, false);
        List<Todo> todoList = todoDao.getByPropertySorted("user", user, "dueDate",0, true);
        //TODO add dates back in if or when they get displayed on the dashboard
//        List<Date> dateList = dateDao.getByPropertySorted("user", user, "updated",0, false);



        req.setAttribute("title", "Dashboard");
        req.setAttribute("notes", noteList);
        req.setAttribute("bookmarks", bookmarkList);
        req.setAttribute("todos", todoList);
//        req.setAttribute("dates", dateList);
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/dashboard.jsp");
        dispatch.forward(req, resp);
    }
    }
