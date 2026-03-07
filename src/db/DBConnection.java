package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:sms.db";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("SQLite connection established.");

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        return conn;
    }
}