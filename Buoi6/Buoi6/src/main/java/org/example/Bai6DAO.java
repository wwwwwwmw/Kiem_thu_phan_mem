package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bai6DAO {

    // Kiểm tra Username tồn tại
    public boolean exists(String username) {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE Username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // CREATE: Thêm mới
    public boolean insert(Bai6 user) {
        if (exists(user.getUsername())) return false;
        String sql = "INSERT INTO Users (Username, Password, Fullname, Email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE: Cập nhật thông tin
    public boolean update(Bai6 user) {
        if (!exists(user.getUsername())) return false;
        String sql = "UPDATE Users SET Password=?, Fullname=?, Email=? WHERE Username=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getFullname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE: Xóa người dùng
    public boolean delete(String username) {
        if (!exists(username)) return false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE Username=?")) {
            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sạch bảng (Dùng cho Test)
    public void clearAll() {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}