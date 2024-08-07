package org.rg25.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.rg25.entity.Bookmark;
import org.rg25.entity.Note;
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
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(
        urlPatterns = {"/todoEditor"}
)
public class TodoController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Todo> todoDao = new GenericDao<>(Todo.class);
    private ServletUtil util = new ServletUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todoId = req.getParameter("id");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (todoId == null || !todoId.matches("\\d+") || user == null) {
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        Todo todo = todoDao.getById(Integer.parseInt(todoId));
        if (!user.equals(todo.getUser())) {
            logger.debug("User does not match!");
            logger.debug(user);
            logger.debug(todo.getUser());
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }

        String url = "/todoEditor.jsp";
        req.getSession().setAttribute("object", todo);
        req.getSession().setAttribute("callback", "todos");
        req.setAttribute("title", "Edit Todo");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);

    }



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
            String title = content.get("todoTitle").getAsString();
            String dueDate = content.get("dueDate").getAsString();
//            dueDate += " 00:00:00";
            Todo todo = todoDao.getById(id);
            if (todo.getContent().equals(description) && todo.getTitle().equals(title) &&
                    todo.getDueDate().equals(dueDate)) {
                util.approveEquivalentJson(writer);
                return;
            }



            if (user.getId() == todo.getUser().getId()) {
                logger.info("Attempting to update..." + todo);
                todo.setTitle(title);
                todo.setContent(description);
                todo.setDueDate(dueDate);
                todo.setUpdated(util.getDateTime());
                todoDao.update(todo);
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
