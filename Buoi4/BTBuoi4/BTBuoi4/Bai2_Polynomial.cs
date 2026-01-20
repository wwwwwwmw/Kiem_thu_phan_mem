using System;
using System.Collections.Generic;
using System.Linq;

namespace BTBuoi4
{
    public class Bai2_Polynomial
    {
        private int n;
        private List<int> a;

        public Bai2_Polynomial(int n, List<int> a)
        {
            if (n < 0)
                throw new ArgumentException("Invalid Data"); // Kiểm tra n âm 
            if (a.Count != n + 1)
                throw new ArgumentException("Invalid Data"); // Kiểm tra số lượng hệ số 

            this.n = n;
            this.a = a;
        }

        public int Cal(double x)
        {
            int result = 0;
            for (int i = 0; i <= this.n; i++)
            {
                result += (int)(a[i] * Math.Pow(x, i)); // Công thức tính tổng 
            }
            return result;
        }
    }
}