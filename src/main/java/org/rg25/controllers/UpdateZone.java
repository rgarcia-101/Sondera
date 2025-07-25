package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Iterator;
import java.util.Set;


@WebServlet(
        urlPatterns = {"/zonechange"}
)
public class UpdateZone extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final Set<String> ZONES = Set.of("US/Eastern","US/Central","US/Pacific"
            ,"US/Alaska","US/Hawaii","US/Mountain");
    private final GenericDao<User> dao = new GenericDao<>(User.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String zone = req.getParameter("zones");
        for (Iterator<String> it = req.getAttributeNames().asIterator(); it.hasNext(); ) {
            String s = it.next();
            logger.debug(s);
        }
        if (user != null) {
            logger.info("Updating zone");
            if (zone == null || !ZONES.contains(zone)) zone = user.getZone();
            if (zone == null) zone = "US/Central";
            // TODO test without using the DAO
            user = dao.getById(user.getId());

            if (!user.getZone().equals(zone)) {
                user.setZone(zone);
                dao.update(user);

                // TODO decide to replace user in sesison, or update existing one
                session.setAttribute("user", user);
            }
            logger.info("Zone updated");
            req.setAttribute("title", "Account");
            resp.sendRedirect("account");
        } else {
            logger.debug("User is null!");
            req.setAttribute("errReason", "noUser");
            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
            dispatch.forward(req, resp);
        }
    }

}
