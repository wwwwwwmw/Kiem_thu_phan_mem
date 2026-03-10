package org.example.bai3;

import org.example.utils.Bai3Functions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhTienNuocBranchCoverageTest {

    @Test(description = "BC_TC1 - soM3 <= 0 -> N1 True")
    public void testSoM3KhongHopLe() {
        double actual = Bai3Functions.tinhTienNuoc(0, "dan_cu");

        Assert.assertEquals(
                actual,
                0.0,
                0.01,
                "Tien nuoc phai bang 0 khi soM3 <= 0."
        );
    }

    @Test(description = "BC_TC2 - ho_ngheo -> N1 False, N2 True")
    public void testHoNgheo() {
        double actual = Bai3Functions.tinhTienNuoc(5, "ho_ngheo");

        Assert.assertEquals(
                actual,
                25000.0,
                0.01,
                "Tinh tien nuoc sai cho khach hang ho_ngheo."
        );
    }

    @Test(description = "BC_TC3 - dan_cu va soM3 <= 10 -> N2 False, N3 True, N4 True")
    public void testDanCuBac1() {
        double actual = Bai3Functions.tinhTienNuoc(10, "dan_cu");

        Assert.assertEquals(
                actual,
                75000.0,
                0.01,
                "Tinh tien nuoc sai cho dan_cu bac 1."
        );
    }

    @Test(description = "BC_TC4 - dan_cu va 10 < soM3 <= 20 -> N4 False, N5 True")
    public void testDanCuBac2() {
        double actual = Bai3Functions.tinhTienNuoc(15, "dan_cu");

        Assert.assertEquals(
                actual,
                148500.0,
                0.01,
                "Tinh tien nuoc sai cho dan_cu bac 2."
        );
    }

    @Test(description = "BC_TC5 - dan_cu va soM3 > 20 -> N5 False")
    public void testDanCuBac3() {
        double actual = Bai3Functions.tinhTienNuoc(25, "dan_cu");

        Assert.assertEquals(
                actual,
                285000.0,
                0.01,
                "Tinh tien nuoc sai cho dan_cu bac 3."
        );
    }

    @Test(description = "BC_TC6 - kinh_doanh -> N3 False")
    public void testKinhDoanh() {
        double actual = Bai3Functions.tinhTienNuoc(10, "kinh_doanh");

        Assert.assertEquals(
                actual,
                220000.0,
                0.01,
                "Tinh tien nuoc sai cho khach hang kinh_doanh."
        );
    }
}