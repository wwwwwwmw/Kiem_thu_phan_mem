package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DemoQATextBoxTest extends BaseTest {

    @Test(description = "Câu 7: Kiểm thử Form tại DemoQA")
    public void testTextBoxForm() {
        // 1. Truy cập trang web
        getDriver().get("https://demoqa.com/text-box");
        TextBoxPage textBoxPage = new TextBoxPage(getDriver());

        // 2. Thực hiện hành động điền form
        textBoxPage.dienThongTin(
                "Sinh Vien Tu Dong Hoa",
                "sinhvien@gmail.com",
                "99 To Hien Thanh, Da Nang",
                "Dormitory Area A"
        );

        // 3. Nhấn Submit
        textBoxPage.clickSubmit();

        // 4. Kiểm tra kết quả hiển thị (Assert)
        Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Lỗi: Form không hiển thị kết quả sau khi nhấn Submit!");

        // In log ra console để theo dõi
        System.out.println("[PASS] Đã hoàn thành Câu 7 - DemoQA Text Box");
    }
}