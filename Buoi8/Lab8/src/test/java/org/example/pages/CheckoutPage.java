package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    // Overview page
    private final By checkoutOverviewContainer = By.id("checkout_summary_container");
    private final By summaryInfo = By.className("summary_info");
    private final By summaryTotalLabel = By.className("summary_total_label");

    // Complete page
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterInformation(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput)).clear();

        if (firstName != null) {
            driver.findElement(firstNameInput).sendKeys(firstName);
        }
        if (lastName != null) {
            driver.findElement(lastNameInput).sendKeys(lastName);
        }
        if (postalCode != null) {
            driver.findElement(postalCodeInput).sendKeys(postalCode);
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isOverviewDisplayed() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(checkoutOverviewContainer),
                    ExpectedConditions.visibilityOfElementLocated(summaryInfo),
                    ExpectedConditions.visibilityOfElementLocated(summaryTotalLabel)
            )) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getCompleteText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}