package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Client;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class HomePageFrm extends JFrame {
	private JTextArea textArea_tinnhan;

	public HomePageFrm() {
		this.setTitle("Caro Game Nhóm 2 Diện Nè");
		this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 517, 622);

		JLabel lbl_gamcaro = new JLabel("GAME CARO");
		lbl_gamcaro.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JPanel panel_gamecaro = new JPanel();
		panel_gamecaro.setBackground(new Color(0, 128, 0));

		JScrollPane scrollPane_tinnhan = new JScrollPane();

		jtf_noidungtinnhan = new JTextField();
		jtf_noidungtinnhan.setColumns(10);

		JButton btn_gui = new JButton();
		btn_gui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_guiActionPerformed(e);
			}


		});
		btn_gui.setForeground(new Color(255, 255, 255));
		btn_gui.setBackground(new Color(0, 128, 0));

		JButton btn_choinhanh = new JButton("Chơi nhanh");
		btn_choinhanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_choinhanhActionperformed(e);
			}

		});
		btn_choinhanh.setForeground(new Color(255, 255, 255));
		btn_choinhanh.setBackground(new Color(0, 128, 0));
		btn_choinhanh.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_timphong = new JButton("Tìm phòng");
		btn_timphong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_timphongActionPerformed(e);
			}

		});
		btn_timphong.setForeground(new Color(255, 255, 255));
		btn_timphong.setBackground(new Color(0, 128, 0));
		btn_timphong.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_bangxephang = new JButton("Bảng xếp hạng");
		btn_bangxephang.setForeground(new Color(255, 255, 255));
		btn_bangxephang.setBackground(new Color(0, 128, 0));
		btn_bangxephang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_bangxephang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_bangxephangActionPerformed(e);
			}

		});

		JButton btn_vaophong = new JButton("Vào phòng");
		btn_vaophong.setForeground(new Color(255, 255, 255));
		btn_vaophong.setBackground(new Color(0, 128, 0));
		btn_vaophong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_vaophong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_vaophongActionPerformed(e);
			}

		});

		JButton btn_taophong = new JButton("Tạo phòng");
		btn_taophong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_taophongActionPerformed(e);
			}

		});
		btn_taophong.setForeground(new Color(255, 255, 255));
		btn_taophong.setBackground(new Color(0, 128, 0));
		btn_taophong.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_choivoimay = new JButton("Chơi với máy");
		btn_choivoimay.setForeground(new Color(255, 255, 255));
		btn_choivoimay.setBackground(new Color(0, 128, 0));
		btn_choivoimay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_choivoimay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_choivoimayActionPerformed(e);
			}

		});

		JButton btn_danhsachbanbe = new JButton("Danh sách bạn bè");
		btn_danhsachbanbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_danhsachbanbeActionPerformed(e);
			}

		});
		btn_danhsachbanbe.setForeground(new Color(255, 255, 255));
		btn_danhsachbanbe.setBackground(new Color(0, 128, 0));
		btn_danhsachbanbe.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_dangxuat = new JButton("Đăng xuất");
		btn_dangxuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_dangxuatActionPermed(e);
			}

		});
		btn_dangxuat.setForeground(new Color(255, 255, 255));
		btn_dangxuat.setBackground(new Color(0, 128, 0));
		btn_dangxuat.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btn_thoatgame = new JButton("Thoát game");
		btn_thoatgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_thoatgameActionPerformed(e);
			}


		});
		btn_thoatgame.setForeground(new Color(255, 255, 255));
		btn_thoatgame.setBackground(new Color(0, 128, 0));
		btn_thoatgame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_gamecaro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_tinnhan, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jtf_noidungtinnhan, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_gui, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btn_bangxephang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_timphong, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_choinhanh, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_vaophong, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_choivoimay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_dangxuat, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_danhsachbanbe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btn_thoatgame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btn_taophong, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
									.addGap(3)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbl_gamcaro, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(174))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_gamcaro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_gamecaro, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_tinnhan, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtf_noidungtinnhan, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_gui))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_choinhanh, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_timphong, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_bangxephang, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_taophong, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_danhsachbanbe, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_thoatgame, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_vaophong, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_choivoimay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_dangxuat, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);

		textArea_tinnhan = new JTextArea();
		textArea_tinnhan.setText("==> Tin nhắn và Tin tức <==\n");
		scrollPane_tinnhan.setViewportView(textArea_tinnhan);

		JLabel lbl_avatar = new JLabel();

		JLabel lbl_nickname = new JLabel("Nick Name");
		lbl_nickname.setForeground(new Color(255, 255, 255));
		lbl_nickname.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_sovandachoi = new JLabel("Số ván đã chơi");
		lbl_sovandachoi.setForeground(new Color(255, 255, 255));
		lbl_sovandachoi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_sovanthang = new JLabel("Số ván thắng");
		lbl_sovanthang.setForeground(new Color(255, 255, 255));
		lbl_sovanthang.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_sovanhoa = new JLabel("Số ván hòa");
		lbl_sovanhoa.setForeground(new Color(255, 255, 255));
		lbl_sovanhoa.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_tilethang = new JLabel("Tỉ lệ thắng");
		lbl_tilethang.setForeground(new Color(255, 255, 255));
		lbl_tilethang.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_diem = new JLabel("Điểm");
		lbl_diem.setForeground(new Color(255, 255, 255));
		lbl_diem.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_xephang = new JLabel("Xếp hạng");
		lbl_xephang.setForeground(new Color(255, 255, 255));
		lbl_xephang.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_nickname1 = new JLabel("{đây là nickname}");
		lbl_nickname1.setForeground(new Color(255, 255, 255));
		lbl_nickname1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_sovandachoi1 = new JLabel("0");
		lbl_sovandachoi1.setForeground(new Color(255, 255, 255));
		lbl_sovandachoi1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_sovanthang1 = new JLabel("0");
		lbl_sovanthang1.setForeground(new Color(255, 255, 255));
		lbl_sovanthang1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_sovanhoa1 = new JLabel("0");
		lbl_sovanhoa1.setForeground(new Color(255, 255, 255));
		lbl_sovanhoa1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_tilethang1 = new JLabel("{đây là tỉ lệ thắng}");
		lbl_tilethang1.setForeground(new Color(255, 255, 255));
		lbl_tilethang1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_diem1 = new JLabel("0");
		lbl_diem1.setForeground(new Color(255, 255, 255));
		lbl_diem1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_xephang1 = new JLabel("0");
		lbl_xephang1.setForeground(new Color(255, 255, 255));
		lbl_xephang1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel_gamecaro = new GroupLayout(panel_gamecaro);
		gl_panel_gamecaro.setHorizontalGroup(gl_panel_gamecaro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_gamecaro.createSequentialGroup().addGap(20)
						.addComponent(lbl_avatar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lbl_nickname, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lbl_xephang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lbl_sovandachoi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lbl_sovanthang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lbl_sovanhoa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lbl_tilethang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lbl_diem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(54)
						.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_sovandachoi1, GroupLayout.PREFERRED_SIZE, 45,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_sovanthang1, GroupLayout.PREFERRED_SIZE, 45,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_sovanhoa1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_diem1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_xephang1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_nickname1).addComponent(lbl_tilethang1))
						.addContainerGap(16, Short.MAX_VALUE)));
		gl_panel_gamecaro.setVerticalGroup(gl_panel_gamecaro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_gamecaro.createSequentialGroup().addGap(26)
						.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lbl_avatar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING,
										gl_panel_gamecaro.createSequentialGroup()
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_nickname).addComponent(lbl_nickname1))
												.addGap(10)
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_sovandachoi).addComponent(lbl_sovandachoi1))
												.addGap(18)
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_sovanthang).addComponent(lbl_sovanthang1))
												.addGap(18)
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_sovanhoa).addComponent(lbl_sovanhoa1))
												.addGap(18)
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_tilethang).addComponent(lbl_tilethang1))
												.addGap(18)
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_diem).addComponent(lbl_diem1))
												.addGap(18)
												.addGroup(gl_panel_gamecaro.createParallelGroup(Alignment.BASELINE)
														.addComponent(lbl_xephang).addComponent(lbl_xephang1))))
						.addContainerGap(19, Short.MAX_VALUE)));
		panel_gamecaro.setLayout(gl_panel_gamecaro);
		getContentPane().setLayout(groupLayout);

		lbl_avatar.setIcon(new ImageIcon("assets/avatar/" + Client.user.getAvatar() + ".gif"));
