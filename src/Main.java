import dao.StudentDAO;
import db.DatabaseInitializer;
import models.Student;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        StudentDAO dao = new StudentDAO();

        Student s = new Student(
                "John Doe",
                "student@example.com",
                "001",
                "Computer Science",
                85
        );

        dao.addStudent(s);

        System.out.println("Application finished.");

    }
}