package proj;

import org.testng.annotations.DataProvider;

public class EndpointsDataProvider {

    /**
     * Data provider method that provides test data for the endpoints.
     *
     * @return a 2D array of test data sets, where each set contains an endpoint
     */
    @DataProvider(name = "endpoints")
    public static Object[][] endpointsData() {
        return new Object[][] {
                {"/api/users/1"}, //{"/api/users/2"},
                // Add more test data sets here
        };
    }
}
