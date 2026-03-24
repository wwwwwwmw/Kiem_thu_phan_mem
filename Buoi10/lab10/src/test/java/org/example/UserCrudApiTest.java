package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserCrudApiTest extends ApiBaseTest {


    private int newlyCreatedId;

    @Test(priority = 1, description = "POST: Tạo user mới sử dụng POJO")
    public void testCreateUser() {

        UserRequest newUserData = new UserRequest("Tri Anh", "Software Engineer");

        UserResponse responseBody = given(requestSpec)
                .body(newUserData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract().as(UserResponse.class);


        Assert.assertEquals(responseBody.getName(), "Tri Anh", "Tên không khớp!");
        Assert.assertEquals(responseBody.getJob(), "Software Engineer", "Job không khớp!");
        Assert.assertNotNull(responseBody.getId(), "ID không được null!");


        newlyCreatedId = responseBody.getId();
        System.out.println("Đã tạo thành công User với ID: " + newlyCreatedId);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser", description = "GET: Xác nhận user vừa tạo")
    public void testGetCreatedUserVerify() {

        System.out.println("Đang verify lại User ID: " + newlyCreatedId);

        given(requestSpec)
                .when()
                .get("/users/" + newlyCreatedId)
                .then()
                .spec(responseSpec)
                .statusCode(404);
    }

    @Test(priority = 3, description = "PUT: Cập nhật toàn bộ user (giả sử update user số 1)")
    public void testUpdateUserFull() {
        UserRequest updateData = new UserRequest("Tri Anh Pro", "Senior Automation");

        given(requestSpec)
                .body(updateData)
                .when()
                .put("/users/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("name", equalTo("Tri Anh Pro"))
                .body("job", equalTo("Senior Automation"));
    }

    @Test(priority = 4, description = "PATCH: Cập nhật một phần (chỉ update job)")
    public void testPatchUserPartial() {

        String patchPayload = "{\"job\": \"Project Manager\"}";

        given(requestSpec)
                .body(patchPayload)
                .when()
                .patch("/users/1")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("job", equalTo("Project Manager"));
    }

    @Test(priority = 5, description = "DELETE: Xóa user")
    public void testDeleteUser() {
        given(requestSpec)
                .when()
                .delete("/users/1")
                .then()
                .spec(responseSpec)

                .statusCode(200)
                .body("$", anEmptyMap());
    }
}