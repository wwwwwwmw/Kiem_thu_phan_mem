package dtm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name") private WebElement userNameField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(id = "login-button") private WebElement loginButton;
    @FindBy(css = "h3[data-test='error']") private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dangNhap(String user, String pass) {
        if (user != null) userNameField.sendKeys(user);
        if (pass != null) passwordField.sendKeys(pass);
        loginButton.click();
    }

    public String layThongBaoLoi() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isDangTrangSanPham() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
}