package dtm.data;

import org.testng.annotations.DataProvider;

public class DangNhapData {
    @DataProvider(name = "du_lieu_dang_nhap")
    public Object[][] getData() {
        return new Object[][] {
                // { username, password, ketQuaMongDoi, moTa }
                {"standard_user", "secret_sauce", "THÀNH CÔNG", "Tài khoản hợp lệ"},
        {"locked_out_user", "secret_sauce", "BỊ KHÓA", "Tài khoản bị khóa"},
        {"wrong_user", "secret_sauce", "SAI THÔNG TIN", "Username không tồn tại"},
        {"standard_user", "wrong_pass", "SAI THÔNG TIN", "Sai mật khẩu"},
        {"", "secret_sauce", "TRƯỜNG TRỐNG", "Để trống username"},
        {"standard_user", "", "TRƯỜNG TRỐNG", "Để trống password"},
        {"", "", "TRƯỜNG TRỐNG", "Để trống cả hai"},
        {"standard_user", "secret_sauce", "SAI THÔNG TIN", ""},
        {"@#$%", "secret_sauce", "SAI THÔNG TIN", "Username chứa ký tự đặc biệt"}
        };
    }
}