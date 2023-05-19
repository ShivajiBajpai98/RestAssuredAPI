package builderdesignparttern;

public class Person {
    private String name;
    private int age;
    private String address;
    private String phoneNumber;

    // Private constructor to enforce object creation through the builder
    private Person(String name, int age, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters for the properties (omitted for brevity)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Nested Builder class
    public static class Builder {
        private String name;
        private int age;
        private String address;
        private String phoneNumber;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build() {
            try {
                // Validate and return the Person object
                if (name == null || address == null) {
                    throw new IllegalStateException("Name and address are required");
                }
                return new Person(name, age, address, phoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
