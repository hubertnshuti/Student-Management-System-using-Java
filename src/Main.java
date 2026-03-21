import com.formdev.flatlaf.FlatDarkLaf;
import db.DatabaseInitializer;
import ui.LoginFormm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize modern theme.");
        }

        DatabaseInitializer.initializeDatabase();

        SwingUtilities.invokeLater(() -> {
            new LoginFormm().setVisible(true);
        });
    }
}