public class Bai4 {

    public static int tinhTienKham(int tuoi, String gioiTinh) {

        if (tuoi < 0 || tuoi > 145) {
            return -1;
        }


        if (tuoi >= 0 && tuoi <= 17) {
            return 50;
        }


        if (gioiTinh.equalsIgnoreCase("Nam")) {
            if (tuoi >= 18 && tuoi <= 35) return 100;
            if (tuoi >= 36 && tuoi <= 50) return 120;
            if (tuoi >= 51 && tuoi <= 145) return 140;
        }
        else if (gioiTinh.equalsIgnoreCase("Nữ")) {
            if (tuoi >= 18 && tuoi <= 35) return 80;
            if (tuoi >= 36 && tuoi <= 50) return 110;
            if (tuoi >= 51 && tuoi <= 145) return 140;
        }

        return 0;
    }
}