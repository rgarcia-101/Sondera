package org.rg25.util;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.rg25.entity.DataEntry;
import org.rg25.entity.User;

public class ServletUtil {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public StringBuilder readRequest(HttpServletRequest req) {
        String line;
        StringBuilder buffer =  new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            logger.error("Could not read! " + e);
        }


        return buffer;
    }

    /**
     * Processes the JSON for instances where no changes to the database are needed
     * @param writer writer to write JSON to
     */
    public void approveEquivalentJson(PrintWriter writer) {
        logger.info("Text is identical, not saving...");
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("responseCode", "0");
        writer.print(jsonResponse.toString());
        writer.flush();
    }

    /**
     * Gets the current year
     * @return current year
     */
    public String getYear() {
        Date date = new Date();
        return String.valueOf(date.getYear());
    }

    public String toLocalTime(String time) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formattedTime = LocalDateTime.parse(time, format);
        return formattedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
    }

    public String getDateTime() {
        ZonedDateTime time = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("US/Central"));
//        LocalDateTime time = LocalDateTime.now();
//        ZonedDateTime zoneTime = ZonedDateTime.now();
//        zoneTime.format(formatter);
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Updates and formats the timezone of an object to match the zone set by the user
     * @param user Current user
     * @param obj Data entry to be verified
     */
    public void formatZone(User user, DataEntry obj) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter localFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

        LocalDateTime tempTime;
        ZonedDateTime time;

        if (obj.getUpdated() != null && !obj.getUpdated().isEmpty()) {
            tempTime = LocalDateTime.parse(obj.getUpdated(), format);
            time = ZonedDateTime.ofLocal(tempTime, ZoneId.of("US/Central"), null)
                    .withZoneSameInstant(ZoneId.of(user.getZone()));
            obj.setUpdated(time.format(localFormat));
        }
        if (obj.getCreated() != null && !obj.getCreated().isEmpty()) {
            tempTime = LocalDateTime.parse(obj.getCreated(), format);
            time = ZonedDateTime.ofLocal(tempTime, ZoneId.of("US/Central"), null)
                    .withZoneSameInstant(ZoneId.of(user.getZone()));
            obj.setCreated(time.format(localFormat));
        }
    }

    /**
     * Processes the JSON for instances where the request was denied
     * @param writer writer to write JSON to
     */
    public void denyJson(PrintWriter writer) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("responseCode", "1");
        writer.print(jsonResponse.toString());
        writer.flush();
    }


    /**
     * Checks for potential issues before an item can be loaded
     * @param user User making the request
     * @param id id of the requested item, from the query string
     * @param req servlet request
     * @param resp servlet response
     * @param context servlet context
     * @return if request can be accepted
     */
    public boolean canAcceptRequest(User user, String id, HttpServletRequest req, HttpServletResponse resp, ServletContext context) {
        try {
            if (user == null) {
                req.setAttribute("errReason", "noUser");
                RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
                dispatch.forward(req, resp);
                return false;
            }
            else if (id == null || !id.matches("\\d+")) {
                req.setAttribute("errReason", "null");
                RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
                dispatch.forward(req, resp);
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * Checks for potential issues before a servlet can be loaded
     * @param user User making the request
     * @param req servlet request
     * @param resp servlet response
     * @param context servlet context
     * @return if request can be accepted
     */
    public boolean canAcceptRequest(User user, HttpServletRequest req, HttpServletResponse resp, ServletContext context) {
        try {
            if (user == null) {
                req.setAttribute("errReason", "noUser");
                RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
                dispatch.forward(req, resp);
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }
}
