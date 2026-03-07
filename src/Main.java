import dao.StudentDAO;
import db.DatabaseInitializer;
import models.Student;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        StudentDAO dao = new StudentDAO();

        // Insert test student
        Student student = new Student(
                "Alice Johnson",
                "Alice@Example.com",
                "001",
                "Computer Science",
                88
        );

        dao.addStudent(student);

        // Search using lowercase to prove case-insensitive search works
        List<Student> results = dao.searchStudents("alice");

        System.out.println("Search results:");

        for (Student s : results) {
            s.displayInfo();
        }

        System.out.println("Search test finished.");
    }
}