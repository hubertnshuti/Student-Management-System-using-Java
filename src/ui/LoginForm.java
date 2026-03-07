package ui;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    // Clean, single names for your components
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JCheckBox chkRememberMe;
    private JButton btnLogin;
    private JButton btnReset;

    public LoginForm() {
        // 1. Setup the Window (Keeping your exact settings)
        setContentPane(mainPanel);
        setTitle("Kigali Student Hub - Secure Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // YOUR CUSTOM DESKTOP SIZE IS RIGHT HERE!
        setSize(1280, 720);
        setLocationRelativeTo(null); // Centers on screen

        // 2. Apply Modern FlatLaf Styling (Using the clean names)
        // Adds placeholder text inside the fields
        txtUsername.putClientProperty("JTextField.placeholderText", "Username or Email");
        pwdPassword.putClientProperty("JTextField.placeholderText", "Password");

        // Makes the text fields slightly rounded
        txtUsername.putClientProperty("JComponent.roundRect", true);
        pwdPassword.putClientProperty("JComponent.roundRect", true);

        // Styles the primary Sign In button blue and rounded
        btnLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnLogin.setBackground(new Color(0, 122, 255));
        btnLogin.setForeground(Color.WHITE);

        // Styles the Reset button rounded
        btnReset.putClientProperty("JButton.buttonType", "roundRect");
    }

    public static void main(String[] args) {
        // Turn on the FlatLaf Dark Theme
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize modern theme.");
        }

        // Launch the window
        SwingUtilities.invokeLater(() -> {
            LoginForm form = new LoginForm();
            form.setVisible(true);
        });
    }
}