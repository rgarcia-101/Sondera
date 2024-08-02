package org.rg25.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;
import org.rg25.entity.Note;
import org.rg25.persistance.GenericDao;
import org.rg25.entity.User;
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

@WebServlet(
        urlPatterns = {"/noteEditor"}
)
public class NotesController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Note> noteDao = new GenericDao<>(Note.class);
    private ServletUtil util = new ServletUtil();

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
        Note note = noteDao.getById(Integer.parseInt(noteId));
        if (!user.equals(note.getUser())) {
            logger.debug("User does not match!");
            logger.debug(user);
            logger.debug(note.getUser());
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
            return;
        }
        String url = "/noteEditor.jsp";
        req.getSession().setAttribute("object", note);
        req.getSession().setAttribute("callback", "notes");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);

    }


    //TODO has been replaced with doPut, decide what to do with this
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    // TODO put date save
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

            String text = content.get("textContent").getAsString();
            int id = content.get("id").getAsInt();
            String title = content.get("noteTitle").getAsString();
            Note note = noteDao.getById(id);
            if (note.getContent().equals(text) && note.getTitle().equals(title)) {
                util.approveEquivalentJson(writer);
                return;
            }


            if (user.getId() == note.getUser().getId()) {
                logger.info("Attempting to update..." + note);
                note.setContent(text);
                note.setTitle(title);
                note.setUpdated(util.getDateTime());
                noteDao.update(note);
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
