package view;

import controller.Client;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.User;
import java.awt.Color;

public class FriendListFrm extends javax.swing.JFrame {
	private List<User> listFriend;
	private boolean isClicked;
	private Thread thread;
	DefaultTableModel defaultTableModel;

	public FriendListFrm() {
		initComponents();
		defaultTableModel = (DefaultTableModel) tbl_danhsachbanbe.getModel();
		this.setTitle("Caro Game Nhóm 5");
		this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		isClicked = false;
		requestUpdate();
		startThread();
	}

	public void stopAllThread() {
		isClicked = true;
	}

	public void startThread() {
		thread = new Thread() {
			@Override
			public void run() {
				while (Client.friendListFrm.isDisplayable() && !isClicked) {
					try {
						System.out.println("Xem danh sách bạn bè đang chạy!");
						requestUpdate();
						Thread.sleep(500);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	public void requestUpdate() {
		try {
			Client.socketHandle.write("view-friend-list,");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());
		}
	}

	public void updateFriendList(List<User> friends) {
		listFriend = friends;
		defaultTableModel.setRowCount(0);
		ImageIcon icon;
		for (User friend : listFriend) {
			if (!friend.isIsOnline()) {
				icon = new ImageIcon("assets/icon/offline.png");
			} else if (friend.isIsPlaying()) {
				icon = new ImageIcon("assets/icon/swords-mini.png");
			} else {
				icon = new ImageIcon("assets/icon/swords-1-mini.png");
			}
			defaultTableModel.addRow(new Object[] { "" + friend.getID(), friend.getNickname(), icon });
		}
	}

	private void initComponents() {

		panel_danhsachbanbe = new javax.swing.JPanel();
		lbl_danhsachbanbe = new javax.swing.JLabel();
		btn_roidanhsachbanbe = new javax.swing.JButton();
		ScrollPane_danhsachbanbe = new javax.swing.JScrollPane();
		Object[][] rows = {};
		String[] columns = { "ID", "Nickname", "" };
		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};
		tbl_danhsachbanbe = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		panel_danhsachbanbe.setBackground(new Color(0, 128, 0));

		lbl_danhsachbanbe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		lbl_danhsachbanbe.setForeground(new java.awt.Color(255, 255, 255));
		lbl_danhsachbanbe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbl_danhsachbanbe.setText("Danh sách bạn bè");

		btn_roidanhsachbanbe.setText("X");
		btn_roidanhsachbanbe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_roidanhsachbanbeActionPerformed(evt);
			}
		});

		tbl_danhsachbanbe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		tbl_danhsachbanbe.setModel(model);
		tbl_danhsachbanbe.setRowHeight(60);
		tbl_danhsachbanbe.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbl_danhsachbanbeMouseClicked(evt);
			}
		});
		ScrollPane_danhsachbanbe.setViewportView(tbl_danhsachbanbe);
		if (tbl_danhsachbanbe.getColumnModel().getColumnCount() > 0) {
			tbl_danhsachbanbe.getColumnModel().getColumn(0).setMinWidth(60);
			tbl_danhsachbanbe.getColumnModel().getColumn(0).setPreferredWidth(60);
			tbl_danhsachbanbe.getColumnModel().getColumn(0).setMaxWidth(60);
			tbl_danhsachbanbe.getColumnModel().getColumn(1).setMinWidth(240);
			tbl_danhsachbanbe.getColumnModel().getColumn(1).setPreferredWidth(240);
			tbl_danhsachbanbe.getColumnModel().getColumn(1).setMaxWidth(240);
			tbl_danhsachbanbe.getColumnModel().getColumn(2).setMinWidth(120);
			tbl_danhsachbanbe.getColumnModel().getColumn(2).setPreferredWidth(120);
			tbl_danhsachbanbe.getColumnModel().getColumn(2).setMaxWidth(120);
		}

		javax.swing.GroupLayout gl_panel_danhsachbanbe = new javax.swing.GroupLayout(panel_danhsachbanbe);
		panel_danhsachbanbe.setLayout(gl_panel_danhsachbanbe);
		gl_panel_danhsachbanbe.setHorizontalGroup(
				gl_panel_danhsachbanbe.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(gl_panel_danhsachbanbe.createSequentialGroup().addComponent(btn_roidanhsachbanbe)
								.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(lbl_danhsachbanbe, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_danhsachbanbe.createSequentialGroup().addContainerGap()
								.addComponent(ScrollPane_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 423,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_danhsachbanbe
				.setVerticalGroup(gl_panel_danhsachbanbe.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								gl_panel_danhsachbanbe.createSequentialGroup().addComponent(btn_roidanhsachbanbe)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbl_danhsachbanbe)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27,
												Short.MAX_VALUE)
										.addComponent(ScrollPane_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE,
												427, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panel_danhsachbanbe,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panel_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btn_roidanhsachbanbeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		Client.closeView(Client.View.FRIENDLIST);
		Client.openView(Client.View.HOMEPAGE);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void tbl_danhsachbanbeMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable1MouseClicked
		try {
			if (tbl_danhsachbanbe.getSelectedRow() == -1)
				return;
			User friend = listFriend.get(tbl_danhsachbanbe.getSelectedRow());
			if (!friend.isIsOnline()) {
				throw new Exception("Người chơi không online");
			}
			if (friend.isIsPlaying()) {
				throw new Exception("Người chơi đang trong trận đấu");
			}
			isClicked = true;
			int res = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thách đấu người bạn này không",
					"Xác nhận thách đầu", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				Client.closeAllViews();
				Client.openView(Client.View.GAMENOTICE, "Thách đấu", "Đang chờ phản hồi từ đối thủ");
				Client.socketHandle.write("duel-request," + friend.getID());
			} else {
				isClicked = false;
				startThread();
			}

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());
		}
	}

	private javax.swing.JButton btn_roidanhsachbanbe;
	private javax.swing.JLabel lbl_danhsachbanbe;
	private javax.swing.JPanel panel_danhsachbanbe;
	private javax.swing.JScrollPane ScrollPane_danhsachbanbe;
	private javax.swing.JTable tbl_danhsachbanbe;
}
