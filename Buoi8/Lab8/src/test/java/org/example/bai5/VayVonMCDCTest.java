package org.example.bai5;

import org.example.utils.VayVonEvaluator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VayVonMCDCTest {

    @DataProvider(name = "mcdcData")
    public Object[][] mcdcData() {
        return new Object[][]{
                // moTa, tuoi, thuNhap, coTaiSanBaoLanh, diemTinDung, expected

                // R1: A=T, B=T, C=T, D=F -> True
                {"R1 - Co so: A=T, B=T, C=T, D=F -> du dieu kien vay",
                        25, 12_000_000, true, 650, true},

                // R2: A=F, B=T, C=T, D=F -> False
                {"R2 - MC/DC cho A: tuoi < 22 lam ket qua doi",
                        20, 12_000_000, true, 650, false},

                // R3: A=T, B=F, C=T, D=F -> False
                {"R3 - MC/DC cho B: thu nhap < 10tr lam ket qua doi",
                        25, 8_000_000, true, 650, false},

                // R4: A=T, B=T, C=F, D=F -> False
                {"R4 - MC/DC cho C: khong co tai san, diem tin dung chua du",
                        25, 12_000_000, false, 650, false},

                // R5: A=T, B=T, C=F, D=T -> True
                {"R5 - MC/DC cho D: diem tin dung >= 700 lam ket qua doi",
                        25, 12_000_000, false, 750, true}
        };
    }

    @Test(dataProvider = "mcdcData", description = "Bo test MC/DC cho ham duDieuKienVay")
    public void testMCDCDataSet(String moTa, int tuoi, double thuNhap,
                                boolean coTaiSanBaoLanh, int diemTinDung,
                                boolean expected) {
        boolean actual = VayVonEvaluator.duDieuKienVay(
                tuoi, thuNhap, coTaiSanBaoLanh, diemTinDung
        );

        Assert.assertEquals(
                actual,
                expected,
                "Sai ket qua voi test case: " + moTa
        );
    }

    @Test(description = "MC/DC - A doc lap: tuoi >= 22")
    public void testMCDC_TuoiDocLap_ThapHon22() {
        boolean baseline = VayVonEvaluator.duDieuKienVay(25, 12_000_000, true, 650);
        boolean changed  = VayVonEvaluator.duDieuKienVay(20, 12_000_000, true, 650);

        Assert.assertTrue(
                baseline,
                "Baseline phai la true khi A=T, B=T, C=T, D=F."
        );
        Assert.assertFalse(
                changed,
                "Khi chi doi A thanh false, ket qua phai doi sang false."
        );
    }

    @Test(description = "MC/DC - B doc lap: thuNhap >= 10_000_000")
    public void testMCDC_ThuNhapDocLap_Duoi10Trieu() {
        boolean baseline = VayVonEvaluator.duDieuKienVay(25, 12_000_000, true, 650);
        boolean changed  = VayVonEvaluator.duDieuKienVay(25, 8_000_000, true, 650);

        Assert.assertTrue(
                baseline,
                "Baseline phai la true khi A=T, B=T, C=T, D=F."
        );
        Assert.assertFalse(
                changed,
                "Khi chi doi B thanh false, ket qua phai doi sang false."
        );
    }

    @Test(description = "MC/DC - C doc lap: coTaiSanBaoLanh")
    public void testMCDC_TaiSanDocLap_KhongCoTaiSan() {
        boolean baseline = VayVonEvaluator.duDieuKienVay(25, 12_000_000, true, 650);
        boolean changed  = VayVonEvaluator.duDieuKienVay(25, 12_000_000, false, 650);

        Assert.assertTrue(
                baseline,
                "Baseline phai la true khi A=T, B=T, C=T, D=F."
        );
        Assert.assertFalse(
                changed,
                "Khi chi doi C thanh false, ket qua phai doi sang false."
        );
    }

    @Test(description = "MC/DC - D doc lap: diemTinDung >= 700")
    public void testMCDC_DiemTinDungDocLap_Tu700TroLen() {
        boolean baseline = VayVonEvaluator.duDieuKienVay(25, 12_000_000, false, 650);
        boolean changed  = VayVonEvaluator.duDieuKienVay(25, 12_000_000, false, 750);

        Assert.assertFalse(
                baseline,
                "Baseline phai la false khi A=T, B=T, C=F, D=F."
        );
        Assert.assertTrue(
                changed,
                "Khi chi doi D thanh true, ket qua phai doi sang true."
        );
    }
}