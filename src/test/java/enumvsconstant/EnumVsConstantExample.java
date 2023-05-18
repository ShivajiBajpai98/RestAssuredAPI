package enumvsconstant;

public class EnumVsConstantExample {

    // Enum definition
    public enum DaysOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    // Constants
    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";
    public static final String SATURDAY = "Saturday";
    public static final String SUNDAY = "Sunday";

    public static void main(String[] args) {

        // Using enum
        DaysOfWeek day1 = DaysOfWeek.MONDAY;
        DaysOfWeek day2 = DaysOfWeek.TUESDAY;

        if (day1 == DaysOfWeek.MONDAY) {
            System.out.println("Today is Monday!");
        } else {
            System.out.println("Today is not Monday.");
        }

        // Using constants
        String day3 = MONDAY;
        String day4 = TUESDAY;

        if (day3.equals(MONDAY)) {
            System.out.println("Today is Monday!");
        } else {
            System.out.println("Today is not Monday.");
        }

    }
}
