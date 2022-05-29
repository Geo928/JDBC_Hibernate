package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "yra_89181138199";
    private static SessionFactory sessionFactory;

    private static final Connection connection;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.current_session_context_class", "thread");
                configuration.setProperty(Environment.URL, DB_URL);
                configuration.setProperty(Environment.USER, DB_USERNAME);
                configuration.setProperty(Environment.PASS, DB_PASSWORD);
                configuration.setProperty("hibernate.connection.release_mode", "auto");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException hibernateException) {
                hibernateException.getStackTrace();
                throw new RuntimeException();
            }
        } return sessionFactory;
    }

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
