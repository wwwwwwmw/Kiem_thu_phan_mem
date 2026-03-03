package dtm.tests;

import dtm.base.BaseTest;
import dtm.data.BlogspotData;
import dtm.pages.BlogspotPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_BlogspotTest extends BaseTest {

    @Test(dataProvider = "data_blogspot", dataProviderClass = BlogspotData.class,
            description = "Kiểm thử 27 kịch bản trên form Blogspot")
    public void testBlogspotForm(String name, String email, String phone, String addr,
                                 String gender, String day, String country,
                                 String expected, String desc) {

        // Truy cập trang web
        getDriver().get("https://testautomationpractice.blogspot.com/");
        BlogspotPage page = new BlogspotPage(getDriver());

        // Thực hiện hành động
        page.dienThongTinCoBan(name, email, phone, addr);
        page.chonGioiTinh(gender);
        page.chonNgayTrongTuan(day);
        page.chonQuocGia(country);
        page.cuonXuongCuoi();

        // Kiểm tra logic đơn giản (Vì trang này không có nút Submit thực thụ)
        if (expected.equals("PASS")) {
            // Kiểm tra xem dữ liệu đã được nhập vào các field chưa
            Assert.assertFalse(getDriver().findElement(org.openqa.selenium.By.id("name")).getAttribute("value").isEmpty(),
                    "Lỗi tại: " + desc);
        } else {
            // Đối với các case FAIL, ta ghi log hoặc assert theo điều kiện lỗi cụ thể
            System.out.println("[INFO] Chạy kịch bản lỗi mong đợi: " + desc);
        }
    }
}