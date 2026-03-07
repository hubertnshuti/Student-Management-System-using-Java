import dao.StudentDAO;
import db.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        StudentDAO dao = new StudentDAO();

        dao.deleteStudent(1);

        System.out.println("Delete operation finished.");
    }
}