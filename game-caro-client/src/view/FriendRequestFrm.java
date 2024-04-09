/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.awt.Color;

/**
 *
 * @author Admin
 */
public class FriendRequestFrm extends javax.swing.JFrame {
    private int ID;
    private Timer timer;
    /**
     * Creates new form FriendRequestFrm
     */
    public FriendRequestFrm(int ID, String nickname) {
        this.ID = ID;
        initComponents();
        this.setTitle("Caro Game Nhóm 5");
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        lbl_nguoiketban.setText("Từ "+nickname +"(ID="+ID+")");
        timer = new Timer(1000, new ActionListener() {
            int count = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 0) {
                    lbl_tgianhethanloimoi.setText("Tự động đóng trong " + count);
                } else {
                    ((Timer) (e.getSource())).stop();
                    disposeFrame();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
    public void disposeFrame(){
        this.dispose();
    }
    private void initComponents() {

        panel_yeucauketban = new javax.swing.JPanel();
        lbl_yeucauketban = new javax.swing.JLabel();
        lbl_loimoiketban = new javax.swing.JLabel();
        lbl_nguoiketban = new javax.swing.JLabel();
        btn_dongy = new javax.swing.JButton();
        btn_tuchoi = new javax.swing.JButton();
        lbl_tgianhethanloimoi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_yeucauketban.setBackground(new Color(0, 128, 0));

        lbl_yeucauketban.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_yeucauketban.setForeground(new java.awt.Color(255, 255, 255));
        lbl_yeucauketban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_yeucauketban.setText("Yêu cầu kết bạn");

        javax.swing.GroupLayout gl_panel_yeucauketban = new javax.swing.GroupLayout(panel_yeucauketban);
        panel_yeucauketban.setLayout(gl_panel_yeucauketban);
        gl_panel_yeucauketban.setHorizontalGroup(
            gl_panel_yeucauketban.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_yeucauketban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        gl_panel_yeucauketban.setVerticalGroup(
            gl_panel_yeucauketban.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gl_panel_yeucauketban.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(lbl_yeucauketban)
                .addGap(28, 28, 28))
        );

        lbl_loimoiketban.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_loimoiketban.setForeground(new java.awt.Color(0, 102, 204));
        lbl_loimoiketban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_loimoiketban.setText("Bạn nhận được một lời mời kết bạn ");

        lbl_nguoiketban.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nguoiketban.setForeground(new java.awt.Color(0, 102, 204));
        lbl_nguoiketban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nguoiketban.setText("Từ");

        btn_dongy.setText("Đồng ý");
        btn_dongy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_dongyActionPerformed(evt);
            }
        });

        btn_tuchoi.setText("Từ chối");
        btn_tuchoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_tuchoiActionPerformed(evt);
            }
        });

        lbl_tgianhethanloimoi.setText("Tự động đóng sau ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_yeucauketban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_loimoiketban, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_nguoiketban, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btn_dongy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_tuchoi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(76, Short.MAX_VALUE))
                    .addComponent(lbl_tgianhethanloimoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_yeucauketban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_loimoiketban)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nguoiketban)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dongy)
                    .addComponent(btn_tuchoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_tgianhethanloimoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dongyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            timer.stop();
            Client.socketHandle.write("make-friend-confirm,"+ID);
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_tuchoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        timer.stop();
        this.dispose();
    }
    private javax.swing.JButton btn_dongy;
    private javax.swing.JButton btn_tuchoi;
    private javax.swing.JLabel lbl_yeucauketban;
    private javax.swing.JLabel lbl_loimoiketban;
    private javax.swing.JLabel lbl_tgianhethanloimoi;
    private javax.swing.JLabel lbl_nguoiketban;
    private javax.swing.JPanel panel_yeucauketban;
    // End of variables declaration//GEN-END:variables
}
