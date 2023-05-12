package enumvsconstant;

public class SeasonDemo {
    enum Season {
        WINTER, SPRING, SUMMER, FALL
    }

    public static void main(String[] args) {
        Season currentSeason = Season.SPRING;

        switch(currentSeason) {
            case WINTER:
                System.out.println("It's winter!");
                break;
            case SPRING:
                System.out.println("It's spring!");
                break;
            case SUMMER:
                System.out.println("It's summer!");
                break;
            case FALL:
                System.out.println("It's fall!");
                break;
            default:
                System.out.println("Invalid season.");
        }
    }
}
