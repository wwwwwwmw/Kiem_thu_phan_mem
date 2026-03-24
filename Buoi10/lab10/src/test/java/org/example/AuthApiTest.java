package org.example;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthApiTest extends ApiBaseTest {


    @Test(description = "Test 1: Login thành công -> trả về 200 và có token")
    public void testLoginSuccess() {

        String payload = "{\"username\": \"emilys\", \"password\": \"emilyspass\"}";

        given(requestSpec)
                .baseUri("https://dummyjson.com")
                .basePath("")
                .body(payload)
                .when()
                .post("/auth/login")
                .then()
                .spec(responseSpec)
                .statusCode(200)

                .body("accessToken", not(emptyOrNullString()));
    }


    @DataProvider(name = "loginScenarios")
    public Object[][] loginScenarios() {
        return new Object[][] {

                {"emilys", "emilyspass", 200, null},
                {"emilys", "wrongpass", 400, "Invalid credentials"},
                {"wronguser", "emilyspass", 400, "Invalid credentials"},
                {"", "emilyspass", 400, "Username and password required"},
                {"emilys", "", 400, "Username and password required"}
        };
    }

    @Test(dataProvider = "loginScenarios", description = "Test Data-Driven cho nhiều kịch bản Login")
    public void testLoginScenarios(String username, String password, int expectedStatus, String expectedError) {

        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        System.out.println("--- Đang test case: User=" + username + " | Pass=" + password + " ---");

        ValidatableResponse response = given(requestSpec)
                .baseUri("https://dummyjson.com")
                .basePath("")
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                .spec(responseSpec)
                .statusCode(expectedStatus);

        if (expectedError != null) {
            response.body("message", equalTo(expectedError));
        }
    }
}