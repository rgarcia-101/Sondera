package org.rg25.util;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.PrintWriter;

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
     * Processes the JSON for instances where the request was denied
     * @param writer writer to write JSON to
     */
    public void denyJson(PrintWriter writer) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("responseCode", "1");
        writer.print(jsonResponse.toString());
        writer.flush();
    }
}
