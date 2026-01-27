import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Bai5 {

    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-ENDTJTR;databaseName=KhachHang;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    private static final String USER = "";
    private static final String PASS = "";

    public static String dangKy(Bai5_KhachHang kh) {

        if (kh.maKH == null || !kh.maKH.matches("^[a-zA-Z0-9]{6,10}$")) return "Lỗi: Mã khách hàng không hợp lệ (6-10 ký tự, chỉ chữ số)";
        if (kh.hoTen == null || kh.hoTen.length() < 5 || kh.hoTen.length() > 50) return "Lỗi: Họ tên phải từ 5-50 ký tự";
        if (kh.email == null || !kh.email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) return "Lỗi: Email không hợp lệ";
        if (kh.sdt == null || !kh.sdt.matches("^0\\d{9,11}$")) return "Lỗi: SĐT phải bắt đầu bằng 0 và có 10-12 số";
        if (kh.diaChi == null || kh.diaChi.isEmpty() || kh.diaChi.length() > 255) return "Lỗi: Địa chỉ không hợp lệ";
        if (kh.matKhau == null || kh.matKhau.length() < 8) return "Lỗi: Mật khẩu quá ngắn";
        if (!kh.matKhau.equals(kh.xacNhanMK)) return "Lỗi: Mật khẩu xác nhận không khớp";


        if (kh.ngaySinh != null && !kh.ngaySinh.isEmpty()) {
            try {
                LocalDate dob = LocalDate.parse(kh.ngaySinh, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (Period.between(dob, LocalDate.now()).getYears() < 18) return "Lỗi: Bạn chưa đủ 18 tuổi";
            } catch (Exception e) { return "Lỗi: Định dạng ngày sinh sai (yyyy-MM-dd)"; }
        }

        if (!kh.dongYDieuKhoan) return "Lỗi: Bạn chưa đồng ý điều khoản";


        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            PreparedStatement psCheck = conn.prepareStatement("SELECT COUNT(*) FROM KhachHang WHERE Email = ?");
            psCheck.setString(1, kh.email);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) return "Lỗi: Email đã tồn tại";


            PreparedStatement psCheckMa = conn.prepareStatement("SELECT COUNT(*) FROM KhachHang WHERE MaKH = ?");
            psCheckMa.setString(1, kh.maKH);
            ResultSet rsMa = psCheckMa.executeQuery();
            if (rsMa.next() && rsMa.getInt(1) > 0) return "Lỗi: Mã khách hàng đã tồn tại";


            String insertSql = "INSERT INTO KhachHang (MaKH, HoTen, Email, SDT, DiaChi, MatKhau, NgaySinh, GioiTinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(insertSql);
            psInsert.setString(1, kh.maKH);
            psInsert.setString(2, kh.hoTen);
            psInsert.setString(3, kh.email);
            psInsert.setString(4, kh.sdt);
            psInsert.setString(5, kh.diaChi);
            psInsert.setString(6, kh.matKhau);

            if (kh.ngaySinh != null && !kh.ngaySinh.isEmpty()) psInsert.setDate(7, java.sql.Date.valueOf(kh.ngaySinh));
            else psInsert.setNull(7, java.sql.Types.DATE);


            psInsert.setString(8, kh.gioiTinh);

            psInsert.executeUpdate();
            return "Đăng ký tài khoản thành công!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi kết nối Database: " + e.getMessage();
        }
    }
}