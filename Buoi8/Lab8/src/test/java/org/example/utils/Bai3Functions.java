package org.example.utils;

public class Bai3Functions {

    // Bài 3.1
    public static String xepLoai(double diemTB, boolean coThiLai) {
        if (diemTB < 0 || diemTB > 10) { // Điều kiện 1
            return "Diem khong hop le";
        }

        if (diemTB >= 8.5) { // Điều kiện 2
            return "Gioi";
        } else if (diemTB >= 7.0) { // Điều kiện 3
            return "Kha";
        } else if (diemTB >= 5.5) { // Điều kiện 4
            return "Trung Binh";
        } else {
            if (coThiLai) { // Điều kiện 5
                return "Thi lai";
            }
            return "Yeu - Hoc lai";
        }
    }

    // Bài 3.2
    public static double tinhTienNuoc(int soM3, String loaiKhachHang) {
        if (soM3 <= 0) return 0; // N1

        double donGia;

        if (loaiKhachHang.equals("ho_ngheo")) { // N2
            donGia = 5000;
        } else if (loaiKhachHang.equals("dan_cu")) { // N3
            if (soM3 <= 10) { // N4
                donGia = 7500;
            } else if (soM3 <= 20) { // N5
                donGia = 9900;
            } else {
                donGia = 11400;
            }
        } else { // kinh_doanh
            donGia = 22000;
        }

        return soM3 * donGia;
    }
}