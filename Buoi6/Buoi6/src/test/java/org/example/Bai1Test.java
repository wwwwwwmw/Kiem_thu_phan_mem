package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Bai1Test {

    public String message = "Fpoly exception";

    // Khởi tạo đối tượng Bai1
    Bai1 bai1 = new Bai1(message);

    // Test case 1: Mong đợi ngoại lệ ArithmeticException xảy ra khi chia cho 0
    @Test(expected = ArithmeticException.class)
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        bai1.printMessage();
    }

    // Test case 2: Kiểm tra phương thức in tin nhắn bình thường
    @Test
    public void testPrintHiMessage() {
        message = "Hi!" + message;
        System.out.println("Inside testPrintHiMessage()");
        // So sánh kết quả mong đợi và thực tế
        assertEquals(message, bai1.printHiMessage());
    }
}