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
        urlPatterns = {"/delete"}
)
public class deleteController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao dao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received Post");
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
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
}
