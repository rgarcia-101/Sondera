package org.rg25.persistance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apininja.Holiday;
import org.rg25.util.PropertiesLoader;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

public class HolidayMapper implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    Properties properties = loadProperties("/api.properties");

    public Holiday[] getHolidays(int year) throws NotFoundException{
        String key = (String) properties.get("key");
        String url = (String) properties.get("url");
        url += "&year=" + year + "$type=major_holiday";
        logger.info("API URL: " + url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);


        String response = target.request(MediaType.APPLICATION_JSON).header("X-Api-Key",key).get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        logger.info("JSON received from API: " + response);
        Holiday[] holidays = null;
        try {
            holidays = mapper.readValue(response, Holiday[].class);
        } catch (JsonProcessingException e) {
            logger.error("Could not parse JSON! " + e, e);
        }

        return holidays;
    }
}
