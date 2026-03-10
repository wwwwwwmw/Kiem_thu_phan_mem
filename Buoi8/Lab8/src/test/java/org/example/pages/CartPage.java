package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartList = By.className("cart_list");
    private final By backpackItem = By.id("item_4_title_link");
    private final By bikeLightItem = By.id("item_0_title_link");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isCartDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartList)).isDisplayed();
    }

    public boolean isBackpackDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backpackItem)).isDisplayed();
    }

    public boolean isBikeLightDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bikeLightItem)).isDisplayed();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }
}