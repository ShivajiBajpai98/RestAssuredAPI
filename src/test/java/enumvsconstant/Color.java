package enumvsconstant;

// Color.java
public enum Color {
    RED("FF0000"), GREEN("00FF00"), BLUE("0000FF");

    private final String hexCode;

    Color(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }
}

// Main.java
class Main {
    public static void main(String[] args) {
        Color red = Color.RED;
        System.out.println(red.getHexCode()); // prints "FF0000"
    }
}
