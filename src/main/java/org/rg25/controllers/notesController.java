package org.rg25.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;
import org.rg25.entity.Note;
import org.rg25.persistance.GenericDao;
import org.rg25.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/notes"}
)
public class notesController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Note> noteDao = new GenericDao<>(Note.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String noteId = req.getParameter("id");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (noteId == null || !noteId.matches("\\d+") || user == null) {
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        GenericDao<Note> notesDao = new GenericDao<>(Note.class);
        Note note = notesDao.getById(Integer.parseInt(noteId));
        if (!user.equals(note.getUser())) {
            logger.debug("User does not match!");
            logger.debug(user);
            logger.debug(note.getUser());
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        String url = "/notes.jsp";
        req.setAttribute("textContent", note.getContent());
        req.setAttribute("title", "Notes");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO add way to change note title
        logger.debug("Received Post");
        StringBuilder buffer = new StringBuilder();
        String line = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            logger.error("No user detected!");
            throw new ServletException();
        }
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            logger.error("Could not read! " + e);
        }

        try {
            JsonObject content = JsonParser.parseString(buffer.toString()).getAsJsonObject();

            String text = content.get("textContent").getAsString();
            int id = content.get("id").getAsInt();
            Note note = noteDao.getById(id);
            if (note.getContent().equals(text)) {
                logger.info("Text is identical, not saving...");
                return;
            }

            //TODO remove
            logger.info("user ID " + user.getId());
            logger.info("note user ID " + note.getUser().getId());
            if (user.getId() == note.getUser().getId()) {
                logger.info("Attempting to update..." + note);
                note.setContent(text);
                noteDao.update(note);
            }
        } catch (JSONException e) {
            logger.error("Could not parse JSON! " + e);
        } catch (Exception e) {
            logger.error("Something went wrong saving a note! " + e);
        }

    }

}
