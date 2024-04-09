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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterFrm extends JFrame {
	private JTextField jtf_taikhoan;
	private JPasswordField jpf_matkhau;
	private JTextField jtf_nickname;
	private JComboBox<ImageIcon> comboBox_avatar;

	public RegisterFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 374);
		JPanel panel_dangki = new JPanel();
		panel_dangki.setBackground(new Color(46, 139, 87));

		JLabel lbl_taikhoan = new JLabel("Tài Khoản");
		lbl_taikhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_matkhau = new JLabel("Mật Khẩu");
		lbl_matkhau.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_nickname = new JLabel("NickName");
		lbl_nickname.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_chonavatar = new JLabel("Chọn Avatar");
		lbl_chonavatar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_dangki = new JButton("Đăng Kí");
		btn_dangki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_dangkiActionPerformed(e);
			}

		});

		JLabel lbl_dangnhap = new JLabel("Đã có tài khoảnn, đăng nhập tại đây");
		lbl_dangnhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_dangnhap.setForeground(new Color(255, 0, 0));
		lbl_dangnhap.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lbl_dangnhapMouseClicked(evt);
			}

		});

		comboBox_avatar = new JComboBox();
		comboBox_avatar.setMaximumRowCount(5);
		for (int i = 0; i <= 5; i++) {
			comboBox_avatar.addItem(new ImageIcon("assets/avatar/" + i + ".png"));
		}

		jtf_taikhoan = new JTextField();
		jtf_taikhoan.setColumns(10);

		jpf_matkhau = new JPasswordField();

		jtf_nickname = new JTextField();
		jtf_nickname.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_dangki, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addGap(151).addComponent(btn_dangki).addContainerGap(408,
						Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lbl_nickname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lbl_chonavatar, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(
														comboBox_avatar, GroupLayout.PREFERRED_SIZE, 107,
														GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup().addGap(14).addComponent(
														jtf_nickname, GroupLayout.PREFERRED_SIZE, 144,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(162))
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(lbl_taikhoan, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
														.addComponent(lbl_matkhau, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(jtf_taikhoan).addComponent(jpf_matkhau,
																GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
						.addGap(182))
				.addGroup(groupLayout.createSequentialGroup().addGap(97)
						.addComponent(lbl_dangnhap, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(331, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(panel_dangki, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbl_taikhoan).addComponent(
						jtf_taikhoan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(28).addComponent(lbl_matkhau))
						.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(jpf_matkhau,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(lbl_nickname))
						.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(jtf_nickname,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(16)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lbl_chonavatar)
						.addComponent(comboBox_avatar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE).addComponent(btn_dangki).addGap(1)
				.addComponent(lbl_dangnhap).addGap(15)));

		JLabel lbl_dangki = new JLabel("Đăng Kí");
		lbl_dangki.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl_dangki.setForeground(new Color(255, 255, 255));
		lbl_dangki.setBackground(new Color(255, 255, 255));
		GroupLayout gl_panel_dangki = new GroupLayout(panel_dangki);
		gl_panel_dangki.setHorizontalGroup(gl_panel_dangki.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_dangki.createSequentialGroup().addGap(118)
						.addComponent(lbl_dangki, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(388, Short.MAX_VALUE)));
		gl_panel_dangki.setVerticalGroup(gl_panel_dangki.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_dangki.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbl_dangki, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_dangki.setLayout(gl_panel_dangki);
		getContentPane().setLayout(groupLayout);
	}

	private void btn_dangkiActionPerformed(ActionEvent e) {
		try {
			String username = jtf_taikhoan.getText();
			if (username.isEmpty())
				throw new Exception("Vui lòng nhập tên tài khoản");
			String password = String.copyValueOf(jpf_matkhau.getPassword());
			if (password.isEmpty())
				throw new Exception("Vui lòng nhập mật khẩu");
			String nickName = jtf_nickname.getText();
			int avatar = comboBox_avatar.getSelectedIndex();
			if (avatar == -1) {
				throw new Exception("Vui lòng chọn avatar");
			}
			if (nickName.isEmpty())
				throw new Exception("Vui lòng nhập nickname");
			Client.closeAllViews();
			Client.openView(Client.View.GAMENOTICE, "Đăng kí tài khoản", "Đang chờ phản hồi");
			Client.socketHandle.write("register," + username + "," + password + "," + nickName + "," + avatar);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());
		}

	}

	private void lbl_dangnhapMouseClicked(MouseEvent evt) {
		Client.closeView(Client.View.REGISTER);
		Client.openView(Client.View.LOGIN);

	}
//	public static void main(String args[]) {
//		new RegisterFrm().setVisible(true);
//	}
}
