package ui;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JCheckBox chkRememberMe;
    private JButton btnLogin;
    private JButton btnReset;

    public LoginForm() {
        setContentPane(mainPanel);
        setTitle("Kigali Student Hub - Secure Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // FlatLaf client properties
        txtUsername.putClientProperty("JTextField.placeholderText", "Username or Email");
        pwdPassword.putClientProperty("JTextField.placeholderText", "Password");

        txtUsername.putClientProperty("JComponent.roundRect", true);
        pwdPassword.putClientProperty("JComponent.roundRect", true);

        btnLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnLogin.setBackground(new Color(0, 122, 255));
        btnLogin.setForeground(Color.WHITE);

        btnReset.putClientProperty("JButton.buttonType", "roundRect");
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getPwdPassword() {
        return pwdPassword;
    }

    public JCheckBox getChkRememberMe() {
        return chkRememberMe;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize modern theme.");
        }

        SwingUtilities.invokeLater(() -> {
            LoginForm form = new LoginForm();
            form.setVisible(true);
        });
    }
}