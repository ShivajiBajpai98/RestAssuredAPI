package chaining;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Chain {

    @Test
    public void testRestAssured() {

        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // GET request to retrieve a list of posts
        RestAssured.given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log()
                .all();

        // POST request to create a new post
        RestAssured.given()
                .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log()
                .all();

        // PUT request to update an existing post
        RestAssured.given()
                .body("{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }")
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .log()
                .all();

        // DELETE request to delete a post
        RestAssured.given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .log()
                .all();

    }

}
