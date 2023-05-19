package builderdesignparttern;

class BuilderDemo {
    public static void main(String[] args) {
        // Create a Person object using the Builder pattern
        Person person = new Person.Builder()
                .setName("Shivaji Bajpai")
                .setAge(27)
                .setAddress("Lancon Hills Hyderabad")
                .setPhoneNumber("9461183047")
                .build();

        // Print the details of the Person object
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Address: " + person.getAddress());
        System.out.println("Phone Number: " + person.getPhoneNumber());
    }
}
