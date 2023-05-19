package testng.dataprovider;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "logins")
    public Object[][] getData() {
        // Define the test data for logins as a 2D array
        Object[][] data = {
                {"shivaji", "123"},
                {"shiv", "456"},
                {"atmecs", "987"}
        };
        return data;
    }
}
