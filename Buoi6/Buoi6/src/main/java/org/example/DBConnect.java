package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    // Cấu hình chuỗi kết nối đến SQL Server
    // databaseName=Lab6_Testing: Tên database chúng ta đã thống nhất tạo ở bước trước
    private static final String URL = "jdbc:sqlserver://DESKTOP-ENDTJTR;databaseName=Lab6;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    // Cập nhật thông tin đăng nhập theo yêu cầu của bạn
    private static final String USER = "";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            // Đăng ký driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Mở kết nối
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Lỗi kết nối Database! Vui lòng kiểm tra lại Username/Password trong SQL Server.");
            System.err.println("Chi tiết lỗi: " + e.getMessage());
            return null;
        }
    }
}