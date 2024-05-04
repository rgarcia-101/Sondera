package org.rg25.util;

import java.util.Properties;


public interface PropertiesLoader {


    //TODO load once on startup, put in session

    default Properties loadProperties(String propertiesFilePath){
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}

