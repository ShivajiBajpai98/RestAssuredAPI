package proj;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class APITestFramework {

    private String baseUri;
    private String username;
    private String password;
    private String postBody;
    private String putBody;

    @BeforeClass
    public void setup() {
        ConfigReader configReader = new ConfigReader();
        // Read properties from the configuration file
        baseUri = configReader.getProperty("baseUri");
        username = configReader.getProperty("username");
        password = configReader.getProperty("password");
        postBody = configReader.getProperty("postBody");
        putBody = configReader.getProperty("putBody");
    }

    /**
     * Test method for sending a GET request to the specified endpoint.
     *
     * @param endpoint the endpoint to send the GET request to
     */
    @Test(dataProvider = "endpoints", dataProviderClass = EndpointsDataProvider.class)
    public void testGetRequest(String endpoint) {
        String url = baseUri + endpoint;
        Response getResponse = APIRequestSender.get(url, new HashMap<>(), username, password);
        System.out.println("GET Response: " + getResponse.asString());
    }

    /**
     * Test method for sending a POST request to the specified endpoint.
     *
     * @param endpoint the endpoint to send the POST request to
     */
    @Test(dataProvider = "endpoints", dataProviderClass = EndpointsDataProvider.class)
    public void testPostRequest(String endpoint) {
        String url = baseUri + endpoint;
        Response postResponse = APIRequestSender.post(url, postBody, new HashMap<>(), username, password);
        System.out.println("POST Response: " + postResponse.asString());
    }

    /**
     * Test method for sending a PUT request to the specified endpoint.
     *
     * @param endpoint the endpoint to send the PUT request to
     */
    @Test(dataProvider = "endpoints", dataProviderClass = EndpointsDataProvider.class)
    public void testPutRequest(String endpoint) {
        String url = baseUri + endpoint;
        Response putResponse = APIRequestSender.put(url, putBody, new HashMap<>(), username, password);
        System.out.println("PUT Response: " + putResponse.asString());
    }

    /**
     * Test method for sending a DELETE request to the specified endpoint.
     *
     * @param endpoint the endpoint to send the DELETE request to
     */
    @Test(dataProvider = "endpoints", dataProviderClass = EndpointsDataProvider.class)
    public void testDeleteRequest(String endpoint) {
        String url = baseUri + endpoint;
        Response deleteResponse = APIRequestSender.delete(url, new HashMap<>(), username, password);
        System.out.println("DELETE Response: " + deleteResponse.asString());
    }
}
