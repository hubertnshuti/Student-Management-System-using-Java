package ui;

import controller.DashboardController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardForm extends JFrame {

    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtEmail;
    private JComboBox<String> comboBox1;
    private JSlider slider1;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JRadioButton sortNameByARadioButton;
    private JRadioButton sortNameByZRadioButton;
    private JRadioButton sortByMarksRadioButton;
    private JRadioButton sortByID;
    private JTextField txtSearch;
    private JButton searchButton1;
    private JTable studentTable;
    private JTextField txtMarks;

    private final DashboardController dashboardController;

    public DashboardForm() {
        dashboardController = new DashboardController();

        setTitle("Student Management System - Dashboard");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        setupTable();
        setupCourseCombo();
        setupSortingGroup();
        setupSlider();
        setupMenuBar();
        applyModernStyling();
        wireActions();

        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        loadAllStudents();
    }

    private void setupTable() {
        String[] columnNames = {"ID", "Name", "Email", "Course", "Marks"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable.setModel(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setupCourseCombo() {
        comboBox1.removeAllItems();
        comboBox1.addItem("Computer Science");
        comboBox1.addItem("Information Technology");
        comboBox1.addItem("Software Engineering");
    }

    private void setupSortingGroup() {
        ButtonGroup sortGroup = new ButtonGroup();
        sortGroup.add(sortNameByARadioButton);
        sortGroup.add(sortNameByZRadioButton);
        sortGroup.add(sortByMarksRadioButton);
        sortGroup.add(sortByID);
    }

    private void setupSlider() {
        slider1.setMinimum(0);
        slider1.setMaximum(100);
        slider1.setValue(100);
        slider1.setMajorTickSpacing(10);
        slider1.setPaintTicks(true);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        javax.swing.border.Border itemPadding = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        javax.swing.border.Border menuPadding = BorderFactory.createEmptyBorder(5, 12, 5, 12);

        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setBorder(menuPadding);

        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setBorder(itemPadding);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setBorder(itemPadding);

        logoutItem.addActionListener(e -> {
            this.dispose();
            LoginFormm login = new LoginFormm();
            login.setVisible(true);
        });

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Students Menu
        JMenu studentsMenu = new JMenu("Students");
        studentsMenu.setBorder(menuPadding);

        JMenuItem addItem = new JMenuItem("Add Student");
        addItem.setBorder(itemPadding);

        JMenuItem updateItem = new JMenuItem("Update Student");
        updateItem.setBorder(itemPadding);

        JMenuItem deleteItem = new JMenuItem("Delete Student");
        deleteItem.setBorder(itemPadding);

        addItem.addActionListener(e -> handleAddStudent());
        updateItem.addActionListener(e -> handleUpdateStudent());
        deleteItem.addActionListener(e -> handleDeleteStudent());

        studentsMenu.add(addItem);
        studentsMenu.add(updateItem);
        studentsMenu.add(deleteItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setBorder(menuPadding);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setBorder(itemPadding);

        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "UR-CST Student Management System\nDeveloped by Hubert and Team",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        });

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(studentsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
    }

    private void applyModernStyling() {
        Font inputFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        Font controlFont = new Font("SansSerif", Font.PLAIN, 15);

        txtName.setFont(inputFont);
        txtEmail.setFont(inputFont);
        txtMarks.setFont(inputFont);
        txtSearch.setFont(inputFont);
        comboBox1.setFont(inputFont);

        txtName.putClientProperty("JComponent.roundRect", true);
        txtEmail.putClientProperty("JComponent.roundRect", true);
        txtMarks.putClientProperty("JComponent.roundRect", true);
        txtSearch.putClientProperty("JComponent.roundRect", true);
        comboBox1.putClientProperty("JComponent.roundRect", true);

        addButton.putClientProperty("JButton.buttonType", "roundRect");
        addButton.setFont(buttonFont);
        addButton.setBackground(new Color(26, 115, 232));
        addButton.setForeground(Color.WHITE);

        deleteButton.putClientProperty("JButton.buttonType", "roundRect");
        deleteButton.setFont(buttonFont);
        deleteButton.setBackground(new Color(180, 50, 50));
        deleteButton.setForeground(Color.WHITE);

        updateButton.putClientProperty("JButton.buttonType", "roundRect");
        updateButton.setFont(buttonFont);
        updateButton.setForeground(new Color(80, 160, 255));

        Color darkGrey = new Color(60, 63, 65);

        searchButton.putClientProperty("JButton.buttonType", "roundRect");
        searchButton.setFont(buttonFont);
        searchButton.setBackground(darkGrey);

        searchButton1.putClientProperty("JButton.buttonType", "roundRect");
        searchButton1.setFont(buttonFont);
        searchButton1.setBackground(darkGrey);

        showAllButton.putClientProperty("JButton.buttonType", "roundRect");
        showAllButton.setFont(buttonFont);
        showAllButton.setBackground(darkGrey);

        txtName.putClientProperty("JTextField.placeholderText", "e.g. John Doe");
        txtEmail.putClientProperty("JTextField.placeholderText", "student@example.com");
        txtMarks.putClientProperty("JTextField.placeholderText", "0.0 - 100.0");
        txtSearch.putClientProperty("JTextField.placeholderText", "Search by name or email...");

        sortNameByARadioButton.setFont(controlFont);
        sortNameByZRadioButton.setFont(controlFont);
        sortByMarksRadioButton.setFont(controlFont);
        sortByID.setFont(controlFont);
        slider1.setFont(new Font("SansSerif", Font.PLAIN, 13));

        styleAllLabels(mainPanel);
        styleAllTitledBorders(mainPanel);

        studentTable.setFont(new Font("SansSerif", Font.PLAIN, 15));
        studentTable.setRowHeight(40);
        studentTable.setShowVerticalLines(false);
        studentTable.setGridColor(new Color(60, 63, 65));
        studentTable.setSelectionBackground(new Color(26, 115, 232));
        studentTable.setSelectionForeground(Color.WHITE);

        studentTable.putClientProperty("JTable.alternateRowColor", new Color(50, 53, 57));

        studentTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        studentTable.getTableHeader().setBackground(new Color(43, 45, 48));
        studentTable.getTableHeader().setForeground(new Color(220, 220, 220));
        studentTable.getTableHeader().setPreferredSize(new Dimension(100, 40));

        updateButton.putClientProperty("FlatLaf.style", "disabledBackground: #2A3036; disabledText: #507090");
        deleteButton.putClientProperty("FlatLaf.style", "disabledBackground: #3D2626; disabledText: #8A4A4A");
    }
    /**
     * Recursively applies a uniform font and color to all JLabels within a given container.
     */
    private void styleAllLabels(Container container) {
        Font premiumLabelFont = new Font("SansSerif", Font.BOLD, 13);
        Color premiumLabelColor = new Color(160, 165, 170);

        for (Component c : container.getComponents()) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                label.setFont(premiumLabelFont);
                label.setForeground(premiumLabelColor);
            } else if (c instanceof Container) {
                styleAllLabels((Container) c);
            }
        }
    }

    private void styleAllTitledBorders(Container container) {
        Font premiumBorderFont = new Font("SansSerif", Font.BOLD, 14);
        Color premiumBorderColor = new Color(80, 160, 255);

        for (Component c : container.getComponents()) {
            if (c instanceof JComponent) {
                JComponent jc = (JComponent) c;
                if (jc.getBorder() instanceof javax.swing.border.TitledBorder) {
                    javax.swing.border.TitledBorder tb = (javax.swing.border.TitledBorder) jc.getBorder();
                    tb.setTitleFont(premiumBorderFont);
                    tb.setTitleColor(premiumBorderColor);
                }
            }
            if (c instanceof Container) {
                styleAllTitledBorders((Container) c);
            }
        }
    }

    private void wireActions() {
        showAllButton.addActionListener(e -> loadAllStudents());
        addButton.addActionListener(e -> handleAddStudent());
        searchButton.addActionListener(e -> handleSearch());
        searchButton1.addActionListener(e -> handleSearch());
        deleteButton.addActionListener(e -> handleDeleteStudent());
        updateButton.addActionListener(e -> handleUpdateStudent());

        sortNameByARadioButton.addActionListener(e -> {
            studentTable.setModel(dashboardController.sortStudentsByNameAZTable());
        });

        sortNameByZRadioButton.addActionListener(e -> {
            studentTable.setModel(dashboardController.sortStudentsByNameZATable());
        });

        sortByMarksRadioButton.addActionListener(e -> {
            studentTable.setModel(dashboardController.sortStudentsByMarksTable());
        });

        sortByID.addActionListener(e -> {
            studentTable.setModel(dashboardController.sortStudentsByIdTable());
        });

        slider1.addChangeListener(e -> {
            double maxMarks = slider1.getValue();
            studentTable.setModel(dashboardController.filterStudentsByMaxMarksTable(maxMarks));
        });

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                populateFormFromSelectedRow();
            }
        });
    }

    private void loadAllStudents() {
        DefaultTableModel model = dashboardController.loadAllStudentsTable();
        studentTable.setModel(model);
    }

    private void handleAddStudent() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String course = comboBox1.getSelectedItem() == null ? "" : comboBox1.getSelectedItem().toString();
        String marks = txtMarks.getText();

        String message = dashboardController.addStudent(name, email, course, marks);

        JOptionPane.showMessageDialog(
                this,
                message,
                "Add Student",
                message.toLowerCase().contains("success")
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.WARNING_MESSAGE
        );

        if (message.toLowerCase().contains("success")) {
            clearForm();
            loadAllStudents();
        }
    }

    private void handleSearch() {
        String keyword = txtSearch.getText().trim();

        if (keyword.isEmpty()) {
            loadAllStudents();
            return;
        }

        studentTable.setModel(dashboardController.searchStudentsTable(keyword));
    }

    private void handleDeleteStudent() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student row first.",
                    "Delete Student",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String idText = studentTable.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete student ID " + idText + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String message = dashboardController.deleteStudent(idText);

        JOptionPane.showMessageDialog(
                this,
                message,
                "Delete Student",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
        loadAllStudents();
    }

    private void handleUpdateStudent() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student row first.",
                    "Update Student",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String idText = studentTable.getValueAt(selectedRow, 0).toString();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String course = comboBox1.getSelectedItem() == null ? "" : comboBox1.getSelectedItem().toString();
        String marks = txtMarks.getText();

        String message = dashboardController.updateStudent(idText, name, email, course, marks);

        JOptionPane.showMessageDialog(
                this,
                message,
                "Update Student",
                message.toLowerCase().contains("success")
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.WARNING_MESSAGE
        );

        if (message.toLowerCase().contains("success")) {
            clearForm();
            loadAllStudents();
        }
    }

    private void populateFormFromSelectedRow() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        txtName.setText(studentTable.getValueAt(selectedRow, 1).toString());
        txtEmail.setText(studentTable.getValueAt(selectedRow, 2).toString());
        comboBox1.setSelectedItem(studentTable.getValueAt(selectedRow, 3).toString());
        txtMarks.setText(studentTable.getValueAt(selectedRow, 4).toString());

        updateButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }

    private void clearForm() {
        txtName.setText("");
        txtEmail.setText("");
        txtMarks.setText("");
        txtSearch.setText("");
        comboBox1.setSelectedIndex(0);
        studentTable.clearSelection();
        slider1.setValue(100);

        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        txtName.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardForm form = new DashboardForm();
            form.setVisible(true);
        });
    }
}