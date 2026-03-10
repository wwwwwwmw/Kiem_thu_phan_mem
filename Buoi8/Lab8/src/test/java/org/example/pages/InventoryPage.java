package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inventoryContainer = By.id("inventory_container");
    private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isInventoryDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBackpackButton)).click();
    }

    public void addBikeLightToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBikeLightButton)).click();
    }

    public String getCartBadgeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }
}