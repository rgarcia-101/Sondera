package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rg25.entity.Note;
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
import java.util.List;

@WebServlet (
        urlPatterns = ("/notes")
)
public class NoteHomeController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<User> userDao = new GenericDao<>(User.class);
    private ServletUtil util = new ServletUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        //TODO code appears several times, condense

        // No user, default to error page
        if (!util.canAcceptRequest(user, req, resp, getServletContext())) return;
//        if (session.getAttribute("user") == null) {
//            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
//            dispatch.forward(req, resp);
//            return;
//        }

        //FIXME only works if I do this??
        user = userDao.getById(user.getId());


        List<Note> noteList = user.getNotes();
        String url = "/notesHome.jsp";
        req.setAttribute("notes", noteList);
        req.setAttribute("title", "Notes");
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);

    }
}
