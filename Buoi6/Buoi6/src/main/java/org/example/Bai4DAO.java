package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bai4DAO {

    // Lưu vào Database thật
    public boolean save(Bai4 unit) {
        // Kiểm tra trùng trước
        if (isIdExist(unit.getUnitId())) {
            return false;
        }

        String sql = "INSERT INTO OrganizationUnit (UnitId, Name, Description) VALUES (?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, unit.getUnitId());
            stmt.setString(2, unit.getName());
            stmt.setString(3, unit.getDescription());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra ID tồn tại bằng SQL
    public boolean isIdExist(String id) {
        String sql = "SELECT COUNT(*) FROM OrganizationUnit WHERE UnitId = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu count > 0 nghĩa là đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm xóa sạch bảng (Dùng để chạy Test cho sạch sẽ)
    public void clearAll() {
        String sql = "DELETE FROM OrganizationUnit";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (conn != null) stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}