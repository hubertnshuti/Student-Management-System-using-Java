import db.DatabaseInitializer;
import service.UserService;

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

        System.out.println(userService.registerUser("admin", "1234"));

        boolean success = userService.loginUser("admin", "1234");
        System.out.println("Login success: " + success);

        System.out.println("Step 20 finished.");
    }
}