package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BlogspotPage {
    private WebDriver driver;

    @FindBy(id = "name") private WebElement nameField;
    @FindBy(id = "email") private WebElement emailField;
    @FindBy(id = "phone") private WebElement phoneField;
    @FindBy(id = "textarea") private WebElement addressField;
    @FindBy(id = "male") private WebElement maleRadio;
    @FindBy(id = "female") private WebElement femaleRadio;
    @FindBy(id = "country") private WebElement countryDropdown;

    public BlogspotPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dienThongTinCoBan(String name, String email, String phone, String address) {
        nameField.clear();
        if (!name.isEmpty()) nameField.sendKeys(name);

        emailField.clear();
        if (!email.isEmpty()) emailField.sendKeys(email);

        phoneField.clear();
        if (!phone.isEmpty()) phoneField.sendKeys(phone);

        addressField.clear();
        if (!address.isEmpty()) addressField.sendKeys(address);
    }

    public void chonGioiTinh(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            maleRadio.click();
        } else {
            femaleRadio.click();
        }
    }

    public void chonNgayTrongTuan(String day) {
        // Tìm checkbox theo ID dựa trên tên ngày (monday, tuesday...)
        WebElement dayCheckbox = driver.findElement(By.id(day.toLowerCase()));
        if (!dayCheckbox.isSelected()) {
            dayCheckbox.click();
        }
    }

    public void chonQuocGia(String country) {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(country);
    }

    public void cuonXuongCuoi() {
        // Xử lý scroll bằng JavascriptExecutor
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}