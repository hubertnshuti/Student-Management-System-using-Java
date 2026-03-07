import controller.LoginController;
import db.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        LoginController controller = new LoginController();

        String validation = controller.validateLoginInput("admin", "1234");
        System.out.println("Validation result: " + validation);

        boolean success = controller.login("admin", "1234");
        System.out.println("Login success: " + success);

        System.out.println("Step 21 finished.");
    }
}