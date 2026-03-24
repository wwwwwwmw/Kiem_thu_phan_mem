package org.example;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PerformanceApiTest extends ApiBaseTest {


    @DataProvider(name = "slaEndpoints")
    public Object[][] slaEndpoints() {
        return new Object[][] {

                {"GET", "https://jsonplaceholder.typicode.com", "/users", null, 200, 2000L},
                {"GET", "https://jsonplaceholder.typicode.com", "/users/2", null, 200, 1500L},
                {"POST", "https://jsonplaceholder.typicode.com", "/users", "{\"name\":\"Tri Anh\",\"job\":\"QA\"}", 201, 3000L},
                {"POST", "https://dummyjson.com", "/auth/login", "{\"username\":\"emilys\",\"password\":\"emilyspass\"}", 200, 2000L},
                {"DELETE", "https://jsonplaceholder.typicode.com", "/users/2", null, 200, 1000L}
        };
    }


    @Test(dataProvider = "slaEndpoints", description = "Giám sát SLA Performance cho các API chính")
    public void testSlaMonitoring(String method, String baseUri, String endpoint, String payload, int expectedStatus, long maxMs) {
        long startTime = System.currentTimeMillis();
        Response response = null;


        var request = given(requestSpec).baseUri(baseUri).basePath("");
        if (payload != null) {
            request.body(payload);
        }


        if (method.equals("GET")) {
            response = request.when().get(endpoint);
        } else if (method.equals("POST")) {
            response = request.when().post(endpoint);
        } else if (method.equals("DELETE")) {
            response = request.when().delete(endpoint);
        }


        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("[SLA Monitor] Gọi " + method + " " + endpoint + " | Thực tế: " + elapsed + "ms / SLA Cho phép: " + maxMs + "ms");


        response.then()
                .statusCode(expectedStatus)
                .time(lessThan(maxMs));


        if (endpoint.equals("/users") && method.equals("GET")) {
            response.then().body("size()", greaterThan(0));
        } else if (endpoint.equals("/users/2") && method.equals("GET")) {
            response.then().body("id", equalTo(2));
        } else if (endpoint.equals("/users") && method.equals("POST")) {
            response.then().body("id", notNullValue());
        } else if (endpoint.equals("/auth/login")) {
            response.then().body("accessToken", notNullValue());
        } else if (method.equals("DELETE")) {
            response.then().body("$", anEmptyMap());
        }
    }


    @Test(description = "Mô phỏng monitoring: Chạy API 10 lần tính Min/Max/Avg")
    public void testAverageResponseTime() {
        int runs = 10;
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = 0;

        System.out.println("\n--- Bắt đầu Monitoring " + runs + " lần API GET /users ---");

        for (int i = 1; i <= runs; i++) {
            long start = System.currentTimeMillis();

            given(requestSpec)
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .basePath("")
                    .when()
                    .get("/users")
                    .then()
                    .statusCode(200);

            long elapsed = System.currentTimeMillis() - start;
            totalTime += elapsed;

            if (elapsed < minTime) minTime = elapsed;
            if (elapsed > maxTime) maxTime = elapsed;

            System.out.println("Lần " + i + " mất: " + elapsed + "ms");
        }

        long avgTime = totalTime / runs;
        System.out.println("=> KẾT QUẢ MONITORING SAU " + runs + " LẦN:");
        System.out.println("Thời gian nhanh nhất (Min) : " + minTime + "ms");
        System.out.println("Thời gian chậm nhất (Max)  : " + maxTime + "ms");
        System.out.println("Thời gian trung bình (Avg) : " + avgTime + "ms\n");
    }
}