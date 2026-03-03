package dtm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    // Khai báo các WebElement theo yêu cầu 
    @FindBy(className = "product_sort_container") private WebElement sortDropdown;
    @FindBy(css = ".btn_primary.btn_inventory") private List<WebElement> addToCartButtons;
    @FindBy(css = ".btn_secondary.btn_inventory") private List<WebElement> removeButtons;
    @FindBy(className = "shopping_cart_badge") private WebElement cartBadge;
    @FindBy(className = "shopping_cart_link") private WebElement cartLink;
    @FindBy(className = "inventory_item_name") private List<WebElement> itemNames;
    @FindBy(className = "inventory_item_price") private List<WebElement> itemPrices;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /** Thêm sản phẩm theo tên  */
    public void themSanPhamTheoTen(String tenSanPham) {
        String xpath = "//div[text()='" + tenSanPham + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }

    /** Thêm N sản phẩm đầu tiên trong danh sách */
    public void themNSanPhamDauTien(int n) {
        for (int i = 0; i < n && i < addToCartButtons.size(); i++) {
            addToCartButtons.get(i).click();
        }
    }

    /** Trả về số lượng badge giỏ hàng, 0 nếu không có  */
    public int laySoLuongBadge() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    /** Sort sản phẩm theo tùy chọn: 'az', 'za', 'lohi', 'hilo'  */
    public void sortSanPham(String option) {
        Select select = new Select(sortDropdown);
        select.selectByValue(option);
    }

    /** Lấy danh sách tên sản phẩm theo thứ tự hiển thị  */
    public List<String> layDanhSachTenSanPham() {
        List<String> names = new ArrayList<>();
        for (WebElement e : itemNames) names.add(e.getText());
        return names;
    }

    /** Lấy danh sách giá sản phẩm theo thứ tự hiển thị  */
    public List<Double> layDanhSachGiaSanPham() {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : itemPrices) {
            prices.add(Double.parseDouble(e.getText().replace("$", "")));
        }
        return prices;
    }

    public void clickCart() {
        cartLink.click();
    }
}