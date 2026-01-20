using Microsoft.VisualStudio.TestTools.UnitTesting;
using BTBuoi4;

namespace BTBuoi4.Tests
{
    [TestClass]
    public class Bai1_PowerTests
    {
        [TestMethod]
        public void TestPower_N_Equals_0()
        {
            // n = 0 thì kết quả phải là 1 
            double result = Bai1_Power.Power(2.0, 0);
            Assert.AreEqual(1.0, result);
        }

        [TestMethod]
        public void TestPower_N_Positive()
        {
            // 2^3 = 8
            double result = Bai1_Power.Power(2.0, 3);
            Assert.AreEqual(8.0, result);
        }

        [TestMethod]
        public void TestPower_N_Negative()
        {
            // 2^-2 = 0.25
            double result = Bai1_Power.Power(2.0, -2);
            Assert.AreEqual(0.25, result);
        }
    }
}
