package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Bai5Test {
    private Bai5Form form;
    private Bai5DAO dao;

    @Before
    public void setUp() {
        form = new Bai5Form();
        dao = new Bai5DAO();
        dao.clearAll(); // Xóa DB trước mỗi lần test để tránh lỗi trùng
        form.setVisible(false);
    }

    // TC1: Thêm mới thành công (Happy Case)
    @Test
    public void testAddSuccess() {
        String result = form.validateAndSave("QA Engineer", "Test Soft", "Note 1");
        assertEquals("Saved successfully", result);
    }

    // TC2: Title bị bỏ trống
    @Test
    public void testEmptyTitle() {
        String result = form.validateAndSave("", "Desc", "Note");
        assertEquals("Title is required", result);
    }

    // TC3: Title quá dài (>100 ký tự) - Kiểm thử biên
    @Test
    public void testTitleBoundary() {
        StringBuilder longTitle = new StringBuilder();
        // Tạo chuỗi 101 ký tự
        for (int i = 0; i < 101; i++) longTitle.append("A");

        String result = form.validateAndSave(longTitle.toString(), "Desc", "Note");
        assertEquals("Title is too long", result);
    }

    // TC4: Description quá dài (>400 ký tự)
    @Test
    public void testDescriptionBoundary() {
        StringBuilder longDesc = new StringBuilder();
        // Tạo chuỗi 401 ký tự
        for (int i = 0; i < 401; i++) longDesc.append("B");

        String result = form.validateAndSave("Dev", longDesc.toString(), "Note");
        assertEquals("Description is too long", result);
    }

    // TC5: Kiểm tra trùng lặp Title
    @Test
    public void testDuplicateTitle() {
        // Thêm lần 1
        form.validateAndSave("Manager", "Desc", "Note");
        // Thêm lần 2 giống hệt Title
        String result = form.validateAndSave("Manager", "New Desc", "New Note");
        assertEquals("Title already exists", result);
    }
}