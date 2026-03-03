package dtm.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    private WebDriver driver;

    // Khai báo 4 trường và nút Submit theo ID của trang demoqa
    @FindBy(id = "userName") private WebElement fullNameField;
    @FindBy(id = "userEmail") private WebElement emailField;
    @FindBy(id = "currentAddress") private WebElement currentAddressField;
    @FindBy(id = "permanentAddress") private WebElement permanentAddressField;
    @FindBy(id = "submit") private WebElement submitBtn;

    // Khu vực hiển thị kết quả sau khi Submit
    @FindBy(id = "output") private WebElement outputBox;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dienThongTin(String name, String email, String curAddr, String perAddr) {
        fullNameField.sendKeys(name);
        emailField.sendKeys(email);
        currentAddressField.sendKeys(curAddr);
        permanentAddressField.sendKeys(perAddr);
    }

    public void clickSubmit() {
        // Trang demoqa thường có quảng cáo che khuất nút, nên dùng JS để click cho chắc chắn
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    }

    public boolean isOutputDisplayed() {
        try {
            return outputBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}