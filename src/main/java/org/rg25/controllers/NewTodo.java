package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rg25.entity.Todo;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(
        urlPatterns = {"/newTodo"}
)
public class NewTodo extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Todo> todoDao = new GenericDao<>(Todo.class);
    private ServletUtil util = new ServletUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // No user, default to error page
        if (session.getAttribute("user") == null) {
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        Todo todo = new Todo(user, "New Todo", "", util.getDateTime(),false);

        int id = todoDao.insert(todo);

        session.setAttribute("title", "Edit Todo");

        resp.sendRedirect("todoEditor?id=" + id);
    }
}
