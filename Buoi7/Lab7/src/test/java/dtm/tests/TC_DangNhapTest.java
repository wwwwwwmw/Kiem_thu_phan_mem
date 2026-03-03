package dtm.tests;

import dtm.base.BaseTest;
import dtm.data.DangNhapData;
import dtm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DangNhapTest extends BaseTest {

    @Test(dataProvider = "du_lieu_dang_nhap", dataProviderClass = DangNhapData.class,
            description = "Kiểm thử đăng nhập với nhiều bộ dữ liệu")
    public void kiemThuDangNhap(String username, String password, String ketQuaMongDoi, String moTa) {
        getDriver().get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.dangNhap(username, password);

        switch (ketQuaMongDoi) {
            case "THÀNH CÔNG":
                Assert.assertTrue(loginPage.isDangTrangSanPham(), "Lỗi: " + moTa);
                break;
            case "BỊ KHÓA":
                Assert.assertTrue(loginPage.layThongBaoLoi().contains("locked out"), "Lỗi: " + moTa);
                break;
            case "SAI THÔNG TIN":
                Assert.assertTrue(loginPage.layThongBaoLoi().contains("do not match"), "Lỗi: " + moTa);
                break;
            case "TRƯỜNG TRỐNG":
                Assert.assertTrue(loginPage.layThongBaoLoi().contains("is required"), "Lỗi: " + moTa);
                break;
        }
    }
}