package org.example;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class Bai3 {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void example() {
        // 1. Tự tạo lỗi và thêm vào collector (Chương trình vẫn chạy tiếp)
        collector.addError(new Throwable("There is an error in first line"));

        // 2. Thêm lỗi thứ hai
        collector.addError(new Throwable("There is an error in second line"));

        System.out.println("Hello");

        // 3. Sử dụng try-catch để bắt lỗi Assertion (so sánh sai) và thêm vào collector
        try {
            Assert.assertTrue("A" == "B"); // Phép so sánh này sẽ Fail
        } catch (Throwable t) {
            collector.addError(t);
        }

        System.out.println("World!!!!");
    }
}