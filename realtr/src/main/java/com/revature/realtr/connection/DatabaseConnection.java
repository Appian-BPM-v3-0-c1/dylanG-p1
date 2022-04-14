package com.revature.realtr.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection con = null;

    private static final Properties prop = new Properties();

    static {

        try {
            Class.forName("org.postgresql.Driver");
            prop.load(new FileReader("src/main/resources/db.properties"));
            con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }
}