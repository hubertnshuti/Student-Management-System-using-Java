package util;

import models.Student;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableUtil {

    public static DefaultTableModel buildStudentTableModel(List<Student> students) {

        String[] columns = {"ID", "Name", "Email", "Course", "Marks"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Student s : students) {

            Object[] row = {
                    s.getStudentId(),
                    s.getName(),
                    s.getEmail(),
                    s.getCourse(),
                    s.getMarks()
            };

            model.addRow(row);
        }

        return model;
    }
}