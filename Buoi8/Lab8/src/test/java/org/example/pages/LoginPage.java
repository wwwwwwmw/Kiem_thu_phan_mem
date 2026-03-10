package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    private final By loginLogo = By.className("login_logo");
    private final By loginBox = By.className("login-box");
    private final By inventoryContainer = By.id("inventory_container");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLoginFormDisplayed() {
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return user.isDisplayed() && pass.isDisplayed() && btn.isDisplayed();
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginLogo)).isDisplayed();
    }

    public boolean isLoginBoxDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginBox)).isDisplayed();
    }

    public void login(String username, String password) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement loginBtnElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        usernameElement.clear();
        passwordElement.clear();

        if (username != null) {
            usernameElement.sendKeys(username);
        }

        if (password != null) {
            passwordElement.sendKeys(password);
        }

        loginBtnElement.click();
    }

    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    public boolean isInventoryPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }
}