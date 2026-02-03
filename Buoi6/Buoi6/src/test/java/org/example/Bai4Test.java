package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Bai4Test {

    private Bai4Form form;
    private Bai4DAO dao;

    @Before
    public void setUp() {
        form = new Bai4Form();
        dao = new Bai4DAO();

        // QUAN TRỌNG: Xóa sạch dữ liệu trong bảng SQL trước khi test
        dao.clearAll();

        form.setVisible(false);
    }

    @Test
    public void testAddSuccess() {
        // Test thêm mới -> Sẽ insert vào SQL Server
        String result = form.validateAndSave("UNIT_SQL_01", "Dev Team", "SQL Test");
        assertEquals("Saved successfully", result);
    }

    @Test
    public void testDuplicateId() {
        // Thêm lần 1
        form.validateAndSave("UNIT_SQL_01", "Dev Team 1", "Desc 1");

        // Thêm lần 2 trùng ID -> DAO sẽ check trong SQL và trả về false
        String result = form.validateAndSave("UNIT_SQL_01", "Dev Team 2", "Desc 2");
        assertEquals("Unit Id already exists", result);
    }

    @Test
    public void testAddEmptyName() {
        String result = form.validateAndSave("UNIT_SQL_02", "", "Desc");
        assertEquals("Name is required", result);
    }
}