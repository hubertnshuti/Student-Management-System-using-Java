package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// 1. We added "extends JFrame" here so Java knows this is a Window!
public class DashboardForm extends JFrame {

    // These are all your variables exactly as IntelliJ generated them
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JSlider slider1;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JRadioButton sortNameByARadioButton;
    private JRadioButton sortNameByZRadioButton;
    private JRadioButton sortByMarksRadioButton;
    private JRadioButton radioButton4;
    private JTextField textField3;
    private JButton searchButton1;
    private JTable studentTable;

    // This is the Constructor. It runs the moment the window is created.
    public DashboardForm() {
        // Setup the main window properties
        setTitle("Student Management System - Dashboard");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null); // This centers the window on your screen

        // Call our custom method to set up the table headings
        setupTable();
    }

    // This method removes the fake "Shape/Color" data and adds your real headings
    private void setupTable() {
        // Define your column headings
        String[] columnNames = {"ID", "Name", "Email", "Course", "Marks"};

        // Create a new Table Model with those columns and 0 starting rows
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Tell your visual table to use this new model
        studentTable.setModel(tableModel);
    }

    // A main method so you can run and test this specific screen easily
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardForm form = new DashboardForm();
            form.setVisible(true);
        });
    }
}