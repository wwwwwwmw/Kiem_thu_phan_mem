using System;
using System.Collections.Generic;
using System.Linq;

namespace BTBuoi4
{
    public class Bai3_Radix
    {
        private int number;

        public Bai3_Radix(int number)
        {
            if (number < 0)
                throw new ArgumentException("Incorrect Value"); // 
            this.number = number;
        }

        public string ConvertDecimalToAnother(int radix)
        {
            if (radix < 2 || radix > 16)
                throw new ArgumentException("Invalid Radix"); // 

            int n = this.number;
            if (n == 0) return "0";

            List<string> result = new List<string>();
            while (n > 0)
            {
                int value = n % radix;
                if (value < 10)
                    result.Add(value.ToString());
                else
                {
                    // Mapping 10-15 sang A-F 
                    switch (value)
                    {
                        case 10: result.Add("A"); break;
                        case 11: result.Add("B"); break;
                        case 12: result.Add("C"); break;
                        case 13: result.Add("D"); break;
                        case 14: result.Add("E"); break;
                        case 15: result.Add("F"); break;
                    }
                }
                n /= radix;
            }
            result.Reverse(); // Đảo ngược chuỗi kết quả 
            return string.Join("", result);
        }
    }
}