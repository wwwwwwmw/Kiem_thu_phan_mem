package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ApiUiIntegrationTest {

    private WebDriver driver;
    private boolean isApiAlive = false;
    private String apiToken = null;

    @BeforeMethod
    public void apiPreconditionCheck() {
        System.out.println("--- BƯỚC 1: Gọi API Check (Precondition) ---");

        Response response = RestAssured.given()
                .baseUri("https://dummyjson.com")
                .header("Content-Type", "application/json")
                .body("{\"username\": \"emilys\", \"password\": \"emilyspass\"}")
                .post("/auth/login");

        if (response.statusCode() == 200) {
            isApiAlive = true;
            apiToken = response.jsonPath().getString("accessToken");

            System.out.println("=> API Đang sống! Token lấy được: " + apiToken.substring(0, 20) + "...");
        } else {
            isApiAlive = false;
            System.out.println("=> API Lỗi! Status: " + response.statusCode());
        }
    }

    @Test(description = "Luồng tích hợp: Kiểm tra API xong mới chạy UI SauceDemo")
    public void testSauceDemoE2EFlow() {
        System.out.println("--- BƯỚC 2: Quyết định chạy UI Test ---");

        if (!isApiAlive) {
            throw new SkipException("BỎ QUA UI TEST: API Precondition thất bại, hệ thống backend có thể đang lỗi!");
        }

        System.out.println("=> Tiền đề OK, bắt đầu mở trình duyệt chạy giao diện UI...");


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();


        System.out.println("--- BƯỚC 3: UI ACTION - Tự động đăng nhập SauceDemo ---");
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        System.out.println("--- BƯỚC 4: ASSERTION - Kiểm tra URL và Title ---");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "URL không chứa inventory");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Sai title trang");


        System.out.println("--- BƯỚC 5: UI ACTION - Thêm 2 sản phẩm vào giỏ ---");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();


        System.out.println("--- BƯỚC 6: ASSERTION - Kiểm tra badge giỏ hàng ---");
        String badgeText = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(badgeText, "2", "Số lượng trên icon giỏ hàng không đúng!");


        driver.findElement(By.className("shopping_cart_link")).click();
        int cartItemsCount = driver.findElements(By.className("cart_item")).size();
        Assert.assertEquals(cartItemsCount, 2, "Số lượng item trong giỏ hàng thực tế không đúng!");

        System.out.println("=> E2E Flow hoàn hảo!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
//            driver.quit();
        }
    }
}