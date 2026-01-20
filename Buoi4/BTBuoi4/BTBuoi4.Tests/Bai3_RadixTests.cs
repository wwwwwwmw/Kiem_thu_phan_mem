using Microsoft.VisualStudio.TestTools.UnitTesting;
using BTBuoi4;
using System;

namespace BTBuoi4.Tests
{
    [TestClass]
    public class Bai3_RadixTests
    {
        [TestMethod]
        public void TestConvert_ToBinary()
        {
            // 10 chuyển sang nhị phân (hệ 2) -> 1010
            var radix = new Bai3_Radix(10);
            Assert.AreEqual("1010", radix.ConvertDecimalToAnother(2));
        }

        [TestMethod]
        public void TestConvert_ToHex()
        {
            // 255 chuyển sang Hex (hệ 16) -> FF
            var radix = new Bai3_Radix(255);
            Assert.AreEqual("FF", radix.ConvertDecimalToAnother(16));
        }

        [TestMethod]
        //[ExpectedException(typeof(ArgumentException))]
        public void TestConvert_InvalidRadix()
        {
            // Hệ cơ số 1 (không hợp lệ) -> Lỗi
            var radix = new Bai3_Radix(10);
            radix.ConvertDecimalToAnother(1);
        }
    }
}