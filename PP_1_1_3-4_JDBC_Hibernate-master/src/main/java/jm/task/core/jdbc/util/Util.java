package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/base";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";


    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //System.out.println("Connection to Store DB succesfull!");

        } catch (SQLException |ClassNotFoundException ex) {
            throw new RuntimeException(ex);
            //System.out.println("Connection failed...");
        }
        return connection;

    }

}
