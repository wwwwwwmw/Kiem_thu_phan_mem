package dtm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;

    @FindBy(id = "checkout") private WebElement checkoutBtn;
    @FindBy(id = "first-name") private WebElement firstName;
    @FindBy(id = "last-name") private WebElement lastName;
    @FindBy(id = "postal-code") private WebElement zipCode;
    @FindBy(id = "continue") private WebElement continueBtn;

    // Step 2 elements [cite: 337]
    @FindBy(className = "summary_subtotal_label") private WebElement subtotalLabel;
    @FindBy(className = "summary_tax_label") private WebElement taxLabel;
    @FindBy(className = "summary_total_label") private WebElement totalLabel;
    @FindBy(id = "finish") private WebElement finishBtn;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCheckout() { checkoutBtn.click(); }

    public void nhapThongTin(String fname, String lname, String zip) {
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        zipCode.sendKeys(zip);
        continueBtn.click();
    }

    public double laySubtotal() { return Double.parseDouble(subtotalLabel.getText().replaceAll("[^0-9.]", "")); }
    public double layTax() { return Double.parseDouble(taxLabel.getText().replaceAll("[^0-9.]", "")); }
    public double layTotal() { return Double.parseDouble(totalLabel.getText().replaceAll("[^0-9.]", "")); }

    public void clickFinish() { finishBtn.click(); }
}