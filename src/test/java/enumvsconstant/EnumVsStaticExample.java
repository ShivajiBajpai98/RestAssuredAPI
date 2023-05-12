package enumvsconstant;

public class EnumVsStaticExample {

    // Example using static final constants
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;

    // Example using enum
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY;
    }

    public static void main(String[] args) {

        // Example using static final constants
        int day1 = MONDAY;
        int day2 = TUESDAY;
        int day3 = WEDNESDAY;

        System.out.println("Static constant example:");
        System.out.println("Day 1: " + day1);
        System.out.println("Day 2: " + day2);
        System.out.println("Day 3: " + day3);

        // Example using enum
        Day day4 = Day.MONDAY;
        Day day5 = Day.TUESDAY;
        Day day6 = Day.WEDNESDAY;

        System.out.println("\nEnum example:");
        System.out.println("Day 4: " + day4);
        System.out.println("Day 5: " + day5);
        System.out.println("Day 6: " + day6);
    }
}
