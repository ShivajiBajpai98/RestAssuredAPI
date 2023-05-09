package hamcrest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class EmailForId {

    @Test
    public void getEmailForId() {
        // Send GET request and retrieve response
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        // Convert response body to JSONObject
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        // Get the 'data' array from the response body
        JSONArray dataArray = responseBody.getJSONArray("data");

        // Loop through the array to find the desired ID and print its email value
       /* int desiredId = 9;
        for (int i = 0; i < dataArray.length(); i++)
        {
            JSONObject dataObject = dataArray.getJSONObject(i);
            int id = dataObject.getInt("id");
            if (id == desiredId)
            {
                String email = dataObject.getString("email");
                System.out.println("Email for ID " + id + ": " + email);
                break;

            }

        }*/

        // Loop through the array to find the desired name and print its email value
        String desiredName = "Michael";
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            String first_name = dataObject.getString("first_name");
            if (first_name.equals(desiredName)) {
                String email = dataObject.getString("email");
                System.out.println("Email for name " + desiredName + ": " + email);
                break;
            }
        }
    }
}
