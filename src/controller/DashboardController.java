package controller;

import models.Student;
import service.StudentService;
import util.TableUtil;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DashboardController {

    private final StudentService studentService;

    public DashboardController() {
        this.studentService = new StudentService();
    }

    public DefaultTableModel loadAllStudentsTable() {
        List<Student> students = studentService.getAllStudents();
        return TableUtil.buildStudentTableModel(students);
    }

    public DefaultTableModel searchStudentsTable(String keyword) {
        List<Student> students = studentService.searchStudents(keyword);
        return TableUtil.buildStudentTableModel(students);
    }

    public DefaultTableModel sortStudentsByNameAZTable() {
        List<Student> students = studentService.sortStudentsByNameAZ();
        return TableUtil.buildStudentTableModel(students);
    }

    public DefaultTableModel sortStudentsByNameZATable() {
        List<Student> students = studentService.sortStudentsByNameZA();
        return TableUtil.buildStudentTableModel(students);
    }

    public DefaultTableModel sortStudentsByIdTable() {
        List<Student> students = studentService.sortStudentsById();
        return TableUtil.buildStudentTableModel(students);
    }

    public DefaultTableModel sortStudentsByMarksTable() {
        List<Student> students = studentService.sortStudentsByMarks();
        return TableUtil.buildStudentTableModel(students);
    }

    public DefaultTableModel filterStudentsByMaxMarksTable(double maxMarks) {
        List<Student> students = studentService.filterStudentsByMaxMarks(maxMarks);
        return TableUtil.buildStudentTableModel(students);
    }

    public String addStudent(String name, String email, String course, String marksText) {
        return studentService.addStudent(name, email, course, marksText);
    }

    public String updateStudent(String idText, String name, String email, String course, String marksText) {
        return studentService.updateStudent(idText, name, email, course, marksText);
    }

    public String deleteStudent(String idText) {
        return studentService.deleteStudent(idText);
    }

    public int getRowCount(DefaultTableModel model) {
        return model.getRowCount();
    }
}