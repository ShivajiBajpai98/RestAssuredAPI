package apitesting;

import static io.restassured.RestAssured.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class SendGetRequetsUsingRestAssured {
	


	public static void main(String[] args) {
	
		Response response = given().auth().basic("sk_test_tR3PYbcVNZZ796tH88S4VQ2u", "").formParams("limit", 3)
							.get("https://api.stripe.com/v1/customers");
		
		
		//response.prettyPrint();
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		System.out.println("Response code --> "+response.statusCode());
		given().contentType(ContentType.JSON);
		given().contentType("application/json");

	}

	

	
}
