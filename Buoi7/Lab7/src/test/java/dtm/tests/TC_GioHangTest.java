package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.CartPage;
import dtm.pages.CheckoutPage;
import dtm.pages.InventoryPage;
import dtm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC_GioHangTest extends BaseTest {
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void chuanBi() {
        // Đăng nhập trước mỗi test
        getDriver().get("https://www.saucedemo.com");
        new LoginPage(getDriver()).dangNhap("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
    }

    @Test(groups={"smoke"}, description="TC_CART_001: Thêm 1 sản phẩm - badge = 1")
    public void themMotSanPham() {
        inventoryPage.themSanPhamTheoTen("Sauce Labs Backpack");
        Assert.assertEquals(inventoryPage.laySoLuongBadge(), 1);
    }

    @Test(groups={"smoke"}, description="TC_CART_002: Thêm 3 sản phẩm - badge = 3")
    public void them3SanPham() {
        inventoryPage.themNSanPhamDauTien(3);
        Assert.assertEquals(inventoryPage.laySoLuongBadge(), 3);
    }

    @Test(groups={"regression"}, description="TC_CART_003: Xoá hết - giỏ trống")
    public void xoaHetSanPham() {
        inventoryPage.themNSanPhamDauTien(2);
        inventoryPage.clickCart();
        cartPage.xoaSanPham("Sauce Labs Backpack");
        cartPage.xoaSanPham("Sauce Labs Bike Light");
        Assert.assertEquals(inventoryPage.laySoLuongBadge(), 0);
    }

    @Test(groups={"regression"}, description="TC_CART_004: Sort giá tăng dần - đúng thứ tự")
    public void sortGiaTangDan() {
        inventoryPage.sortSanPham("lohi");
        List<Double> prices = inventoryPage.layDanhSachGiaSanPham();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Assert.assertEquals(prices, sortedPrices);
    }

    @Test(groups={"regression"}, description="TC_CART_010: Kiểm tra tính toán tổng tiền chính xác")
    public void kiemTraTongTien() {
        // 1. Thêm ít nhất 3 sản phẩm có giá khác nhau vào giỏ hàng
        inventoryPage.themSanPhamTheoTen("Sauce Labs Backpack");    // $29.99
        inventoryPage.themSanPhamTheoTen("Sauce Labs Bike Light");   // $9.99
        inventoryPage.themSanPhamTheoTen("Sauce Labs Bolt T-Shirt"); // $15.99

        // 2. Chuyển sang trang Checkout Step 2 (Overview)
        inventoryPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.nhapThongTin("Nguyen", "Van A", "700000"); // Hoàn thành Step 1

        // 3. Lấy các giá trị hiển thị trên trang (Item total, Tax, Total)
        double subtotalFromWeb = checkoutPage.laySubtotal();
        double taxFromWeb = checkoutPage.layTax();
        double totalFromWeb = checkoutPage.layTotal();

        // 4. Tính toán giá trị mong đợi theo công thức đặc tả
        // Item total = tổng giá từng item (29.99 + 9.99 + 15.99)
        double expectedSubtotal = 55.97;

        // Tax = Item total * 8%
        double expectedTax = expectedSubtotal * 0.08;

        // Total = Item total + Tax
        double expectedTotal = expectedSubtotal + taxFromWeb;

        // 5. Assert kiểm tra tính chính xác với delta 0.01
        // Kiểm tra Item Total (Subtotal)
        Assert.assertEquals(subtotalFromWeb, expectedSubtotal, 0.01, "Lỗi: Item total trên web không khớp với tổng giá sản phẩm!");

        // Kiểm tra Tax (8%)
        Assert.assertTrue(Math.abs(taxFromWeb - (subtotalFromWeb * 0.08)) < 0.01,
                "Lỗi: Thuế tính sai (Mong đợi: " + (subtotalFromWeb * 0.08) + ", Thực tế: " + taxFromWeb + ")");

        // Kiểm tra Total (Subtotal + Tax)
        Assert.assertTrue(Math.abs(totalFromWeb - (subtotalFromWeb + taxFromWeb)) < 0.01,
                "Lỗi: Tổng thanh toán cuối cùng tính sai!");

        System.out.println("[PASS] Kiểm tra tính toán: Subtotal=" + subtotalFromWeb + ", Tax=" + taxFromWeb + ", Total=" + totalFromWeb);
    }
}