package apitesting.httpmethods;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakeData {
    @Test
    void generateDummyData() {
        // Test method to generate dummy data using the Faker library

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = faker.name().username();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Person1 Name: " + userName);
        System.out.println("Password: " + password);
        System.out.println("Phone Number: " + phone);
        System.out.println("Email: " + email);
    }
}
