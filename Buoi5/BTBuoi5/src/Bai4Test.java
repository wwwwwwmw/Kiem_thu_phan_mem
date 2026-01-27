import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Bai4Test {


    @Test
    public void testTreEm_BienDuoi() {

        assertEquals(50, Bai4.tinhTienKham(0, "Nam"));
    }

    @Test
    public void testTreEm_BienTren() {

        assertEquals(50, Bai4.tinhTienKham(17, "Nữ"));
    }


    @Test
    public void testNam_Nhom1_18to35() {
        assertEquals(100, Bai4.tinhTienKham(18, "Nam"));
        assertEquals(100, Bai4.tinhTienKham(35, "Nam"));
    }

    @Test
    public void testNam_Nhom2_36to50() {
        assertEquals(120, Bai4.tinhTienKham(36, "Nam"));
        assertEquals(120, Bai4.tinhTienKham(50, "Nam"));
    }

    @Test
    public void testNam_Nhom3_51to145() {
        assertEquals(140, Bai4.tinhTienKham(51, "Nam"));
        assertEquals(140, Bai4.tinhTienKham(145, "Nam"));
    }


    @Test
    public void testNu_Nhom1_18to35() {
        assertEquals(80, Bai4.tinhTienKham(18, "Nữ"));
    }

    @Test
    public void testNu_Nhom2_36to50() {
        assertEquals(110, Bai4.tinhTienKham(40, "Nữ"));
    }

    @Test
    public void testNu_Nhom3_51to145() {
        assertEquals(140, Bai4.tinhTienKham(100, "Nữ"));
    }


    @Test
    public void testTuoiAm() {
        assertEquals(-1, Bai4.tinhTienKham(-5, "Nam"));
    }

    @Test
    public void testTuoiQuaCao() {
        assertEquals(-1, Bai4.tinhTienKham(146, "Nữ"));
    }
}