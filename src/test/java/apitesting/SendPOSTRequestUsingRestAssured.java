package apitesting;

import static io.restassured.RestAssured.given;


import io.restassured.response.Response;

public class SendPOSTRequestUsingRestAssured {

	public static void main(String[] args) {


		Response response = given().auth().basic("sk_test_tR3PYbcVNZZ796tH88S4VQ2u", "")
		.formParam("name", "Shivaji Bajpai").formParam("email", "shivaji@gmail.com")
		.formParam("description", "This is new POST request from Rest Assured API").post("https://api.stripe.com/v1/customers");

		response.prettyPrint();
		
	}
	

}
