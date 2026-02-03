package org.example;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Bai3Runner {
    public static void main(String[] args) {
        // Chạy class Bai3
        Result result = JUnitCore.runClasses(Bai3.class);

        // Duyệt và in ra danh sách các lỗi đã thu thập được
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        // In kết quả cuối cùng (true/false)
        System.out.println("Result==" + result.wasSuccessful());
    }
}