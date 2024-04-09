/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.Color;

/**
 *
 * @author Admin
 */
public class JoinRoomPasswordFrm extends javax.swing.JFrame {
	private int room;
	private String password;

	/**
	 * Creates new form JoinRoomPasswordFrm
	 */
	public JoinRoomPasswordFrm(int room, String password) {
		initComponents();
		this.setTitle("Caro Game Nhóm 2 Diện nè");
		this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.room = room;
		this.password = password;
		btn_roiphong.setIcon(new ImageIcon("assets/icon/door_exit.png"));
	}

	private void initComponents() {

		panel_vaophong = new javax.swing.JPanel();
		lbl_vaophong = new javax.swing.JLabel();
		btn_roiphong = new javax.swing.JButton();
		jtf_matkhau = new javax.swing.JTextField();
		btn_vaophong = new javax.swing.JButton();
		lbl_matkhau = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		panel_vaophong.setBackground(new Color(0, 128, 0));
		panel_vaophong.setForeground(new java.awt.Color(102, 102, 102));

		lbl_vaophong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		lbl_vaophong.setForeground(new java.awt.Color(255, 255, 255));
		lbl_vaophong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbl_vaophong.setText("Vào Phòng ");

		btn_roiphong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_roiphongActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout gl_panel_vaophong = new javax.swing.GroupLayout(panel_vaophong);
		panel_vaophong.setLayout(gl_panel_vaophong);
		gl_panel_vaophong
				.setHorizontalGroup(gl_panel_vaophong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								gl_panel_vaophong.createSequentialGroup().addContainerGap()
										.addComponent(btn_roiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbl_vaophong, javax.swing.GroupLayout.PREFERRED_SIZE, 136,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_vaophong.setVerticalGroup(
				gl_panel_vaophong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								gl_panel_vaophong.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbl_vaophong).addGap(23, 23, 23))
						.addGroup(gl_panel_vaophong.createSequentialGroup().addContainerGap()
								.addComponent(btn_roiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btn_vaophong.setText("Vào phòng");
		btn_vaophong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_vaophongActionPerformed(evt);
			}
		});

		lbl_matkhau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		lbl_matkhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbl_matkhau.setText("Nhập mật khẩu");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(panel_vaophong, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(lbl_matkhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addGap(52, 52, 52)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jtf_matkhau)
								.addComponent(btn_vaophong, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addContainerGap(53, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(panel_vaophong, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lbl_matkhau).addGap(18, 18, 18)
								.addComponent(jtf_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btn_vaophong).addContainerGap(29, Short.MAX_VALUE)));

		pack();
	}

	private void btn_vaophongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		try {
			String password = jtf_matkhau.getText();
			if (!password.equals(this.password))
				throw new Exception("Mật khẩu không chính xác");
			Client.socketHandle.write("join-room," + this.room);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());
		}
	}

	private void btn_roiphongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		Client.closeView(Client.View.JOINROOMPASSWORD);
		Client.openView(Client.View.HOMEPAGE);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btn_vaophong;
	private javax.swing.JButton btn_roiphong;
	private javax.swing.JLabel lbl_vaophong;
	private javax.swing.JLabel lbl_matkhau;
	private javax.swing.JPanel panel_vaophong;
	private javax.swing.JTextField jtf_matkhau;
	// End of variables declaration//GEN-END:variables
}
