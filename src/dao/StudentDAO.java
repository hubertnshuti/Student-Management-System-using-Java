package dao;

import db.DBConnection;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

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
}