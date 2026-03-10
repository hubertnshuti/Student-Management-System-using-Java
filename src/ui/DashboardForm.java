package ui;

import controller.DashboardController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

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