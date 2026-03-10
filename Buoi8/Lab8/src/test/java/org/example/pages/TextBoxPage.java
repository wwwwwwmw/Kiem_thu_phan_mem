package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {

    private final WebDriver driver;

    @FindBy(id = "userName")
    private WebElement nameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressField;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressField;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(id = "output")
    private WebElement outputSection;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://demoqa.com/text-box");
    }

    public void fillAndSubmit(String name, String email, String currentAddress, String permanentAddress) {
        clearAndType(nameField, name);
        clearAndType(emailField, email);
        clearAndType(currentAddressField, currentAddress);
        clearAndType(permanentAddressField, permanentAddress);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    }

    private void clearAndType(WebElement element, String value) {
        element.clear();
        if (value != null) {
            element.sendKeys(value);
        }
    }

    public boolean isOutputDisplayed() {
        try {
            return outputSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNameValue() {
        return outputSection.getText();
    }

    public String getEmailClass() {
        return emailField.getAttribute("class");
    }

    public boolean isEmailInvalidStyled() {
        String cls = getEmailClass();
        return cls != null && cls.contains("field-error");
    }
}