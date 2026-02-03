package org.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.fail;

public class Bai2Test {

    // CÁCH 1: Sử dụng @Rule ExpectedException [cite: 133, 134]
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testExpectedExceptionRule() {
        // Định nghĩa lỗi mong muốn bắt được
        exception.expect(IllegalArgumentException.class);
        // (Tùy chọn) Kiểm tra message của lỗi có chứa chuỗi cụ thể không
        // exception.expectMessage("Invalid age");

        // Hành động gây lỗi: Tạo người với tuổi âm
        new Bai2("Fpoly", -1);
    }

    // CÁCH 2: Sử dụng tham số 'expected' trong @Test [cite: 159]
    @Test(expected = IllegalArgumentException.class)
    public void testExpectedExceptionAnnotation() {
        // Hành động gây lỗi trực tiếp
        new Bai2("Fpoly", -1);
    }

    // CÁCH 3: Sử dụng khối try-catch truyền thống [cite: 190]
    @Test
    public void testExpectedExceptionTryCatch() {
        try {
            new Bai2("Fpoly", -1);
            // Nếu dòng trên KHÔNG gây lỗi, dòng fail() sẽ chạy và báo test thất bại
            fail("Should have thrown an IllegalArgumentException because age is invalid!");
        } catch (IllegalArgumentException e) {
            // Nếu bắt được lỗi thì test pass, không làm gì cả (hoặc có thể assert thêm message)
        }
    }
}