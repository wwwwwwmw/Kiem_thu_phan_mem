package org.example.bai6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.TextBoxPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextBoxWhiteBoxTest {

    private WebDriver driver;
    private TextBoxPage textBoxPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        textBoxPage = new TextBoxPage(driver);
        textBoxPage.open();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "TC01 - Du lieu hop le -> hien thi output")
    public void testValidInput_ShouldDisplayOutput() {
        textBoxPage.fillAndSubmit(
                "Nguyen Van A",
                "vana@gmail.com",
                "123 Le Loi, HCM",
                "456 Tran Hung Dao, HCM"
        );

        Assert.assertTrue(
                textBoxPage.isOutputDisplayed(),
                "Output phai hien thi khi du lieu hop le."
        );
    }

    @Test(description = "TC02 - Email sai dinh dang -> khong hien thi output")
    public void testInvalidEmail_ShouldNotDisplayOutput() {
        textBoxPage.fillAndSubmit(
                "Nguyen Van A",
                "vana-gmail.com",
                "123 Le Loi, HCM",
                "456 Tran Hung Dao, HCM"
        );

        Assert.assertFalse(
                textBoxPage.isOutputDisplayed(),
                "Output khong duoc hien thi khi email sai dinh dang."
        );

        Assert.assertTrue(
                textBoxPage.isEmailInvalidStyled(),
                "Email field phai bi danh dau loi."
        );
    }

    @Test(description = "TC03 - Ten rong, email hop le -> van submit duoc")
    public void testEmptyName_ValidEmail() {
        textBoxPage.fillAndSubmit(
                "",
                "test@gmail.com",
                "123 Le Loi, HCM",
                "456 Tran Hung Dao, HCM"
        );

        Assert.assertTrue(
                textBoxPage.isOutputDisplayed(),
                "Form van hien thi output khi ten rong va email hop le."
        );
    }

    @Test(description = "TC04 - Tat ca rong -> van submit duoc, khong crash")
    public void testAllEmptyFields() {
        textBoxPage.fillAndSubmit(
                "",
                "",
                "",
                ""
        );

        Assert.assertTrue(
                textBoxPage.isOutputDisplayed(),
                "Trang khong duoc loi khi submit tat ca rong."
        );
    }

    @Test(description = "TC05 - Name chi gom khoang trang")
    public void testWhitespaceName() {
        textBoxPage.fillAndSubmit(
                "   ",
                "white@gmail.com",
                "Dia chi hien tai",
                "Dia chi thuong tru"
        );

        Assert.assertTrue(
                textBoxPage.isOutputDisplayed(),
                "He thong van xu ly du lieu name la khoang trang."
        );
    }

    @Test(description = "TC06 - Name co ky tu dac biet")
    public void testSpecialCharacterName() {
        textBoxPage.fillAndSubmit(
                "@@@###",
                "special@gmail.com",
                "123 ABC",
                "456 XYZ"
        );

        Assert.assertTrue(
                textBoxPage.isOutputDisplayed(),
                "Name co ky tu dac biet van duoc submit."
        );
    }

    @Test(description = "TC07 - Dia chi rat dai")
    public void testLongAddress() {
        String longAddress = "A".repeat(300);

        textBoxPage.fillAndSubmit(
                "Long Address User",
                "long@gmail.com",
                longAddress,
                longAddress
        );

        Assert.assertTrue(
                textBoxPage.isOutputDisplayed(),
                "Dia chi dai van phai duoc xu ly."
        );
    }
}