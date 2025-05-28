package org.rg25.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rg25.entity.Date;
import org.rg25.entity.Note;
import org.rg25.entity.User;
import org.rg25.persistance.GenericDao;
import org.rg25.util.ServletUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for date home
 */
@WebServlet(
        urlPatterns = {"/dates"}
)
public class DatesHomeController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<User> userDao = new GenericDao<>(User.class);
    private ServletUtil util = new ServletUtil();

    /**
     * Handles get requests, shows date home
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
        User user = (User) session.getAttribute("user");
        //TODO sort dates

        // No user, default to error page
        if (!util.canAcceptRequest(user, req, resp, getServletContext())) return;
//        if (session.getAttribute("user") == null) {
//            RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/error.jsp");
//            req.setAttribute("rg25.reason", "noUser");
//            dispatch.forward(req, resp);
//            return;
//        }
        String year = req.getParameter("year");
        if (year == null) {
            year = util.getYear();
        }
        if (Integer.parseInt(year) > 1900) {
            year = String.valueOf((Integer.parseInt(year) - 1900));
        }



        //FIXME
        user = userDao.getById(user.getId());

        List<Date> dateList = user.getDates();

        List<List<Date>> filteredDates = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            filteredDates.add(new ArrayList<>());
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : dateList) {
            try {
                java.util.Date currentDate = format.parse(date.getDate());
                if (String.valueOf(currentDate.getYear()).equals(year)) {
                    switch (currentDate.getMonth()) {
                        case Calendar.JANUARY:
                            filteredDates.get(0).add(date);
                            break;
                        case Calendar.FEBRUARY:
                            filteredDates.get(1).add(date);
                            break;
                        case Calendar.MARCH:
                            filteredDates.get(2).add(date);
                            break;
                        case Calendar.APRIL:
                            filteredDates.get(3).add(date);
                            break;
                        case Calendar.MAY:
                            filteredDates.get(4).add(date);
                            break;
                        case Calendar.JUNE:
                            filteredDates.get(5).add(date);
                            break;
                        case Calendar.JULY:
                            filteredDates.get(6).add(date);
                            break;
                        case Calendar.AUGUST:
                            filteredDates.get(7).add(date);
                            break;
                        case Calendar.SEPTEMBER:
                            filteredDates.get(8).add(date);
                            break;
                        case Calendar.OCTOBER:
                            filteredDates.get(9).add(date);
                            break;
                        case Calendar.NOVEMBER:
                            filteredDates.get(10).add(date);
                            break;
                        case Calendar.DECEMBER:
                            filteredDates.get(11).add(date);
                            break;
                    }
                }
            } catch (ParseException e) {
                logger.error("Could not parse dates! " + e);
            }

        }

        String url = "/datesHome.jsp";
        req.setAttribute("title", "Dates");
        req.setAttribute("dates", filteredDates);
        req.setAttribute("calendarYear", (Integer.parseInt(year)+1900));
        RequestDispatcher dispatch = getServletContext().getRequestDispatcher(url);
        dispatch.forward(req, resp);
    }
    }
