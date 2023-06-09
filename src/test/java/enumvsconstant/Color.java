package enumvsconstant;

public enum Color {
    RED("FF0000"),
    GREEN("00FF00"),
    BLUE("0000FF");

    private final String hexCode;

    Color(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }

    // Builder2.java
    public static class Main {
        public static void main(String[] args) {
            Color red = Color.RED;
            System.out.println(red.getHexCode()); // prints "FF0000"
        }
    }
}
