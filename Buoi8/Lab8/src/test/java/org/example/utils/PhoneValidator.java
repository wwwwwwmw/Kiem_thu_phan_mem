package org.example.utils;

public class PhoneValidator {

    public static boolean isValid(String phone) {
        if (phone == null) {
            return false;
        }

        String input = phone.trim();
        if (input.isEmpty()) {
            return false;
        }

        // Chỉ cho phép chữ số, dấu +, khoảng trắng
        if (!input.matches("[0-9+\\s]+")) {
            return false;
        }

        // Không cho phép dấu + xuất hiện sai vị trí
        if (input.indexOf('+') > 0) {
            return false;
        }
        if (input.contains("+") && !input.startsWith("+84")) {
            return false;
        }

        // Bỏ khoảng trắng
        String normalized = input.replaceAll("\\s+", "");

        // Chuẩn hóa về 0xxxxxxxxx
        if (normalized.startsWith("+84")) {
            normalized = "0" + normalized.substring(3);
        } else if (!normalized.startsWith("0")) {
            return false;
        }

        // Phải đúng 10 chữ số
        if (!normalized.matches("\\d{10}")) {
            return false;
        }

        // Đầu số hợp lệ Việt Nam theo đề
        return normalized.matches("0(3|5|7|8|9)\\d{8}");
    }
}