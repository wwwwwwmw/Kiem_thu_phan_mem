using Microsoft.VisualStudio.TestTools.UnitTesting;
using BTBuoi4;

namespace BTBuoi4.Tests
{
    [TestClass]
    public class Bai4_HCNTests
    {
        [TestMethod]
        public void TestDienTich()
        {
            // (0, 10) đến (10, 0) -> Rộng 10, Cao 10 -> Diện tích 100
            Diem p1 = new Diem(0, 10);
            Diem p2 = new Diem(10, 0);
            HinhChuNhat hcn = new HinhChuNhat(p1, p2);
            Assert.AreEqual(100, hcn.TinhDienTich());
        }

        [TestMethod]
        public void TestGiaoNhau_True()
        {
            // HCN 1: (0, 10) -> (10, 0)
            // HCN 2: (5, 5) -> (15, -5) -> Có phần chung
            HinhChuNhat h1 = new HinhChuNhat(new Diem(0, 10), new Diem(10, 0));
            HinhChuNhat h2 = new HinhChuNhat(new Diem(5, 5), new Diem(15, -5));
            Assert.IsTrue(h1.IsIntersect(h2));
        }
    }
}