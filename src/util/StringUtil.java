package util;

public class StringUtil {

    public static String toTitleCase(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        String[] words = text.trim().toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return result.toString().trim();
    }
