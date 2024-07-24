package tn.esprit.bookstore.bookms.bookcommandms.Utility;

public class ConversionUtil {
        // Convert Long to String
        public static String longToString(Long value) {
            if (value == null) {
                return null;
            }
            return value.toString();
        }

        // Convert String to Long
        public static Long stringToLong(String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid format for Long: " + value);
            }
        }
}
