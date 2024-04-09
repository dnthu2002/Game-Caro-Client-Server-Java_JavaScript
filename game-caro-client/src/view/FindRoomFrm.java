package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Client;
import controller.Client.View;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.awt.event.ActionEvent;

public class FindRoomFrm extends JFrame {


	private Timer timer;
    private boolean isFinded;
    private JPanel panel_timphong;
    private JProgressBar progressBar_thoigian;
    private JLabel lbl_thoigian;
    private JLabel lbl_dangtimdoithu;
    private JLabel lbl_datimthaydoithu ;
    private JButton btn_huytimphong;


	public FindRoomFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Caro Game Nhóm 2");
		this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		
		panel_timphong = new JPanel();
		panel_timphong.setBackground(new Color(0, 128, 0));
		
		progressBar_thoigian = new JProgressBar();
		progressBar_thoigian.setForeground(new Color(0, 255, 0));
		
		lbl_thoigian = new JLabel("00:20");
		lbl_thoigian.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lbl_dangtimdoithu = new JLabel("Đang tìm đối thủ");
		lbl_dangtimdoithu.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		lbl_datimthaydoithu = new JLabel("Đã tìm thấy đối thủ, đang vào phòng");
		lbl_datimthaydoithu.setForeground(new Color(255, 0, 0));
		lbl_datimthaydoithu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btn_huytimphong = new JButton();
		btn_huytimphong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_huytimphongActionPerformed(e);
			}


		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_timphong, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(196, Short.MAX_VALUE)
					.addComponent(lbl_thoigian, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(165))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(progressBar_thoigian, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(162, Short.MAX_VALUE)
					.addComponent(lbl_dangtimdoithu, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(139))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(91, Short.MAX_VALUE)
					.addComponent(lbl_datimthaydoithu, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addGap(69))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(366, Short.MAX_VALUE)
					.addComponent(btn_huytimphong, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_timphong, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(lbl_thoigian)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(progressBar_thoigian, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_dangtimdoithu)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl_datimthaydoithu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_huytimphong, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addGap(51))
		);
		
		JLabel lbl_timphong = new JLabel("Tìm Phòng Nhanh");
		lbl_timphong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_timphong.setForeground(new Color(255, 255, 255));
		GroupLayout gl_panel_timphong = new GroupLayout(panel_timphong);
		gl_panel_timphong.setHorizontalGroup(
			gl_panel_timphong.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_timphong.createSequentialGroup()
					.addContainerGap(132, Short.MAX_VALUE)
					.addComponent(lbl_timphong, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(92))
		);
		gl_panel_timphong.setVerticalGroup(
			gl_panel_timphong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_timphong.createSequentialGroup()
					.addGap(18)
					.addComponent(lbl_timphong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel_timphong.setLayout(gl_panel_timphong);
		getContentPane().setLayout(groupLayout);

		btn_huytimphong.setIcon(new ImageIcon("assets/icon/door_exit.png"));
        progressBar_thoigian.setValue(70);
        isFinded = false;
        startFind();
        sendFindRequest();
		
	
	}
    public void stopAllThread(){
        timer.stop();
    }
	private void startFind() {
		lbl_datimthaydoithu.setVisible(false);
		timer = new Timer(1000, new ActionListener() {
			int count =20;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				if(count >=0) {
					if(count >=10) lbl_thoigian.setText("00:"+count);
					else lbl_thoigian.setText("00:0"+count);
					progressBar_thoigian.setValue(Math.round((float) count / 20 *100));
					
				}else {
					((Timer) (e.getSource())).stop();
					try {
						Client.socketHandle.write("cancel-room,");
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(rootPane, e2.getMessage());
					}
					int res = JOptionPane.showConfirmDialog(rootPane, "Tìm kiếm thất bại, bạn muốn thử lại lần nữa chứ?");
					if (res==JOptionPane.YES_OPTION){
                        startFind();
                        sendFindRequest();
                    }
					else{
                        //Có thể hỏi chơi với máy không
                        Client.closeView(Client.View.FINDROOM);
                        Client.openView(Client.View.HOMEPAGE);
                    }
				}
				
			}
			
		});
		timer.setInitialDelay(0);
        timer.start();
		
	}
	private void sendFindRequest() {
		try {
            Client.socketHandle.write("quick-room,");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
		
	}
    public void showFindedRoom(){
        isFinded = true;
        timer.stop();
        lbl_datimthaydoithu.setVisible(true);
        lbl_dangtimdoithu.setVisible(false);
        
    }

	private void btn_huytimphongActionPerformed(ActionEvent e) {
		if(isFinded)return;
		try {
			Client.socketHandle.write("cancel-room,");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(rootPane, e2.getMessage());
		}
		timer.stop();
		Client.closeView(Client.View.FINDROOM);
		Client.openView(Client.View.HOMEPAGE);
	
	}
//	public static void main(String [] args) {
//	new FindRoomFrm().setVisible(true);
//}
}
