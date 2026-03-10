package org.example.bai2;

import org.example.base.BaseTest;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Override
    @Parameters({"baseUrl"})
    public void setUp(@Optional("https://www.saucedemo.com/") String baseUrl) {
        super.setUp(baseUrl);

        LoginPage loginPage = new LoginPage(driver, wait);
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        CartPage cartPage = new CartPage(driver, wait);

        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();
        cartPage.clickCheckout();
    }

    @Test(groups = {"smoke", "regression"}, description = "Điền thông tin checkout hợp lệ")
    public void testCheckoutInformationSuccess() {
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.enterInformation("Nguyen", "Van A", "700000");
        checkoutPage.clickContinue();

        Assert.assertFalse(
                checkoutPage.isErrorDisplayed(),
                "Vẫn còn thông báo lỗi nên chưa sang được trang overview."
        );

        Assert.assertTrue(
                checkoutPage.isOverviewDisplayed(),
                "Không chuyển sang trang checkout overview sau khi nhập thông tin hợp lệ."
        );
    }

    @Test(groups = {"regression"}, description = "Bỏ trống first name")
    public void testCheckoutEmptyFirstName() {
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.enterInformation("", "Van A", "700000");
        checkoutPage.clickContinue();

        Assert.assertTrue(
                checkoutPage.isErrorDisplayed(),
                "Không hiển thị thông báo lỗi khi bỏ trống first name."
        );

        Assert.assertTrue(
                checkoutPage.getErrorMessageText().contains("First Name is required"),
                "Thông báo lỗi khi bỏ trống first name không đúng."
        );
    }
}