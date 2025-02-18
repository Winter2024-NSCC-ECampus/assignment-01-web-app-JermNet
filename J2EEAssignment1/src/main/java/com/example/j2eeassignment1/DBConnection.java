package com.example.j2eeassignment1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

// Class to connect to the DB, uses info from the .properties I created, making this work a lot more like a "manual" version of what we did with JPA
public class DBConnection {
    private static Connection connection;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db-config.properties");
            props.load(input);

            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String driver = props.getProperty("jdbc.driver");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
