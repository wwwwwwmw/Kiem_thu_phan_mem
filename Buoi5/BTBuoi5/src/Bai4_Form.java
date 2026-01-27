import javax.swing.*;
import java.awt.*;

public class Bai4_Form extends JFrame {
    private JTextField txtTuoi;
    private JRadioButton rdoNam, rdoNu;
    private ButtonGroup grpGioiTinh;
    private JLabel lblKetQua;
    private JButton btnTinhTien;

    public Bai4_Form() {
        setTitle("TÍNH TIỀN KHÁM BỆNH (BÀI 4)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));


        JPanel pnlTuoi = new JPanel();
        pnlTuoi.add(new JLabel("Nhập tuổi: "));
        txtTuoi = new JTextField(10);
        pnlTuoi.add(txtTuoi);
        add(pnlTuoi);


        JPanel pnlGioiTinh = new JPanel();
        pnlGioiTinh.add(new JLabel("Giới tính: "));
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        grpGioiTinh = new ButtonGroup();
        grpGioiTinh.add(rdoNam);
        grpGioiTinh.add(rdoNu);
        rdoNam.setSelected(true);
        pnlGioiTinh.add(rdoNam);
        pnlGioiTinh.add(rdoNu);
        add(pnlGioiTinh);


        JPanel pnlButton = new JPanel();
        btnTinhTien = new JButton("Tính Tiền");
        btnTinhTien.setFont(new Font("Arial", Font.BOLD, 14));
        btnTinhTien.setBackground(Color.BLUE);
        btnTinhTien.setForeground(Color.black);
        pnlButton.add(btnTinhTien);
        add(pnlButton);


        lblKetQua = new JLabel("Số tiền phải trả: ...", SwingConstants.CENTER);
        lblKetQua.setFont(new Font("Arial", Font.BOLD, 18));
        lblKetQua.setForeground(Color.RED);
        add(lblKetQua);


        btnTinhTien.addActionListener(e -> {
            try {

                String tuoiStr = txtTuoi.getText();
                if (tuoiStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi!");
                    return;
                }

                int tuoi = Integer.parseInt(tuoiStr);
                String gioitinh = rdoNam.isSelected() ? "Nam" : "Nữ";


                int ketQua = Bai4.tinhTienKham(tuoi, gioitinh);


                if (ketQua == -1) {
                    lblKetQua.setText("Lỗi: Tuổi không hợp lệ (0-145)");
                } else {
                    lblKetQua.setText("Số tiền phải trả: " + ketQua + " Euro");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tuổi phải là số nguyên!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        new Bai4_Form();
    }
}