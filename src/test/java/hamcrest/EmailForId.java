package hamcrest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class EmailForId {

    @Test
    public void getEmailForId() {
        // Send a GET request and retrieve the response
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        // Convert the response body to a JSONObject
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        // Get the 'data' array from the response body
        JSONArray dataArray = responseBody.getJSONArray("data");

        // Specify the desired ID
        int desiredId = 9;

        // Loop through the 'data' array to find the desired ID and print its associated email
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            int id = dataObject.getInt("id");
            if (id == desiredId) {
                String email = dataObject.getString("email");
                System.out.println("Email for ID " + id + ": " + email);
                break;
            }
        }
    }
}
