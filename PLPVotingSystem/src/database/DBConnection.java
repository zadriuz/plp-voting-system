package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Connection DBConnect() {
        Properties props = new Properties();
        File configFile = new File("db.properties");
        if (!configFile.exists()) {
            configFile = new File("PLPVotingSystem/db.properties");
        }

        try (FileInputStream fis = new FileInputStream(configFile)) {
            props.load(fis);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            System.out.println("DB Connection Error: " + e.getMessage());
            return null;
        }
    }
}