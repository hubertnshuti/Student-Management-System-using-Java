package util;

public class Validator {

    public static boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty() && name.trim().matches("[A-Za-z ]+");
    }

    public static boolean isEmailValid(String email) {
        return email != null
                && !email.trim().isEmpty()
                && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isCourseValid(String course) {
        return course != null && !course.trim().isEmpty();
    }

    public static boolean isMarksValid(String marksText) {
        if (marksText == null || marksText.trim().isEmpty()) {
            return false;
        }

        try {
            double marks = Double.parseDouble(marksText.trim());
            return marks >= 0 && marks <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isStudentValid(String name, String email, String course, String marksText) {
        return isNameValid(name)
                && isEmailValid(email)
                && isCourseValid(course)
                && isMarksValid(marksText);
    }
}