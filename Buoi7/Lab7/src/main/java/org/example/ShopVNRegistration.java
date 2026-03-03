package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ShopVNRegistration {

    // Các trường dữ liệu trên Form
    private String hoVaTen;
    private String tenDangNhap;
    private String email;
    private String soDienThoai;
    private String matKhau;
    private String xacNhanMatKhau;
    private LocalDate ngaySinh; // Không bắt buộc
    private String gioiTinh;    // Không bắt buộc
    private String maGioiThieu; // Không bắt buộc
    private boolean dongYDieuKhoan;

    // Constructors
    public ShopVNRegistration() {}

    // Getters and Setters (Sinh viên tự generate thêm nếu cần, ở đây viết gọn)
    public void setHoVaTen(String hoVaTen) { this.hoVaTen = hoVaTen; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }
    public void setEmail(String email) { this.email = email; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
    public void setXacNhanMatKhau(String xacNhanMatKhau) { this.xacNhanMatKhau = xacNhanMatKhau; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setMaGioiThieu(String maGioiThieu) { this.maGioiThieu = maGioiThieu; }
    public void setDongYDieuKhoan(boolean dongYDieuKhoan) { this.dongYDieuKhoan = dongYDieuKhoan; }

    /**
     * Hàm giả lập hành động click nút [Đăng ký]
     * Thực hiện validate toàn bộ dữ liệu dựa trên đặc tả
     * @return Danh sách các lỗi (nếu rỗng tức là form hợp lệ)
     */
    public List<String> validateForm() {
        List<String> errors = new ArrayList<>();

        // 1. Validate Họ và tên
        // Chỉ chứa chữ cái (có dấu hoặc không dấu) và dấu cách. Độ dài 2-50 ký tự.
        if (hoVaTen == null || !Pattern.matches("^[\\p{L} \\s]{2,50}$", hoVaTen)) {
            errors.add("Họ và tên không hợp lệ (bắt buộc, 2-50 ký tự chữ).");
        }

        // 2. Validate Tên đăng nhập
        // Chữ thường, số, dấu gạch dưới. Bắt đầu bằng chữ cái. Dài 5-20 ký tự.
        if (tenDangNhap == null || !Pattern.matches("^[a-z][a-z0-9_]{4,19}$", tenDangNhap)) {
            errors.add("Tên đăng nhập không hợp lệ (bắt buộc, 5-20 ký tự, bắt đầu bằng chữ, chỉ chứa chữ thường, số, _).");
        }

        // 3. Validate Email
        // Email chuẩn, giả sử regex cơ bản cho RFC 5322
        if (email == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            errors.add("Email không hợp lệ.");
        }

        // 4. Validate Số điện thoại
        // Số VN: bắt đầu bằng 0, gồm 10 chữ số liên tiếp
        if (soDienThoai == null || !Pattern.matches("^0\\d{9}$", soDienThoai)) {
            errors.add("Số điện thoại không hợp lệ (bắt buộc, 10 số, bắt đầu bằng 0).");
        }

        // 5. Validate Mật khẩu
        // 8-32 ký tự. Phải có ít nhất: 1 chữ hoa, 1 chữ thường, 1 chữ số, 1 ký tự đặc biệt
        if (matKhau == null || !Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,32}$", matKhau)) {
            errors.add("Mật khẩu không hợp lệ (bắt buộc, 8-32 ký tự, đủ chữ hoa, thường, số, ký tự đặc biệt).");
        }

        // 6. Validate Xác nhận mật khẩu
        if (xacNhanMatKhau == null || !xacNhanMatKhau.equals(matKhau)) {
            errors.add("Xác nhận mật khẩu không khớp.");
        }

        // 7. Validate Ngày sinh (Không bắt buộc, nhưng nếu nhập phải từ 16 đến dưới 100 tuổi)
        if (ngaySinh != null) {
            int age = Period.between(ngaySinh, LocalDate.now()).getYears();
            if (age < 16 || age >= 100) {
                errors.add("Ngày sinh không hợp lệ (phải từ 16 đến dưới 100 tuổi).");
            }
        }

        // 8. Validate Giới tính (Không bắt buộc)
        if (gioiTinh != null && !gioiTinh.equals("Nam") && !gioiTinh.equals("Nữ") && !gioiTinh.equals("Không muốn tiết lộ")) {
            errors.add("Giới tính không hợp lệ.");
        }

        // 9. Validate Mã giới thiệu (Không bắt buộc, 8 ký tự chữ hoa và số)
        if (maGioiThieu != null && !maGioiThieu.isEmpty()) {
            if (!Pattern.matches("^[A-Z0-9]{8}$", maGioiThieu)) {
                errors.add("Mã giới thiệu không hợp lệ (8 ký tự chữ hoa và số).");
            }
        }

        // 10. Validate Đồng ý Điều khoản
        if (!dongYDieuKhoan) {
            errors.add("Bạn phải đồng ý với điều khoản sử dụng.");
        }

        return errors;
    }
}