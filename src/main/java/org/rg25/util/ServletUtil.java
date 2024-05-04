package org.rg25.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

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
}
