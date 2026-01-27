import javax.swing.*;
import java.awt.*;

public class Bai5_Form extends JFrame {
    private JTextField txtMaKH, txtHoTen, txtEmail, txtSDT, txtNgaySinh;
    private JTextArea txtDiaChi;
    private JPasswordField txtMatKhau, txtXacNhanMK;
    private JRadioButton rdoNam, rdoNu, rdoKhac;
    private ButtonGroup grpGioiTinh;
    private JCheckBox chkDieuKhoan;
    private JButton btnDangKy, btnNhapLai;

    public Bai5_Form() {
        setTitle("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel lblTitle = new JLabel("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);


        gbc.gridwidth = 1;


        addFormRow(panel, gbc, 1, "Mã Khách Hàng *", txtMaKH = new JTextField(20));
        txtMaKH.setToolTipText("6-10 ký tự, chỉ chữ và số");

        addFormRow(panel, gbc, 2, "Họ và Tên *", txtHoTen = new JTextField(20));
        addFormRow(panel, gbc, 3, "Email *", txtEmail = new JTextField(20));
        addFormRow(panel, gbc, 4, "Số điện thoại *", txtSDT = new JTextField(20));


        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Địa chỉ *", SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        txtDiaChi = new JTextArea(3, 20);
        txtDiaChi.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(txtDiaChi, gbc);

        addFormRow(panel, gbc, 6, "Mật khẩu *", txtMatKhau = new JPasswordField(20));
        addFormRow(panel, gbc, 7, "Xác nhận Mật khẩu *", txtXacNhanMK = new JPasswordField(20));
        addFormRow(panel, gbc, 8, "Ngày sinh (yyyy-MM-dd)", txtNgaySinh = new JTextField(20));


        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Giới tính", SwingConstants.RIGHT), gbc);

        JPanel pnlGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        rdoKhac = new JRadioButton("Khác");
        grpGioiTinh = new ButtonGroup();
        grpGioiTinh.add(rdoNam); grpGioiTinh.add(rdoNu); grpGioiTinh.add(rdoKhac);
        rdoNam.setSelected(true);
        pnlGioiTinh.add(rdoNam); pnlGioiTinh.add(rdoNu); pnlGioiTinh.add(rdoKhac);

        gbc.gridx = 1;
        panel.add(pnlGioiTinh, gbc);


        gbc.gridx = 1; gbc.gridy = 10;
        chkDieuKhoan = new JCheckBox("Tôi đồng ý với các điều khoản dịch vụ *");
        panel.add(chkDieuKhoan, gbc);


        JPanel pnlButton = new JPanel();
        btnDangKy = new JButton("Đăng ký");
        btnDangKy.setBackground(new Color(0, 102, 204));
        btnDangKy.setForeground(Color.BLACK);

        btnNhapLai = new JButton("Nhập lại");
        btnNhapLai.setBackground(Color.GRAY);
        btnNhapLai.setForeground(Color.black);

        pnlButton.add(btnDangKy);
        pnlButton.add(btnNhapLai);

        gbc.gridx = 0; gbc.gridy = 11; gbc.gridwidth = 2;
        panel.add(pnlButton, gbc);


        add(panel);


        btnDangKy.addActionListener(e -> xuLyDangKy());
        btnNhapLai.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void addFormRow(JPanel p, GridBagConstraints gbc, int row, String label, Component comp) {
        gbc.gridx = 0; gbc.gridy = row;
        p.add(new JLabel(label, SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        p.add(comp, gbc);
    }

    private void resetForm() {
        txtMaKH.setText(""); txtHoTen.setText(""); txtEmail.setText("");
        txtSDT.setText(""); txtDiaChi.setText(""); txtMatKhau.setText("");
        txtXacNhanMK.setText(""); txtNgaySinh.setText("");
        rdoNam.setSelected(true);
        chkDieuKhoan.setSelected(false);
    }

    private void xuLyDangKy() {

        String gioitinh = "Nam";
        if (rdoNu.isSelected()) gioitinh = "Nữ";
        if (rdoKhac.isSelected()) gioitinh = "Khác";

        Bai5_KhachHang kh = new Bai5_KhachHang(
                txtMaKH.getText(), txtHoTen.getText(), txtEmail.getText(),
                txtSDT.getText(), txtDiaChi.getText(),
                new String(txtMatKhau.getPassword()),
                new String(txtXacNhanMK.getPassword()),
                txtNgaySinh.getText(),
                gioitinh,
                chkDieuKhoan.isSelected()
        );

        String ketQua = Bai5.dangKy(kh);
        if (ketQua.contains("thành công")) {
            JOptionPane.showMessageDialog(this, ketQua);
        } else {
            JOptionPane.showMessageDialog(this, ketQua, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        new Bai5_Form();
    }
}