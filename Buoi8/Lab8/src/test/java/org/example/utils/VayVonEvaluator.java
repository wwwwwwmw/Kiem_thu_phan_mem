package org.example.utils;

public class VayVonEvaluator {

    public static boolean duDieuKienVay(int tuoi, double thuNhap,
                                        boolean coTaiSanBaoLanh, int diemTinDung) {
        // dieuKienCoBan = (tuoi >= 22) && (thuNhap >= 10_000_000)
        boolean dieuKienCoBan = (tuoi >= 22) && (thuNhap >= 10_000_000);

        // dieuKienBaoDam = coTaiSanBaoLanh || (diemTinDung >= 700)
        boolean dieuKienBaoDam = coTaiSanBaoLanh || (diemTinDung >= 700);

        // ketQua = dieuKienCoBan && dieuKienBaoDam
        return dieuKienCoBan && dieuKienBaoDam;
    }
}