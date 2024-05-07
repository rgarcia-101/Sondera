package org.rg25.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;


public interface PropertiesLoader {

    default Properties loadProperties(String propertiesFilePath){
        Logger logger = LogManager.getLogger(this.getClass());
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (Exception e) {
            logger.error("Could not load properties! " + e);
        }
        return properties;
    }
}

