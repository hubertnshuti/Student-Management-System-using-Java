package dao;

import db.DBConnection;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM students WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                // If rs.next() is true, it found a matching email.
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Email check failed: " + e.getMessage());
        }
        return false;
    }

    public void addStudent(Student student) {

        String sql = "INSERT INTO students(name, email, course, marks) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getCourse());
            pstmt.setDouble(4, student.getMarks());

            pstmt.executeUpdate();

            System.out.println("Student inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("email"),
                        String.valueOf(rs.getInt("id")),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );

                students.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Fetch failed: " + e.getMessage());
        }

        return students;
    }

    public Student getStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("name"),
                        rs.getString("email"),
                        String.valueOf(rs.getInt("id")),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("Find by ID failed: " + e.getMessage());
        }

        return null;
    }

    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    public boolean updateStudent(int id, Student student) {

        String sql = "UPDATE students SET name = ?, email = ?, course = ?, marks = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getCourse());
            pstmt.setDouble(4, student.getMarks());
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully.");
                return true;
            } else {
                System.out.println("Student not found.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    public List<Student> searchStudents(String keyword) {

        List<Student> students = new ArrayList<>();

        String sql = """
            SELECT * FROM students
            WHERE CAST(id AS CHAR) LIKE ?
              OR LOWER(name) LIKE LOWER(?)
              OR LOWER(email) LIKE LOWER(?)
              OR LOWER(course) LIKE LOWER(?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword.trim() + "%";

            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("email"),
                        String.valueOf(rs.getInt("id")),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );
                students.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        }

        return students;
    }
}