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

@WebServlet(
        urlPatterns = {"/home"}
)
public class HomeController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<Note> noteDao = new GenericDao<>(Note.class);
    GenericDao<Todo> todoDao = new GenericDao<>(Todo.class);
    GenericDao<Date> dateDao = new GenericDao<>(Date.class);
    GenericDao<Bookmark> bookmarkDao = new GenericDao<>(Bookmark.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // No user, default to timeout page
        if (session.getAttribute("user") == null) {
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        User user = (User) session.getAttribute("user");
        session.setAttribute("object", null);
        session.setAttribute("callback", null);

        List<Note> noteList = noteDao.getByPropertyUpTo("user", user,5);
        List<Bookmark> bookmarkList = bookmarkDao.getByPropertyUpTo("user", user,5);
        List<Todo> todoList = todoDao.getByPropertyUpTo("user", user,5);
        List<Date> dateList = dateDao.getByPropertyUpTo("user", user,5);



        req.setAttribute("title", "Dashboard");
        req.setAttribute("notes", noteList);
        req.setAttribute("bookmarks", bookmarkList);
        req.setAttribute("todos", todoList);
        req.setAttribute("dates", dateList);
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/dashboard.jsp");
        dispatch.forward(req, resp);


    }
    }
