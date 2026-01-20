using Microsoft.VisualStudio.TestTools.UnitTesting;
using BTBuoi4;
using System.Collections.Generic;

namespace BTBuoi4.Tests
{
    [TestClass]
    public class Bai5_HocVienTests
    {
        [TestMethod]
        public void TestHocBong_Dat()
        {
            // 8, 9, 8 -> TB > 8, ko điểm nhỏ -> Đạt
            HocVien hv = new HocVien("HV01", "Nguyen Van A", 8, 9, 8);
            Assert.IsTrue(hv.CoHocBong());
        }

        [TestMethod]
        public void TestHocBong_KhongDat_DiemLiet()
        {
            // 10, 10, 4 -> TB cao nhưng có môn < 5 -> Không đạt
            HocVien hv = new HocVien("HV02", "Nguyen Van B", 10, 10, 4);
            Assert.IsFalse(hv.CoHocBong());
        }

        [TestMethod]
        public void TestHocBong_KhongDat_DiemThap()
        {
            // 6, 6, 6 -> Không môn < 5 nhưng TB < 8 -> Không đạt
            HocVien hv = new HocVien("HV03", "Nguyen Van C", 6, 6, 6);
            Assert.IsFalse(hv.CoHocBong());
        }
    }
}