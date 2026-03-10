package org.example.bai2;

import org.example.base.BaseTest;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Override
    @Parameters({"baseUrl"})
    public void setUp(@Optional("https://www.saucedemo.com/") String baseUrl) {
        super.setUp(baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(groups = {"smoke", "regression"}, description = "Thêm 1 sản phẩm vào giỏ")
    public void testAddOneItemToCart() {
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        inventoryPage.addBackpackToCart();

        Assert.assertEquals(inventoryPage.getCartBadgeText(), "1",
                "Số lượng sản phẩm trong giỏ không đúng sau khi thêm 1 sản phẩm");
    }

    @Test(groups = {"regression"}, description = "Mở trang giỏ hàng và kiểm tra sản phẩm")
    public void testOpenCartAndVerifyItem() {
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        CartPage cartPage = new CartPage(driver, wait);

        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        Assert.assertTrue(cartPage.isCartDisplayed(),
                "Trang giỏ hàng không hiển thị");
        Assert.assertTrue(cartPage.isBackpackDisplayed(),
                "Sản phẩm Backpack không hiển thị trong giỏ");
    }
}