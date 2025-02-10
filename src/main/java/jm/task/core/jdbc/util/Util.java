package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    private static String url;
    private static String username;
    private static String password;

    public static Connection getConnection()  {

        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\db.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.info("Произошла ошибка");
        }
        try {
            connection = DriverManager.getConnection(url, username, password);

            logger.info("установлено.");
        } catch (SQLException e) {
            logger.info("oшибка при подключении к базе данных: ");
            e.printStackTrace();

        }
        return connection;
    }
}
