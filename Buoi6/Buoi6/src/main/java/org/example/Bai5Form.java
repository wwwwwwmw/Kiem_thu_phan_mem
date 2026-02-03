package org.example;

import javax.swing.*;
import java.awt.*;

public class Bai5Form extends JFrame {
    private JTextField txtTitle;
    private JTextArea txtDesc;
    private JTextArea txtNote;
    private JButton btnSave;
    private Bai5DAO dao;

    public Bai5Form() {
        dao = new Bai5DAO();
        initUI();
    }

    private void initUI() {
        setTitle("Add Job Title");
        setSize(400, 400);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Job Title*:"));
        txtTitle = new JTextField();
        add(txtTitle);

        add(new JLabel("Description:"));
        txtDesc = new JTextArea();
        add(new JScrollPane(txtDesc));

        add(new JLabel("Note:"));
        txtNote = new JTextArea();
        add(new JScrollPane(txtNote));

        btnSave = new JButton("Save");
        add(btnSave);

        btnSave.addActionListener(e -> {
            String msg = validateAndSave(txtTitle.getText(), txtDesc.getText(), txtNote.getText());
            JOptionPane.showMessageDialog(this, msg);
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Logic quan trọng cần kiểm thử
    public String validateAndSave(String title, String desc, String note) {
        // 1. Kiểm tra Title rỗng
        if (title == null || title.trim().isEmpty()) {
            return "Title is required";
        }
        // 2. Kiểm tra độ dài Title (>100 là lỗi)
        if (title.length() > 100) {
            return "Title is too long";
        }
        // 3. Kiểm tra độ dài Description (>400 là lỗi)
        if (desc != null && desc.length() > 400) {
            return "Description is too long";
        }
        // 4. Kiểm tra độ dài Note (>400 là lỗi)
        if (note != null && note.length() > 400) {
            return "Note is too long";
        }
        // 5. Kiểm tra trùng lặp trong DB
        if (dao.isTitleExist(title)) {
            return "Title already exists";
        }

        // Lưu
        Bai5 job = new Bai5(title, desc, note);
        return dao.save(job) ? "Saved successfully" : "Save failed";
    }

    public static void main(String[] args) {
        new Bai5Form();
    }
}