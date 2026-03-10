package org.example.bai2;

import org.example.base.ParallelBaseTest;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParallelLoginTest extends ParallelBaseTest {

    @Test(description = "Parallel - Login success")
    public void testParallelLoginSuccess() {
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        InventoryPage inventoryPage = new InventoryPage(getDriver(), getWait());

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory.html"),
                "Parallel login thất bại, không chuyển sang inventory");
        Assert.assertTrue(inventoryPage.isInventoryDisplayed(),
                "Inventory không hiển thị ở luồng song song");
    }
}