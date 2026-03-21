package ui;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFormm extends JFrame {

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel lblLogo;
    private JLabel lblLogoText;

    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    private JCheckBox chkRememberMe;
    private JButton btnLogin;
    private JButton btnReset;
    private JLabel lblMessage;
    private JProgressBar progressBar1;
    private JLabel lblTitle;
    private JLabel lblWelcome;

    private final LoginController loginController;

    public LoginFormm() {
        this.loginController = new LoginController();

        setContentPane(mainPanel);
        setTitle("UR-CST Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Apply our custom sizes, fonts, and colors
        applyModernSizing();

        // HIDE BOTH THE PROGRESS BAR AND MESSAGE ON LOAD
        progressBar1.setVisible(false);
        lblMessage.setVisible(false);

        wireActions();
    }

    private void applyModernSizing() {
        // 1. Setup Custom Fonts (Bigger, Bolder, Modern!)
        Font titleFont = new Font("SansSerif", Font.BOLD, 24);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);
        Font inputFont = new Font("SansSerif", Font.PLAIN, 16);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);

        // Make the Title Pop!
//        lblTitle.setFont(titleFont);
        lblTitle.setForeground(new Color(240, 240, 240)); // Bright off-white

        // Apply Fonts to Labels & Checkbox
        lblUsername.setFont(labelFont);
        lblPassword.setFont(labelFont);
        chkRememberMe.setFont(labelFont);
        lblMessage.setFont(labelFont);

        // Apply Fonts to Inputs
        txtUsername.setFont(inputFont);
        pwdPassword.setFont(inputFont);

        // 2. Force Chunky Heights
        Dimension inputSize = new Dimension(-1, 45);
        txtUsername.setPreferredSize(inputSize);
        pwdPassword.setPreferredSize(inputSize);

        Dimension buttonSize = new Dimension(-1, 45);
        btnLogin.setPreferredSize(buttonSize);
        btnReset.setPreferredSize(buttonSize);

        // 3. Apply FlatLaf Styling & Colors
        txtUsername.putClientProperty("JTextField.placeholderText", "Username or Email");
        pwdPassword.putClientProperty("JTextField.placeholderText", "••••••••");
        txtUsername.putClientProperty("JComponent.roundRect", true);
        pwdPassword.putClientProperty("JComponent.roundRect", true);

        // Style the Sign In Button
        btnLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnLogin.setFont(buttonFont);
        btnLogin.setBackground(new Color(26, 115, 232));
        btnLogin.setForeground(Color.WHITE);

        // Style the Clear All Button
        btnReset.putClientProperty("JButton.buttonType", "roundRect");
        btnReset.setFont(buttonFont);
        btnReset.setBackground(new Color(60, 63, 65));
        btnReset.setForeground(new Color(200, 200, 200));
    }
    private void wireActions() {
        btnLogin.addActionListener(e -> handleLogin());

        btnReset.addActionListener(e -> {
            txtUsername.setText("");
            pwdPassword.setText("");
            chkRememberMe.setSelected(false);
            lblMessage.setVisible(false);
            progressBar1.setVisible(false);
            txtUsername.requestFocus();
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(pwdPassword.getPassword());

        lblMessage.setVisible(true);

        String validationMessage = loginController.validateLoginInput(username, password);
        if (!validationMessage.equals("VALID")) {
            lblMessage.setText(validationMessage);
            return;
        }

        btnLogin.setEnabled(false);
        btnReset.setEnabled(false);
        progressBar1.setVisible(true);
        progressBar1.setValue(0);
        progressBar1.setStringPainted(true);
        progressBar1.setString("Connecting to database...");
        lblMessage.setText("Please wait...");

        Timer timer = new Timer(30, new ActionListener() {
            int progress = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 2;
                progressBar1.setValue(progress);

                if (progress == 50) {
                    progressBar1.setString("Verifying credentials...");
                }

                if (progress >= 100) {
                    ((Timer)e.getSource()).stop();
                    processActualLogin(username, password);
                }
            }
        });
        timer.start();
    }

    private void processActualLogin(String username, String password) {
        boolean success = loginController.login(username, password);

        if (success) {
            lblMessage.setText("Login successful. Opening Dashboard...");
            DashboardForm dashboardForm = new DashboardForm();
            dashboardForm.setVisible(true);
            dispose();
        } else {
            lblMessage.setText("Invalid username or password.");
            progressBar1.setVisible(false);
            btnLogin.setEnabled(true);
            btnReset.setEnabled(true);
        }
    }

    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getPwdPassword() { return pwdPassword; }
    public JCheckBox getChkRememberMe() { return chkRememberMe; }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnReset() { return btnReset; }
    public JPanel getMainPanel() { return mainPanel; }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize modern theme.");
        }

        SwingUtilities.invokeLater(() -> {
            LoginFormm form = new LoginFormm();
            form.setVisible(true);
        });
    }
}