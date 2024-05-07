package org.rg25.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apininja.Holiday;
import org.json.JSONException;
import org.rg25.entity.Date;
import org.rg25.entity.User;
import org.rg25.persistance.GenericDao;
import org.rg25.persistance.HolidayMapper;
import org.rg25.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(
        urlPatterns = {"/getHolidays"}
)
public class ImportHolidays extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    HolidayMapper dao = new HolidayMapper();
    GenericDao<Date> dateDao = new GenericDao<>(Date.class);
    ServletUtil util = new ServletUtil();

    /**
     * Handles put requests, will get and insert holidays for the user
     * @param req   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer;
        String line = null;
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

            String year = content.get("year").getAsString();
            ArrayList<Holiday> holidayList = new ArrayList<>(Arrays.asList(dao.getHolidays(Integer.parseInt(year))));

            for (Holiday holiday : holidayList) {
                logger.info("Attempting to insert holiday...");
                Date holidayDate = new Date();
                holidayDate.setTitle(holiday.getName());
                holidayDate.setDate(holiday.getDate());
                holidayDate.setUser(user);
                holidayDate.setContent("");
                dateDao.insert(holidayDate);
            }
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("responseCode", "0");
            writer.print(jsonResponse.toString());
            writer.flush();
        } catch (JSONException e) {
            util.denyJson(writer);
            logger.error("Could not parse JSON! " + e);
        } catch (Exception e) {
            util.denyJson(writer);
            logger.error("Something went wrong saving a date! " + e);
        }

    }

}
