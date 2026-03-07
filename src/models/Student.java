package models;

public class Student extends Person {
    private String studentId;   // business/student ID (e.g., "23RP001")
    private String course;
    private double marks;

    public Student(String name, String email, String studentId, String course, double marks) {
        super(name, email);
        this.studentId = studentId;
        this.course = course;
        this.marks = marks;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Student ID: " + studentId +
                        ", Name: " + name +
                        ", Email: " + email +
                        ", Course: " + course +
                        ", Marks: " + marks
        );
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}