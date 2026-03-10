package org.example.bai2;

import org.example.base.BaseTest;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestBai2 extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Đăng nhập thành công")
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver, wait);
        InventoryPage inventoryPage = new InventoryPage(driver, wait);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Đăng nhập thành công nhưng không chuyển sang inventory.html");
        Assert.assertTrue(inventoryPage.isInventoryDisplayed(),
                "Trang inventory không hiển thị sau khi đăng nhập");
    }

    @Test(groups = {"regression"}, description = "Sai mật khẩu")
    public void testLoginWrongPassword() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Không hiển thị thông báo lỗi khi sai mật khẩu");
        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username and password do not match"),
                "Thông báo lỗi sai mật khẩu không đúng");
    }
}