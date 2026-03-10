package org.example.bai3;

import org.example.utils.Bai3Functions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiBranchCoverageTest {

    @Test(description = "BC_TC1 - diemTB khong hop le (<0) -> N1 True")
    public void testInvalidScoreNegative() {
        String actual = Bai3Functions.xepLoai(-1, false);

        Assert.assertEquals(
                actual,
                "Diem khong hop le",
                "Sai ket qua khi diemTB < 0."
        );
    }

    @Test(description = "BC_TC2 - diemTB >= 8.5 -> N1 False, N2 True")
    public void testXepLoaiGioi() {
        String actual = Bai3Functions.xepLoai(9.0, false);

        Assert.assertEquals(
                actual,
                "Gioi",
                "Sai ket qua xep loai Gioi."
        );
    }

    @Test(description = "BC_TC3 - 7.0 <= diemTB < 8.5 -> N1 False, N2 False, N3 True")
    public void testXepLoaiKha() {
        String actual = Bai3Functions.xepLoai(7.5, false);

        Assert.assertEquals(
                actual,
                "Kha",
                "Sai ket qua xep loai Kha."
        );
    }

    @Test(description = "BC_TC4 - 5.5 <= diemTB < 7.0 -> N1 False, N2 False, N3 False, N4 True")
    public void testXepLoaiTrungBinh() {
        String actual = Bai3Functions.xepLoai(6.0, false);

        Assert.assertEquals(
                actual,
                "Trung Binh",
                "Sai ket qua xep loai Trung Binh."
        );
    }

    @Test(description = "BC_TC5 - diemTB < 5.5 va coThiLai = true -> N5 True")
    public void testXepLoaiThiLai() {
        String actual = Bai3Functions.xepLoai(4.0, true);

        Assert.assertEquals(
                actual,
                "Thi lai",
                "Sai ket qua khi hoc sinh phai thi lai."
        );
    }

    @Test(description = "BC_TC6 - diemTB < 5.5 va coThiLai = false -> N5 False")
    public void testXepLoaiHocLai() {
        String actual = Bai3Functions.xepLoai(4.0, false);

        Assert.assertEquals(
                actual,
                "Yeu - Hoc lai",
                "Sai ket qua khi hoc sinh phai hoc lai."
        );
    }
}