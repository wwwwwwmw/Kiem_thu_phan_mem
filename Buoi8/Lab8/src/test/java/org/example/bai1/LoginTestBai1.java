package org.example.bai1;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestBai1 extends BaseTest {

    @Test(description = "Đăng nhập thành công với tài khoản chuẩn")
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Đăng nhập thành công nhưng không chuyển sang trang inventory.");

        Assert.assertTrue(loginPage.isInventoryPageDisplayed(),
                "Trang inventory không hiển thị sau khi đăng nhập thành công.");
    }

    @Test(description = "Đăng nhập sai mật khẩu")
    public void testLoginWrongPassword() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi khi nhập sai mật khẩu.");

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username and password do not match"),
                "Nội dung thông báo lỗi khi sai mật khẩu không đúng.");
    }

    @Test(description = "Để trống username")
    public void testLoginEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi khi để trống username.");

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username is required"),
                "Thông báo lỗi khi để trống username không đúng.");
    }

    @Test(description = "Để trống password")
    public void testLoginEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi khi để trống password.");

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Password is required"),
                "Thông báo lỗi khi để trống password không đúng.");
    }

    @Test(description = "Để trống cả username và password")
    public void testLoginEmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi khi để trống cả username và password.");

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username is required"),
                "Thông báo lỗi khi để trống cả username và password không đúng như mong đợi.");
    }

    @Test(description = "Đăng nhập bằng tài khoản bị khóa")
    public void testLoginLockedUser() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi với tài khoản bị khóa.");

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Sorry, this user has been locked out"),
                "Thông báo lỗi tài khoản bị khóa không đúng.");
    }

    @Test(description = "Đăng nhập bằng problem_user")
    public void testLoginProblemUser() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("problem_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "problem_user đăng nhập thành công nhưng không chuyển sang inventory.");
    }

    @Test(description = "Đăng nhập bằng performance_glitch_user")
    public void testLoginPerformanceGlitchUser() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("performance_glitch_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "performance_glitch_user đăng nhập thành công nhưng không chuyển sang inventory.");
    }
}