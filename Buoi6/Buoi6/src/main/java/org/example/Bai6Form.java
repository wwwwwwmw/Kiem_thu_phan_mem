package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class Bai6Form extends JFrame {
    private JTextField txtUser, txtPass, txtName, txtEmail;
    private Bai6DAO dao;

    public Bai6Form() {
        dao = new Bai6DAO();
        initUI();
    }

    private void initUI() {
        setTitle("User Management");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Username:")); txtUser = new JTextField(); add(txtUser);
        add(new JLabel("Password:")); txtPass = new JPasswordField(); add(txtPass);
        add(new JLabel("Fullname:")); txtName = new JTextField(); add(txtName);
        add(new JLabel("Email:"));    txtEmail = new JTextField(); add(txtEmail);

        setVisible(false); // Ẩn GUI khi chạy test
    }

    // Validate Email Regex
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    // Logic THÊM MỚI
    public String addUser(String u, String p, String n, String e) {
        if (u.isEmpty() || p.isEmpty()) return "Username/Password required";
        if (!isValidEmail(e)) return "Invalid Email";
        if (dao.exists(u)) return "User already exists";

        return dao.insert(new Bai6(u, p, n, e)) ? "User created" : "Create failed";
    }

    // Logic CẬP NHẬT
    public String updateUser(String u, String p, String n, String e) {
        if (u.isEmpty()) return "Username required";
        if (!dao.exists(u)) return "User not found";

        return dao.update(new Bai6(u, p, n, e)) ? "User updated" : "Update failed";
    }

    // Logic XÓA
    public String deleteUser(String u) {
        if (u.isEmpty()) return "Username required";
        if (!dao.exists(u)) return "User not found";

        return dao.delete(u) ? "User deleted" : "Delete failed";
    }
}