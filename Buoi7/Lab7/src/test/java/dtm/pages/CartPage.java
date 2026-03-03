package dtm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // Các element trên trang giỏ hàng theo đặc tả
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Chuyển sang trang Checkout Step 1
    public void clickCheckout() {
        checkoutButton.click();
    }

    // Quay lại trang danh sách sản phẩm
    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    // Kiểm tra số lượng item thực tế trong giỏ
    public int laySoLuongSanPhamTrongGio() {
        return cartItems.size();
    }

    // Xóa một sản phẩm cụ thể khỏi giỏ dựa trên tên
    public void xoaSanPham(String tenSanPham) {
        String xpath = "//div[text()='" + tenSanPham + "']/ancestor::div[@class='cart_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }

    // Kiểm tra xem giỏ hàng có trống không
    public boolean isGioHangTrong() {
        return cartItems.isEmpty();
    }
}