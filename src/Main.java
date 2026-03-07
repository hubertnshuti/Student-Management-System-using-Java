import db.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        System.out.println("Application started.");

    }
}