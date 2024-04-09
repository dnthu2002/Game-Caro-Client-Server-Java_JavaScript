package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Room;
import controller.Server;
import controller.ServerThread;
import dao.UserDAO;
import model.User;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Admin extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private JTextField jtf_chattoanserver;
	private JTextField jtf_idnguoidung;

	/**
	 * Launch the application.
	 */
	


	/**
	 * Create the frame.
	 */
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 520);
        setIconImage(new ImageIcon("image/iconcaroserver.jpg").getImage());

		JPanel panel_admin = new JPanel();
		panel_admin.setBackground(new Color(0, 128, 0));
		
		scrollPane_xemdanhsachluong_dahsachphong = new JScrollPane();
		
		btn_xemdanhsachluong = new JButton("Xem danh sách luồng");
		btn_xemdanhsachluong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_xemdanhsachluong_ActionPerformed(e);
			}

			
		});
		btn_xemdanhsachluong.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btn_xemdanhsachphong = new JButton("Xem danh sách phòng");
		btn_xemdanhsachphong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_xemdanhsachphong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_xemdanhsachphong_ActionPerformed(e);
			}

		});
		
		scrollPane_xemdanhsachonline_chat = new JScrollPane();
		
		jtf_chattoanserver = new JTextField();
		jtf_chattoanserver.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				jtf_chattoanserver_keyPressed(e);
			}
		});
		jtf_chattoanserver.setColumns(10);
		
		btn_chattoanserver = new JButton("Chat Toàn Server");
		btn_chattoanserver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_chattoanserver_ActionPerformed(e);
			}

		});
		
		jtf_idnguoidung = new JTextField();
		jtf_idnguoidung.setColumns(10);
		
		comboBox_lydobichan = new JComboBox<>();
		comboBox_lydobichan.setModel(new DefaultComboBoxModel<>(new String[] {"Chọn lý do", "Ngôn ngữ thô tục - xúc phạm người khác", "Spam đăng nhập", "Sử dụng game với mục đích xấu", "Phát hiện rò rỉ bảo mật - tài khoản tạm thời bị khoá để kiểm tra thêm"}));
		
		btn_huychan = new JButton("Hủy Chặn");
		btn_huychan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_huychan_ActionPerformed(e);
			}

		});
		
		btn_canhcao = new JButton("Cảnh Cáo");
		btn_canhcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_canhcao_ActionPerformed(e);
}
		});
		
		btn_chan = new JButton("Chặn");
		btn_chan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_chan_ActionPerformed(e);
			}

		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(btn_xemdanhsachluong, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addGap(176)
					.addComponent(btn_xemdanhsachphong, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addGap(44))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(42, Short.MAX_VALUE)
							.addComponent(scrollPane_xemdanhsachluong_dahsachphong, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jtf_chattoanserver, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btn_chattoanserver, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
								.addComponent(scrollPane_xemdanhsachonline_chat, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jtf_idnguoidung, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboBox_lydobichan, 0, 275, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btn_huychan)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_canhcao, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_chan)))))
					.addGap(24))
				.addComponent(panel_admin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_admin, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_xemdanhsachluong_dahsachphong, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_xemdanhsachluong, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btn_xemdanhsachphong, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
					.addGap(22)
					.addComponent(scrollPane_xemdanhsachonline_chat, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtf_chattoanserver, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_chattoanserver, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_chan, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_canhcao, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_huychan, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBox_lydobichan, Alignment.LEADING)
							.addComponent(jtf_idnguoidung, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
					.addGap(23))
		);
		
		textArea_xemdanhsachonline_chat = new JTextArea();
		scrollPane_xemdanhsachonline_chat.setViewportView(textArea_xemdanhsachonline_chat);
		
		textArea_xemdanhsachluong_danhsachphong = new JTextArea();
		scrollPane_xemdanhsachluong_dahsachphong.setViewportView(textArea_xemdanhsachluong_danhsachphong);
		
		JLabel jlb_admin = new JLabel("ADMIN SERVER");
		jlb_admin.setForeground(new Color(255, 255, 255));
		jlb_admin.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_admin.setBackground(Color.WHITE);
		jlb_admin.setFont(new Font("STXinwei", Font.BOLD, 20));
		GroupLayout gl_panel_admin = new GroupLayout(panel_admin);
		gl_panel_admin.setHorizontalGroup(
			gl_panel_admin.createParallelGroup(Alignment.LEADING)
				.addComponent(jlb_admin, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
		);
		gl_panel_admin.setVerticalGroup(
			gl_panel_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_admin.createSequentialGroup()
					.addGap(23)
					.addComponent(jlb_admin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(16))
		);
		panel_admin.setLayout(gl_panel_admin);
		getContentPane().setLayout(groupLayout);
		
		
	}
	private void btn_xemdanhsachluong_ActionPerformed(ActionEvent e) {
		String res = "";
		String room = "";
		int i = 1;
		for(ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if(serverThread.getRoom() == null) room = null;
			else room = "" + serverThread.getRoom().getID();
			if(serverThread.getUser()!=null) {
				res += i + ". Client-number: "+serverThread.getClientNumber()+", User-ID: "+serverThread.getUser().getID()+",Room: "+room+"\n";
			}else {
				res+=i+". Client-number: "+serverThread.getClientNumber()+", User-ID: null, Room: "+room+"\n";
			}
			i++;
		}
		textArea_xemdanhsachluong_danhsachphong.setText(res);
		
	}

	private void btn_xemdanhsachphong_ActionPerformed(ActionEvent e) {
		String res ="";
		int i = 1;
		for(ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			Room room1 = serverThread.getRoom();
			String listUser = "List user ID: ";
			if(room1 != null) {
				if(room1.getNumberOfUser() ==1) {
					listUser +=room1.getUser1().getUser().getID();
				}else {
					listUser +=room1.getUser1().getUser().getID() + ", "+ room1.getUser2().getUser().getID();
				}
				res += i+ ". Room_ID: "+room1.getID() + ",Number of player: "+ room1.getNumberOfUser() + ", "+listUser + "\n";
				i++;
			}		
		}
		textArea_xemdanhsachluong_danhsachphong.setText(res);
		
	}

	private void btn_chattoanserver_ActionPerformed(ActionEvent e) {
		sendMessage();
		
	}
	

	private void jtf_chattoanserver_keyPressed(KeyEvent e) {
		if(e.getKeyCode() ==10) {
			sendMessage();
		}
		
	}

	private void btn_huychan_ActionPerformed(ActionEvent e) {
		try {
            if(jtf_idnguoidung.getText().length()==0){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ID của User");
                return;
            }
            int userId = Integer.parseInt(jtf_idnguoidung.getText());
            User user = new User();
            user.setID(userId);
            userDAO.updateBannedStatus(user, false);
            jtf_idnguoidung.setText("");
            JOptionPane.showMessageDialog(rootPane, "Đã huỷ BAN user "+userId);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
        }
		
	}
	

	private void btn_canhcao_ActionPerformed(ActionEvent e) {
        try {
            if(jtf_idnguoidung.getText().length()==0){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ID của User");
                return;
            }
            if(comboBox_lydobichan.getSelectedIndex()<1){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn lý do");
                return;
            }
            int userId = Integer.parseInt(jtf_idnguoidung.getText());
            Server.serverThreadBus.sendMessageToUserID(userId, "warning-notice,"+comboBox_lydobichan.getSelectedItem());
            JOptionPane.showMessageDialog(rootPane, "Đã cảnh cáo user "+userId);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
        }
		
	}

	private void btn_chan_ActionPerformed(ActionEvent e) {
		try {
            if(jtf_idnguoidung.getText().length()==0){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ID của User");
                return;
            }
            if(comboBox_lydobichan.getSelectedIndex()<1){
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn lý do");
                return;
            }
            int userId = Integer.parseInt(jtf_idnguoidung.getText());
            User user = new User();
            user.setID(userId);
            userDAO.updateBannedStatus(user, true);
            ServerThread serverThread = Server.serverThreadBus.getServerThreadByUserID(userId);
            serverThread.write("banned-notice,"+comboBox_lydobichan.getSelectedItem());
            if(serverThread.getRoom()!=null){
                Room room = serverThread.getRoom();
                ServerThread competitorThread = room.getCompetitor(serverThread.getClientNumber());
                room.setUsersToNotPlaying();
                if(competitorThread!=null){
                    room.decreaseNumberOfGame();
                    competitorThread.write("left-room,");
                    competitorThread.setRoom(null);
                }
                serverThread.setRoom(null);
            }
            Server.admin.addMessage("User có ID "+ userId+" đã bị BAN");
            serverThread.setUser(null);
            Server.serverThreadBus.boardCast(-1, "chat-server,"+"User có ID "+ userId+" đã bị BAN");
            JOptionPane.showMessageDialog(rootPane, "Đã BAN user "+userId);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
        }
		
	}
	private void sendMessage() {
		String message = jtf_chattoanserver.getText();
		if(message.length()==0) return;
		String temp = textArea_xemdanhsachonline_chat.getText();
		temp+= "Thông báo này từ máy chủ : "+message+"\n";
		textArea_xemdanhsachonline_chat.setText(temp);
		textArea_xemdanhsachonline_chat.setCaretPosition(textArea_xemdanhsachonline_chat.getDocument().getLength());
        Server.serverThreadBus.boardCast(-1,"chat-server,Thông báo từ máy chủ : "+ message);
        jtf_chattoanserver.setText("");
		
	}
	public void addMessage(String message) {
        String tmp = textArea_xemdanhsachonline_chat.getText();
        tmp=tmp+message+"\n";
        textArea_xemdanhsachonline_chat.setText(tmp);
        textArea_xemdanhsachonline_chat.setCaretPosition(textArea_xemdanhsachluong_danhsachphong.getDocument().getLength());
    }
	private JButton btn_xemdanhsachluong;
	private JScrollPane scrollPane_xemdanhsachluong_dahsachphong;
	private JButton btn_xemdanhsachphong;
	private JScrollPane scrollPane_xemdanhsachonline_chat;
	private JButton btn_chattoanserver;
	private JComboBox<String> comboBox_lydobichan;
	private JButton btn_huychan;
	private JButton btn_canhcao;
	private JButton btn_chan;
	private JTextArea textArea_xemdanhsachluong_danhsachphong;
	private static JTextArea textArea_xemdanhsachonline_chat;
	public void run() {
		new Admin().setVisible(true);
	}
}
