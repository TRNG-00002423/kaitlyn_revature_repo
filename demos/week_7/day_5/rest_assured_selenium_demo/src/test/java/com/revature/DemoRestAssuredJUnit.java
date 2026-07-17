package com.revature;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@DisplayName("Rest Assured with JUnit5 Integration")
public class DemoRestAssuredJUnit {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        System.out.println("==== Test Suite Started ====");
    }

    @AfterAll
    static void teardown() {
        RestAssured.reset();
        System.out.println("==== Test Suite Completed ====");
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.println("Running: " + testInfo.getDisplayName());
    }

    @AfterEach
    void afterEach() {
        // cleanup would go here
    }

    @ParameterizedTest(name = "GET /posts/{0} returns 200")
    @ValueSource(ints = { 1, 2, 3, 4, 5 })
    @DisplayName("Get multiple posts by ID")
    void getPosts_variousIds_return200(int postId) {
        given()
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .body("id", equalTo(postId));
    }

    @ParameterizedTest(name = "GET /users/{0} returns 200")
    @ValueSource(ints = { 1, 2, 3, 4, 5 })
    @DisplayName("Get multiple users by ID")
    void getUsers_variousIds_return200(int userId) {
        given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("email", containsString("@"));
    }

    @Test
    @DisplayName("Extract and assert with JUnit")
    void extractAndAssert_withJunit() {
        var response = given()
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode, "Status should be 200");

        int userCount = response.jsonPath().getList("$").size();
        Assertions.assertEquals(10, userCount, "Should have 10 users");
    }
}
