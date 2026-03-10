package ui;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JCheckBox chkRememberMe;
    private JButton btnLogin;
    private JButton btnReset;

    private final LoginController loginController;

    public LoginForm() {
        this.loginController = new LoginController();

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

        wireActions();
    }

    private void wireActions() {
        btnLogin.addActionListener(e -> handleLogin());

        btnReset.addActionListener(e -> {
            txtUsername.setText("");
            pwdPassword.setText("");
            chkRememberMe.setSelected(false);
            txtUsername.requestFocus();
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(pwdPassword.getPassword());

        String validationMessage = loginController.validateLoginInput(username, password);

        if (!validationMessage.equals("VALID")) {
            JOptionPane.showMessageDialog(
                    this,
                    validationMessage,
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean success = loginController.login(username, password);

        if (success) {
            DashboardForm dashboardForm = new DashboardForm();
            dashboardForm.setVisible(true);

            JOptionPane.showMessageDialog(
                    dashboardForm,
                    "Login successful.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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