//		System.out.println(new ImageIcon(this.getClass().getResource("/2.gif")));
		lbl_nickname1.setText(Client.user.getNickname());
		lbl_sovandachoi1.setText(Integer.toString(Client.user.getNumberOfGame()));
		lbl_sovanthang1.setText(Integer.toString(Client.user.getNumberOfWin()));
		lbl_sovanhoa1.setText(Integer.toString(Client.user.getNumberOfDraw()));
		if (Client.user.getNumberOfGame() == 0) {
			lbl_tilethang1.setText("-");
		} else {
			lbl_tilethang1.setText(
					String.format("%.2f", (float) Client.user.getNumberOfWin() / Client.user.getNumberOfGame() * 100)
							+ "%");
		}
		lbl_diem1.setText("" + (Client.user.getNumberOfGame() + Client.user.getNumberOfWin() * 10));
		lbl_xephang1.setText("" + Client.user.getRank());
		btn_gui.setIcon(new ImageIcon("assets/image/send2.png"));

	}

	private JTextField jtf_noidungtinnhan;

	public void addMessage(String message) {
		String temp = textArea_tinnhan.getText();
		temp += message + "\n";
		textArea_tinnhan.setText(temp);
		textArea_tinnhan.setCaretPosition(textArea_tinnhan.getDocument().getLength());
	}

	private void btn_choinhanhActionperformed(ActionEvent e) {
		Client.closeView(Client.View.HOMEPAGE);
		Client.openView(Client.View.FINDROOM);

	}

	private void btn_vaophongActionPerformed(ActionEvent e) {
		Client.openView(Client.View.ROOMNAMEFRM);

	}

	private void btn_taophongActionPerformed(ActionEvent e) {
		int res = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đặt mật khẩu cho phòng không?", "Tạo phòng",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			Client.closeView(Client.View.HOMEPAGE);
			Client.openView(Client.View.CREATEROOMPASSWORD);
		} else if (res == JOptionPane.NO_OPTION) {
			try {
				Client.socketHandle.write("create-room,");
				Client.closeView(Client.View.HOMEPAGE);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(rootPane, ex.getMessage());
			}
		}

	}

	private void btn_timphongActionPerformed(ActionEvent e) {
		try {
			Client.closeView(Client.View.HOMEPAGE);
			Client.openView(Client.View.ROOMLIST);
			Client.socketHandle.write("view-room-list,");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(rootPane, ex.getMessage());
		}

	}

	private void btn_choivoimayActionPerformed(ActionEvent e) {
		Client.openView(Client.View.GAMEAI);
	}

	private void btn_danhsachbanbeActionPerformed(ActionEvent e) {
		Client.closeView(Client.View.HOMEPAGE);
        Client.openView(Client.View.FRIENDLIST);
		
	}

	private void btn_bangxephangActionPerformed(ActionEvent e) {
		Client.openView(Client.View.RANK);
		
	}

	private void btn_dangxuatActionPermed(ActionEvent e) {
		try {
            Client.socketHandle.write("offline,"+Client.user.getID());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        Client.closeView(Client.View.HOMEPAGE);
        Client.openView(Client.View.LOGIN);
		
	}
	private void btn_thoatgameActionPerformed(ActionEvent e) {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		
	}
	private void btn_guiActionPerformed(ActionEvent e) {
		sendMessage();
		
	}
    private void sendMessage(){
        try {
            if (jtf_noidungtinnhan.getText().isEmpty()) {
                throw new Exception("Vui lòng nhập nội dung tin nhắn");
            }
            String temp = textArea_tinnhan.getText();
            temp += "Tôi: " + jtf_noidungtinnhan.getText() + "\n";
            textArea_tinnhan.setText(temp);
            Client.socketHandle.write("chat-server," + jtf_noidungtinnhan.getText());
            jtf_noidungtinnhan.setText("");
            textArea_tinnhan.setCaretPosition(textArea_tinnhan.getDocument().getLength());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }
//	public static void main(String [] args) {
//		new HomePageFrm().setVisible(true);
//	}
}
