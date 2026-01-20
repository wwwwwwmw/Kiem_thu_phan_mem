using System.Collections.Generic;
using System.Linq;

namespace BTBuoi4
{
    public class HocVien
    {
        public string MaSo { get; set; }
        public string HoTen { get; set; }
        public double DiemMon1 { get; set; }
        public double DiemMon2 { get; set; }
        public double DiemMon3 { get; set; }

        public HocVien(string ma, string ten, double d1, double d2, double d3)
        {
            MaSo = ma; HoTen = ten; DiemMon1 = d1; DiemMon2 = d2; DiemMon3 = d3;
        }

        public bool CoHocBong()
        {
            // Điều kiện: Không môn nào dưới 5 
            if (DiemMon1 < 5 || DiemMon2 < 5 || DiemMon3 < 5)
                return false;

            // Điều kiện: Điểm trung bình >= 8.0 
            double dtb = (DiemMon1 + DiemMon2 + DiemMon3) / 3.0;
            return dtb >= 8.0;
        }
    }

    public class TrungTam
    {
        public List<HocVien> TimHocVienNhanHocBong(List<HocVien> ds)
        {
            return ds.Where(hv => hv.CoHocBong()).ToList();
        }
    }
}