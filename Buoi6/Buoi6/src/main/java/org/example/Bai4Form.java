package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai4Form extends JFrame {
    private JTextField txtUnitId;
    private JTextField txtName;
    private JTextArea txtDescription;
    private JButton btnSave;
    private JButton btnCancel;
    private Bai4DAO dao;

    public Bai4Form() {
        dao = new Bai4DAO();
        initUI();
    }

    private void initUI() {
        setTitle("Add Bai4 Unit");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Unit Id:"));
        txtUnitId = new JTextField();
        add(txtUnitId);

        add(new JLabel("Name*:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Description:"));
        txtDescription = new JTextArea();
        add(new JScrollPane(txtDescription));

        btnCancel = new JButton("Cancel");
        add(btnCancel);

        btnSave = new JButton("Save");
        add(btnSave);

        // Sự kiện nút Save
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUnit();
            }
        });

        // Sự kiện nút Cancel
        btnCancel.addActionListener(e -> {
            txtUnitId.setText("");
            txtName.setText("");
            txtDescription.setText("");
        });

        setVisible(true);
    }

    // Logic xử lý khi bấm Save (Hàm này sẽ được test)
    public String validateAndSave(String id, String name, String desc) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required";
        }
        if (dao.isIdExist(id)) {
            return "Unit Id already exists";
        }
        if (desc.length() > 400) { // Giả sử max length là 400
            return "Description is too long";
        }

        Bai4 unit = new Bai4(id, name, desc);
        boolean saved = dao.save(unit);
        return saved ? "Saved successfully" : "Save failed";
    }

    private void saveUnit() {
        String result = validateAndSave(txtUnitId.getText(), txtName.getText(), txtDescription.getText());
        JOptionPane.showMessageDialog(this, result);
    }

    public static void main(String[] args) {
        new Bai4Form();
    }
}