package org.example.bai1;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TitleTest extends BaseTest {

    @Test(description = "Kiểm thử tiêu đề trang chủ")
    public void testTitle() {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "Tiêu đề trang không đúng.");
    }

    @Test(description = "Kiểm thử URL trang chủ")
    public void testURL() {
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("saucedemo"),
                "URL hiện tại không hợp lệ hoặc không chứa từ khóa 'saucedemo'.");
    }

    @Test(description = "Kiểm thử page source có chứa nội dung Swag Labs")
    public void testPageSourceContainsText() {
        String pageSource = driver.getPageSource();

        Assert.assertTrue(pageSource.contains("Swag Labs"),
                "Page source không chứa nội dung mong đợi 'Swag Labs'.");
    }

    @Test(description = "Kiểm thử form đăng nhập có hiển thị")
    public void testLoginFormDisplayed() {
        LoginPage loginPage = new LoginPage(driver, wait);

        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Form đăng nhập không hiển thị đầy đủ.");
    }

    @Test(description = "Kiểm thử logo đăng nhập hiển thị")
    public void testLoginLogoDisplayed() {
        LoginPage loginPage = new LoginPage(driver, wait);

        Assert.assertTrue(loginPage.isLogoDisplayed(),
                "Logo đăng nhập không hiển thị.");
    }

    @Test(description = "Kiểm thử login box hiển thị")
    public void testLoginBoxDisplayed() {
        LoginPage loginPage = new LoginPage(driver, wait);

        Assert.assertTrue(loginPage.isLoginBoxDisplayed(),
                "Khung đăng nhập không hiển thị.");
    }
}