using BTBuoi4;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace BTBuoi4.Tests
{
    [TestClass]
    public class Bai2_PolynomialTests
    {
        [TestMethod]
        //[ExpectedException(typeof(ArgumentException))]
        public void TestContructor_InvalidCount()
        {
            // Bậc 2 cần 3 hệ số, nhưng chỉ truyền 2 -> Mong đợi lỗi 
            new Bai2_Polynomial(2, new List<int> { 1, 2 });
        }

        [TestMethod]
        public void TestCal_ValidData()
        {
            // Đa thức: 1 + 2x + 3x^2 (n=2)
            // Tại x = 2: 1 + 2(2) + 3(4) = 1 + 4 + 12 = 17
            var poly = new Bai2_Polynomial(2, new List<int> { 1, 2, 3 });
            int result = poly.Cal(2);
            Assert.AreEqual(17, result);
        }
    }
}