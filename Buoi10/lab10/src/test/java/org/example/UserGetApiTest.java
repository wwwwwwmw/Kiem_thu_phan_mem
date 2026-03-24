package org.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserGetApiTest extends ApiBaseTest {

    @Test(description = "Test 1: GET /users (Lấy danh sách, kiểm tra size > 0)")
    public void testGetAllUsers() {
        given(requestSpec)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(description = "Test 2: GET /users (Kiểm tra cấu trúc từng phần tử)")
    public void testUsersStructure() {
        given(requestSpec)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()))
                .body("email", everyItem(notNullValue()))

                .body("address.city", everyItem(notNullValue()));
    }

    @Test(description = "Test 3: GET /users/3 (Kiểm tra user cụ thể)")
    public void testGetUser3() {
        given(requestSpec)
                .when()
                .get("/users/3")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", equalTo(3))
                .body("email", containsString("@"))
                .body("name", not(emptyOrNullString()));
    }

    @Test(description = "Test 4: GET /users/9999 (Not Found)")
    public void testGetUserNotFound() {
        given(requestSpec)
                .when()
                .get("/users/9999")
                .then()
                .spec(responseSpec)
                .statusCode(404)
                .body("$", anEmptyMap());
    }
}