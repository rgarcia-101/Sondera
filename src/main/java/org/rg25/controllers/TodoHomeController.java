package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rg25.entity.Note;
import org.rg25.entity.Todo;
import org.rg25.entity.User;
import org.rg25.persistance.GenericDao;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(
        urlPatterns = {"/todos"}
)
public class TodoHomeController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private GenericDao<User> userDao = new GenericDao<>(User.class);

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

        user = userDao.getById(user.getId());
        List<Todo> todos = user.getTodos();
        List<List<Todo>> todoList = null;
        try {
            todoList = processDates(todos);
        } catch (Exception e) {
            logger.error("Could not process dates! " + e);
        }


        String url = "/todosHome.jsp";
        req.setAttribute("todos", todoList);
        req.setAttribute("title", "Todo");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);
    }

    private List<List<Todo>> processDates(List<Todo> todos) throws Exception {
        List<Todo> overdue = new ArrayList<>();
        List<Todo> today = new ArrayList<>();
        List<Todo> soon = new ArrayList<>();
        List<Todo> later = new ArrayList<>();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Date tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        Date week = new Date();
        week.setDate(week.getDate() + 7);
        Date date;
        for (Todo todo : todos) {
            date = format.parse(todo.getDueDate());
            if (date.before(now)) {
                logger.info("overdue");
                overdue.add(todo);
            } else if (date.before(tomorrow)) {
                logger.info("tomorrow");
                today.add(todo);
            } else if (date.before(week)) {
                logger.info("week");
                soon.add(todo);
            } else {
                logger.info("month");
                later.add(todo);
            }
        }
        List<List<Todo>> sortedTodo = new ArrayList<>();
        sortedTodo.add(overdue);
        sortedTodo.add(today);
        sortedTodo.add(soon);
        sortedTodo.add(later);

        logger.info(sortedTodo);
        return sortedTodo;

    }

    }
