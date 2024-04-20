package org.rg25.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Connects to the database
 */

public class Database implements PropertiesLoader {

    private static Database instance = new Database();
    private final Logger logger = LogManager.getLogger(this.getClass());


    private Properties properties;
    private Connection connection;

    /** private constructor prevents instantiating this class anywhere else
     **/
    private Database() {
        properties = loadProperties("/database.properties");
    }

    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error disconnecting!" + e);
            }
        }

        connection = null;
    }

    public static Database getInstance() {
        return instance;
    }


    public void runSQL(String sqlFile) {

        Statement statement = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(sqlFile))))  {
            connect();
            statement = connection.createStatement();

            String sql = "";
            while (br.ready())
            {
                char inputValue = (char)br.read();

                if(inputValue == ';')
                {
                    statement.executeUpdate(sql);
                    sql = "";
                }
                else
                    sql += inputValue;
            }

        } catch (SQLException e) {
            logger.error("Error with the sql command! " + e);
        } catch (Exception e) {
            logger.error("Error running sql! " + e);
        } finally {
            disconnect();
        }

    }
}