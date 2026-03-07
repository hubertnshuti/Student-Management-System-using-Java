import dao.StudentDAO;
import models.Student;

public class Main {
    public static void main(String[] args) {
        // Quick model test
        Student s = new Student(
                "Hubert Nshuti Ngendahayo",
                "nshutihubert04@gmail.com",
                "001",
                "Computer Scince",
                90.5
        );

        s.displayInfo();

        // Quick DAO placeholder test
        StudentDAO dao = new StudentDAO();
        dao.add();
        dao.search("001");
    }
}