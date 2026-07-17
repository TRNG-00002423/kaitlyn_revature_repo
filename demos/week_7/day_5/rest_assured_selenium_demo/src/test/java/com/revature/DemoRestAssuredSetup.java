package com.revature;

//REST assured Setup and First Tests

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;

@DisplayName("REST Assured Setup Demo")
public class DemoRestAssuredSetup {

    // Setup

    @BeforeAll
    static void setup() {
        // configure base URI once for all tests
        // This is like setting baseUrl in Postman

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Optional: Enable loggin and debugging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @AfterAll
    static void teardown() {
        // Reset REST Assured to Defaults
        RestAssured.reset();
    }

    // First REST Assured Test
    @Test
    @DisplayName("First REST Assured Test - GET a post")
    void firstTest_getPost_returnSuccessfully() {

        given() // Setup
                .log().all() // Log the request (optional)
                .when() // Action
                .get("/posts/1")
                .then() // Validation
                .log().all() // log the response
                .statusCode(200);
    }

    @Test
    @DisplayName("Detailed given-when-then example")
    void givenWhenThen_detailed_breakdown() {
        given()
                // headers
                .header("Accept", "application/json")
                // query parameters
                .queryParam("userId", 1)
                .when()
                // HTTP method + endpoint
                .then()
                // status code validation
                .statusCode(200)
                // Content-Type validation
                .contentType(ContentType.JSON)
                // body validation using Hamcrest matchers
                .body("size()", greaterThan(0));
    }

    @Test
    @DisplayName("Validate response body fields")
    void validateBody_postFields_areCorrect() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                // validate specific fields using JSONPath
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .body("title", not(emptyString()))
                .body("body", containsString("qu"));
    }

    @Test
    @DisplayName("Validate user with nested objects")
    void validateBody_nestedObjects_accessedCorrectly() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))
                .body("email", containsString("@"))
                // nested object access
                .body("address.city", containsString("Gwenborough"))
                .body("address.geo.lat", notNullValue())
                // company nested object
                .body("company.name", equalTo("Romaguera-Crona"));
    }

}