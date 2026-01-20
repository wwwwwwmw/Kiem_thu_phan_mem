using System;

namespace BTBuoi4
{
    public class Diem
    {
        public int X { get; set; }
        public int Y { get; set; }
        public Diem(int x, int y) { X = x; Y = y; }
    }

    public class HinhChuNhat
    {
        public Diem TopLeft { get; set; }
        public Diem BottomRight { get; set; }

        public HinhChuNhat(Diem tl, Diem br)
        {
            TopLeft = tl;
            BottomRight = br;
        }

        public int TinhDienTich()
        {
            // Diện tích = |Rộng| * |Cao|
            return Math.Abs(BottomRight.X - TopLeft.X) * Math.Abs(TopLeft.Y - BottomRight.Y);
        }

        public bool IsIntersect(HinhChuNhat other)
        {
            // Kiểm tra điều kiện KHÔNG giao nhau, sau đó phủ định lại
            // Giả sử hệ tọa độ máy tính (Y tăng dần xuống dưới hoặc lên trên đều dùng logic này)
            // Left > Right hoặc Right < Left hoặc Top < Bottom ...

            // Logic chuẩn: Hai HCN không giao nhau nếu một cái nằm hoàn toàn bên trái, phải, trên hoặc dưới cái kia.
            if (this.TopLeft.X > other.BottomRight.X || other.TopLeft.X > this.BottomRight.X)
                return false;

            // Lưu ý: Tùy hệ trục tọa độ Y mà so sánh Top/Bottom sẽ khác nhau. 
            // Giả sử Y hướng lên (Toán học): Top Y > Bottom Y.
            if (this.BottomRight.Y > other.TopLeft.Y || other.BottomRight.Y > this.TopLeft.Y)
                return false;

            return true;
        }
    }
}