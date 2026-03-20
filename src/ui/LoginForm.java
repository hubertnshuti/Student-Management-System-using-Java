package ui;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    // These variables perfectly match the field names we just set in the visual designer
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel lblLogo;

    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    private JCheckBox chkRememberMe;
    private JButton btnLogin;
    private JButton btnReset;
    private JLabel lblMessage;

    private final LoginController loginController;

    public LoginForm() {
        this.loginController = new LoginController();

        // Bind the visual form to this window
        setContentPane(mainPanel);
        setTitle("UR-CST Student Hub - Secure Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600); // Slightly adjusted to fit our new proportions beautifully
        setLocationRelativeTo(null);

        // Apply your modern FlatLaf styling
        txtUsername.putClientProperty("JTextField.placeholderText", "Username or Email");
        pwdPassword.putClientProperty("JTextField.placeholderText", "Password");

        txtUsername.putClientProperty("JComponent.roundRect", true);
        pwdPassword.putClientProperty("JComponent.roundRect", true);

        btnLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnLogin.setBackground(new Color(0, 122, 255)); // UR-CST Blue
        btnLogin.setForeground(Color.WHITE);

        btnReset.putClientProperty("JButton.buttonType", "roundRect");

        lblMessage.setForeground(Color.LIGHT_GRAY);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);

        wireActions();
    }

    private void wireActions() {
        // What happens when you click Login
        btnLogin.addActionListener(e -> handleLogin());

        // What happens when you click Clear All
        btnReset.addActionListener(e -> {
            txtUsername.setText("");
            pwdPassword.setText("");
            chkRememberMe.setSelected(false);
            lblMessage.setText("Waiting for server response...");
            txtUsername.requestFocus();
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(pwdPassword.getPassword());

        lblMessage.setText("Verifying credentials...");

        String validationMessage = loginController.validateLoginInput(username, password);

        if (!validationMessage.equals("VALID")) {
            lblMessage.setText(validationMessage);
            return;
        }

        boolean success = loginController.login(username, password);

        if (success) {
            // If login works, open the Dashboard and close this window
            DashboardForm dashboardForm = new DashboardForm();
            dashboardForm.setVisible(true);

            lblMessage.setText("Login successful.");
            dispose();
        } else {
            lblMessage.setText("Invalid username or password.");
        }
    }

    // Getters
    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getPwdPassword() { return pwdPassword; }
    public JCheckBox getChkRememberMe() { return chkRememberMe; }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnReset() { return btnReset; }
    public JPanel getMainPanel() { return mainPanel; }

    public static void main(String[] args) {
        // Initialize the dark theme before showing the window
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