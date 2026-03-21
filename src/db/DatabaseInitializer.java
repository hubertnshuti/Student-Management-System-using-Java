package db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        String createStudentsTableSQL = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    course VARCHAR(100) NOT NULL,
                    marks DOUBLE
                );
                """;

        String createUsersTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(100) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL
                );
                """;

        // Default admin user
        String seedUser = """
                INSERT IGNORE INTO users (username, password)
                VALUES ('admin', '1234');
                """;

        // Default students
        String seedStudents = """
                INSERT IGNORE INTO students (id, name, email, course, marks)
                VALUES
                (1, 'Hubert Nshuti', 'hubert@ur.ac.rw', 'Computer Science', 85),
                (2, 'Radouce Imbabazi', 'radouce@ur.ac.rw', 'Information Technology', 78),
                (3, 'David Uwimana', 'david@ur.ac.rw', 'Software Engineering', 90),
                (4, 'Kalisa Jean', 'kalisa@ur.ac.rw', 'Computer Science', 72),
                (5, 'Ngenzi Ingenzi', 'ngenzi@ur.ac.rw', 'Information Technology', 88);
                """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create tables
            stmt.execute(createStudentsTableSQL);
            stmt.execute(createUsersTableSQL);

            System.out.println("Tables ready.");

            // Seed data
            stmt.execute(seedUser);
            stmt.execute(seedStudents);

            System.out.println("Default data ensured.");

        } catch (SQLException e) {
            System.out.println("Database initialization failed: " + e.getMessage());
        }
    }
}