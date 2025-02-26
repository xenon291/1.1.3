package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import jm.task.core.jdbc.model.User;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private final static String URL = "jdbc:mysql://localhost:3306/base";
    private final static String URL = "jdbc:mysql://localhost:3306/1.1.4";
    private final static String USER = "root";
    private final static String PASSWORD = "1234";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";


    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperties(getProperties())
                .buildSessionFactory();
        return sessionFactory;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put(Environment.URL, URL);
        properties.put(Environment.USER, USER);
        properties.put(Environment.PASS, PASSWORD);
        properties.put(Environment.DIALECT, DIALECT);
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "");
        properties.put(Environment.DRIVER, DRIVER);
        return properties;
    }



    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            //System.out.println("Connection to Store DB succesfull!");

        } catch (SQLException |ClassNotFoundException ex) {
            throw new RuntimeException(ex);
            //System.out.println("Connection failed...");
        }
        return connection;

    }

}
