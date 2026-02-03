package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Bai6Test {
    private Bai6Form form;
    private Bai6DAO dao;

    @Before
    public void setUp() {
        form = new Bai6Form();
        dao = new Bai6DAO();
        dao.clearAll(); // Xóa sạch dữ liệu cũ
    }

    // 1. Test Thêm mới thành công
    @Test
    public void testCreateSuccess() {
        String result = form.addUser("admin", "123", "Admin User", "admin@gmail.com");
        assertEquals("User created", result);
    }

    // 2. Test Lỗi trùng Username
    @Test
    public void testCreateDuplicate() {
        form.addUser("user1", "123", "User One", "user1@test.com");
        String result = form.addUser("user1", "456", "User Two", "user2@test.com");
        assertEquals("User already exists", result);
    }

    // 3. Test Lỗi Email sai định dạng
    @Test
    public void testInvalidEmail() {
        String result = form.addUser("user2", "123", "Name", "abc"); // Email thiếu @...
        assertEquals("Invalid Email", result);
    }

    // 4. Test Cập nhật thành công
    @Test
    public void testUpdateSuccess() {
        form.addUser("userUpdate", "123", "Old Name", "old@test.com");
        String result = form.updateUser("userUpdate", "newPass", "New Name", "new@test.com");
        assertEquals("User updated", result);
    }

    // 5. Test Xóa thành công
    @Test
    public void testDeleteSuccess() {
        form.addUser("userDelete", "123", "To Delete", "del@test.com");
        String result = form.deleteUser("userDelete");
        assertEquals("User deleted", result);
    }

    // 6. Test Xóa user không tồn tại
    @Test
    public void testDeleteNotFound() {
        String result = form.deleteUser("unknownUser");
        assertEquals("User not found", result);
    }
}