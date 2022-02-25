package ro.fasttrackit.curs19.homework;

public class StringUtils {
    public static String ensureNotEmpty(String word) {
        if (word != null || "".equals(word.trim())) {
            return word;
        } else {
            throw new IllegalArgumentException("Null or empty word not accepted!");
        }
    }

    public static double ensureNotNegative(double number) {
        if (number >= 0) {
            return number;
        } else {
            throw new IllegalArgumentException("Negative value not accepted!");
        }
    }

}
