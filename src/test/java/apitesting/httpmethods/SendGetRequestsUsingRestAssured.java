package apitesting.httpmethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class SendGetRequestsUsingRestAssured {
	public static void main(String[] args) {
		// Send a GET request with basic authentication and form parameters
		Response response = given()
				.auth().basic("sk_test_tR3PYbcVNZZ796tH88S4VQ2u", "")
				.formParams("limit", 3)
				.get("https://api.stripe.com/v1/customers");

		// Print the response body
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		// Print the response code
		System.out.println("Response code: " + response.statusCode());

		// Set the content type using different approaches
		given().contentType(ContentType.JSON);
		given().contentType("application/json");
	}
}
