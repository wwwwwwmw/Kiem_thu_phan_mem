package org.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserSchemaValidationTest extends ApiBaseTest {

    @Test(description = "Test 1: Validate Schema danh sách user (Array)")
    public void testUserListSchemaValidation() {
        given(requestSpec)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-list-schema.json"));
    }

    @Test(description = "Test 2: Validate Schema một user cụ thể (Object lồng Object)")
    public void testSingleUserSchemaValidation() {
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

    @Test(description = "Test 3: Validate Schema khi tạo mới User")
    public void testCreateUserSchemaValidation() {
        UserRequest newUserData = new UserRequest("Tri Anh", "Software Engineer");

        given(requestSpec)
                .body(newUserData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)

                .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }

    @Test(description = "Test 4 (DEMO FAIL): Cố tình gửi thừa field để schema bắt lỗi")
    public void testCreateUserSchemaValidationFailDemo() {

        String payloadThuaDuLieu = "{\n" +
                "  \"name\": \"Tri Anh\",\n" +
                "  \"job\": \"QA\",\n" +
                "  \"extraField\": \"Du lieu la bi backend nhet them\"\n" +
                "}";

        System.out.println("--- ĐANG CHẠY TEST DEMO FAIL (MÀU ĐỎ LÀ ĐÚNG YÊU CẦU) ---");

        given(requestSpec)
                .body(payloadThuaDuLieu)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)

                .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }
}