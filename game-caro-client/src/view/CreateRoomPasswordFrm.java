package view;

import controller.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.Color;


public class CreateRoomPasswordFrm extends javax.swing.JFrame {


    public CreateRoomPasswordFrm() {
        initComponents();
        this.setTitle("Caro Game Nhóm 2 Diện nè");
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        btn_roitaophong.setIcon(new ImageIcon("assets/icon/door_exit.png"));
    }
    private void initComponents() {

        panel_taophong = new javax.swing.JPanel();
        lbl_taophong = new javax.swing.JLabel();
        jtf_matkhau = new javax.swing.JTextField();
        lbl_matkhau = new javax.swing.JLabel();
        btn_taophong = new javax.swing.JButton();
        btn_roitaophong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_taophong.setBackground(new Color(0, 128, 0));

        lbl_taophong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_taophong.setForeground(new java.awt.Color(255, 255, 255));
        lbl_taophong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_taophong.setText("Tạo phòng");

        javax.swing.GroupLayout gl_panel_taophong = new javax.swing.GroupLayout(panel_taophong);
        panel_taophong.setLayout(gl_panel_taophong);
        gl_panel_taophong.setHorizontalGroup(
            gl_panel_taophong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_taophong, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );
        gl_panel_taophong.setVerticalGroup(
            gl_panel_taophong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_taophong.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_taophong)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lbl_matkhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_matkhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_matkhau.setText("Nhập mật khẩu");

        btn_taophong.setText("Tạo");
        btn_taophong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_taophongActionPerformed(evt);
            }
        });

        btn_roitaophong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_roitaophongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_taophong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_matkhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jtf_matkhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_taophong, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_roitaophong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_taophong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbl_matkhau)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_taophong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_roitaophong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btn_roitaophongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Client.closeView(Client.View.CREATEROOMPASSWORD);
        Client.openView(Client.View.HOMEPAGE);
    }

    private void btn_taophongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String password = jtf_matkhau.getText();
            if(password.isEmpty())
                throw new Exception("Vui lòng nhập mật khẩu bạn muốn đặt cho phòng");
            Client.socketHandle.write("create-room,"+password);
            Client.closeView(Client.View.CREATEROOMPASSWORD);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    
    private javax.swing.JButton btn_roitaophong;
    private javax.swing.JButton btn_taophong;
    private javax.swing.JLabel lbl_taophong;
    private javax.swing.JLabel lbl_matkhau;
    private javax.swing.JPanel panel_taophong;
    private javax.swing.JTextField jtf_matkhau;

}
