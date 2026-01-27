import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import static org.junit.Assert.assertEquals;

public class Bai5Test {


    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-ENDTJTR;databaseName=KhachHang;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "";
    private static final String PASS = "";


    @Before
    public void cleanUp() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            String sql = "DELETE FROM KhachHang WHERE MaKH IN ('userHappy', 'userTrung', 'userEmail', 'userTuoi', 'abc', 'user@#$')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }


    @Test
    public void testMaKH_HopLe() {

        Bai5_KhachHang kh = new Bai5_KhachHang("userHappy", "Nguyen Van A", "a@email.com", "0901234567", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Đăng ký tài khoản thành công!", Bai5.dangKy(kh));
    }

    @Test
    public void testMaKH_ChuaKyTuDacBiet() {
        Bai5_KhachHang kh = new Bai5_KhachHang("user@#$", "Nguyen Van A", "a@email.com", "0901234567", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: Mã khách hàng không hợp lệ (6-10 ký tự, chỉ chữ số)", Bai5.dangKy(kh));
    }

    @Test
    public void testMaKH_QuaNgan() {
        Bai5_KhachHang kh = new Bai5_KhachHang("abc", "Nguyen Van A", "a@email.com", "0901234567", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: Mã khách hàng không hợp lệ (6-10 ký tự, chỉ chữ số)", Bai5.dangKy(kh));
    }

    @Test
    public void testMaKH_TrungLap() {

        Bai5_KhachHang k1 = new Bai5_KhachHang("userTrung", "Nguoi Mot", "a@email.com", "0901111111", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        Bai5.dangKy(k1);


        Bai5_KhachHang k2 = new Bai5_KhachHang("userTrung", "Nguoi Hai", "b@email.com", "0902222222", "HCM", "pass1234", "pass1234", "2000-01-01", "Nữ", true);
        assertEquals("Lỗi: Mã khách hàng đã tồn tại", Bai5.dangKy(k2));
    }


    @Test
    public void testEmail_SaiDinhDang() {
        Bai5_KhachHang kh = new Bai5_KhachHang("userHappy", "Nguyen Van A", "email-khong-co-a-moc", "0901234567", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: Email không hợp lệ", Bai5.dangKy(kh));
    }

    @Test
    public void testEmail_TrungLap() {

        Bai5_KhachHang k1 = new Bai5_KhachHang("userHappy", "Nguoi Mot", "trung@email.com", "0901111111", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        Bai5.dangKy(k1);


        Bai5_KhachHang k2 = new Bai5_KhachHang("userEmail", "Nguoi Hai", "trung@email.com", "0902222222", "HCM", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: Email đã tồn tại", Bai5.dangKy(k2));
    }


    @Test
    public void testSDT_KhongBatDauBang0() {
        Bai5_KhachHang kh = new Bai5_KhachHang("userHappy", "Nguyen Van A", "a@email.com", "9991234567", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: SĐT phải bắt đầu bằng 0 và có 10-12 số", Bai5.dangKy(kh));
    }

    @Test
    public void testSDT_CoChuCai() {
        Bai5_KhachHang kh = new Bai5_KhachHang("userHappy", "Nguyen Van A", "a@email.com", "090abc1234", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: SĐT phải bắt đầu bằng 0 và có 10-12 số", Bai5.dangKy(kh));
    }


    @Test
    public void testMatKhau_KhongKhop() {
        Bai5_KhachHang kh = new Bai5_KhachHang("userHappy", "Nguyen Van A", "a@email.com", "0901234567", "HN", "pass1234", "passKhac", "2000-01-01", "Nam", true);
        assertEquals("Lỗi: Mật khẩu xác nhận không khớp", Bai5.dangKy(kh));
    }

    @Test
    public void testTuoi_Duoi18() {

        Bai5_KhachHang kh = new Bai5_KhachHang("userTuoi", "Nguyen Van A", "a@email.com", "0901234567", "HN", "pass1234", "pass1234", "2010-01-01", "Nam", true);
        assertEquals("Lỗi: Bạn chưa đủ 18 tuổi", Bai5.dangKy(kh));
    }

    @Test
    public void testDieuKhoan_ChuaDongY() {
        Bai5_KhachHang kh = new Bai5_KhachHang("userHappy", "Nguyen Van A", "a@email.com", "0901234567", "HN", "pass1234", "pass1234", "2000-01-01", "Nam", false);
        assertEquals("Lỗi: Bạn chưa đồng ý điều khoản", Bai5.dangKy(kh));
    }
    @Test
    public void testTuoi_QuaCao_1000Tuoi() {
        Bai5_KhachHang kh = new Bai5_KhachHang("userOld", "Cu Ong", "old@email.com", "0901234567", "HN", "pass1234", "pass1234", "1024-01-01", "Nam", true);
        String ketQuaThucTe = Bai5.dangKy(kh);
        assertEquals("Lỗi: Tuổi không hợp lệ", ketQuaThucTe);
    }
}