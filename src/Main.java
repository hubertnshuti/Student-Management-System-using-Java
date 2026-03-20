import com.formdev.flatlaf.FlatDarkLaf;
import ui.LoginFormm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize modern theme.");
        }

        SwingUtilities.invokeLater(() -> {
            new LoginFormm().setVisible(true);
        });
    }
}