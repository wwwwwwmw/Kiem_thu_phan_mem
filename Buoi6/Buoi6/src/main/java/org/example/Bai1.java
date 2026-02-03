package org.example;

public class Bai1 {
    private String message;

    public Bai1(String message) {
        this.message = message;
    }

    // Phương thức in thông báo và cố tình gây lỗi chia cho 0
    public void printMessage() {
        System.out.println(message);
        int divide = 1 / 0; // Dòng này sẽ gây ra ArithmeticException [cite: 17]
    }

    // Phương thức nối chuỗi "Hi!" và trả về kết quả
    public String printHiMessage() {
        message = "Hi!" + message;
        System.out.println(message);
        return message;
    }
}