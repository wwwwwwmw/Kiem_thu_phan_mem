using System;

namespace BTBuoi4
{
    public class Bai1_Power
    {
        public static double Power(double x, int n)
        {
            if (n == 0)
                return 1.0;
            else if (n > 0)
                return x * Power(x, n - 1);
            else // n < 0
                return Power(x, n + 1) / x;
        }
    }
}
