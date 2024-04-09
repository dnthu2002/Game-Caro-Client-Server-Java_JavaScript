package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Client;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrm extends JFrame {
	private JTextField jtf_taikhoan;
	private JPasswordField jpf_matkhau;

	public void showError(String message) {
		JOptionPane.showMessageDialog(rootPane, message);
	}

	public void log(String message) {
		JOptionPane.showMessageDialog(rootPane, "ID của server thread là:" + message);
	}

	public LoginFrm(String taiKhoan, String matKhau) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 282);
        this.setIconImage(new ImageIcon("assets/image/caroicon.jpg").getImage());


		JPanel jpanel_dangnhap = new JPanel();
		jpanel_dangnhap.setBackground(new Color(60, 179, 113));

		jtf_taikhoan = new JTextField();
		jtf_taikhoan.setColumns(10);

		jpf_matkhau = new JPasswordField();

		JLabel lblNewLabel = new JLabel("Tài khoản ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_dangnhap = new JButton("Đăng Nhập");
		btn_dangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_dangnhapActionperformed(e);
			}

		});
		btn_dangnhap.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_dangki = new JButton("Đăng Kí");
		btn_dangki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_dangkiActionPerformed(e);
			}

		});
		btn_dangki.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jpanel_dangnhap, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jpf_matkhau, Alignment.LEADING).addComponent(jtf_taikhoan,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
								.addContainerGap(60, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addContainerGap(111, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btn_dangki, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btn_dangnhap, Alignment.LEADING))
										.addGap(93)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup()
						.addComponent(jpanel_dangnhap, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGap(36)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtf_taikhoan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jpf_matkhau, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addGap(18).addComponent(btn_dangnhap).addGap(18).addComponent(btn_dangki)
						.addContainerGap(19, Short.MAX_VALUE)));

		JLabel lb_dangnhap = new JLabel("ĐĂNG NHẬP");
		lb_dangnhap.setForeground(new Color(248, 248, 255));
		lb_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_jpanel_dangnhap = new GroupLayout(jpanel_dangnhap);
		gl_jpanel_dangnhap.setHorizontalGroup(gl_jpanel_dangnhap.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanel_dangnhap.createSequentialGroup().addGap(103)
						.addComponent(lb_dangnhap, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(99, Short.MAX_VALUE)));
		gl_jpanel_dangnhap.setVerticalGroup(gl_jpanel_dangnhap.createParallelGroup(Alignment.LEADING)
				.addComponent(lb_dangnhap, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE));
		jpanel_dangnhap.setLayout(gl_jpanel_dangnhap);
		getContentPane().setLayout(groupLayout);
	}

	private void btn_dangnhapActionperformed(ActionEvent e) {
		try {
			String taiKhoan = jtf_taikhoan.getText();
			if (taiKhoan.isEmpty())
				throw new Exception("Vui lòng nhập tên tài khoản");
			String matKhau = String.copyValueOf(jpf_matkhau.getPassword());
			if (matKhau.isEmpty())
				throw new Exception("Vui lòng nhập mật khẩu");
			Client.closeAllViews();
			Client.openView(Client.View.GAMENOTICE, "Đăng nhập", "Đang xác thực thông tin đăng nhập");
			Client.socketHandle.write("client-verify," + taiKhoan + "," + matKhau);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(rootPane, e2.getMessage());
		}

	}

	private void btn_dangkiActionPerformed(ActionEvent e) {
		Client.closeView(Client.View.LOGIN);
		Client.openView(Client.View.REGISTER);

	}
//	public static void main(String args[]) {
//		new LoginFrm(null,null).setVisible(true);
//	}
}
