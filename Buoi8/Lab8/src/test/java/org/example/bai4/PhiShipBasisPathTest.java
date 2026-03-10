package org.example.bai4;

import org.example.utils.PhiShip;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhiShipBasisPathTest {

    @Test(description = "Path 1: Trọng lượng không hợp lệ")
    public void testPath1_InvalidWeight() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> PhiShip.tinhPhiShip(-1, "noi_thanh", false)
        );
    }

    @Test(description = "Path 2: Nội thành, <= 5kg, không member")
    public void testPath2_NoiThanhNheKhongMember() {
        double expected = 15000.0;
        double actual = PhiShip.tinhPhiShip(3, "noi_thanh", false);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 2."
        );
    }

    @Test(description = "Path 3: Nội thành, > 5kg, không member")
    public void testPath3_NoiThanhNangKhongMember() {
        double expected = 19000.0; // 15000 + (7-5)*2000
        double actual = PhiShip.tinhPhiShip(7, "noi_thanh", false);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 3."
        );
    }

    @Test(description = "Path 4: Ngoại thành, <= 3kg, không member")
    public void testPath4_NgoaiThanhNheKhongMember() {
        double expected = 25000.0;
        double actual = PhiShip.tinhPhiShip(2, "ngoai_thanh", false);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 4."
        );
    }

    @Test(description = "Path 5: Ngoại thành, > 3kg, không member")
    public void testPath5_NgoaiThanhNangKhongMember() {
        double expected = 31000.0; // 25000 + (5-3)*3000
        double actual = PhiShip.tinhPhiShip(5, "ngoai_thanh", false);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 5."
        );
    }

    @Test(description = "Path 6: Tỉnh khác, <= 2kg, không member")
    public void testPath6_TinhKhacNheKhongMember() {
        double expected = 50000.0;
        double actual = PhiShip.tinhPhiShip(2, "tinh_khac", false);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 6."
        );
    }

    @Test(description = "Path 7: Tỉnh khác, > 2kg, không member")
    public void testPath7_TinhKhacNangKhongMember() {
        double expected = 60000.0; // 50000 + (4-2)*5000
        double actual = PhiShip.tinhPhiShip(4, "tinh_khac", false);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 7."
        );
    }

    @Test(description = "Path 8: Nội thành, <= 5kg, có member")
    public void testPath8_NoiThanhNheCoMember() {
        double expected = 13500.0; // 15000 * 0.9
        double actual = PhiShip.tinhPhiShip(3, "noi_thanh", true);

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Sai phí ship cho path 8."
        );
    }
}