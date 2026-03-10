package org.example.bai2;

import org.example.base.ParallelBaseTest;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParallelCartTest extends ParallelBaseTest {

    @Test(description = "Parallel - Add item to cart")
    public void testParallelAddItemToCart() {
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        InventoryPage inventoryPage = new InventoryPage(getDriver(), getWait());

        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();

        Assert.assertEquals(inventoryPage.getCartBadgeText(), "1",
                "Parallel cart badge không đúng");
    }

    @Test(description = "Parallel - Open cart")
    public void testParallelOpenCart() {
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        InventoryPage inventoryPage = new InventoryPage(getDriver(), getWait());
        CartPage cartPage = new CartPage(getDriver(), getWait());

        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        Assert.assertTrue(cartPage.isCartDisplayed(),
                "Trang cart không hiển thị khi chạy parallel");
    }
}