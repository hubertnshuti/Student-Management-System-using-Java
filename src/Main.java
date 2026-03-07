import db.DatabaseInitializer;
import service.UserService;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        UserService userService = new UserService();

        System.out.println(userService.registerUser("admin", "1234"));

        boolean success = userService.loginUser("admin", "1234");
        System.out.println("Login success: " + success);

        System.out.println("Step 20 finished.");
    }
}