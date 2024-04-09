package view;

import controller.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.Color;


public class RoomNameFrm extends javax.swing.JFrame {

    /**
     * Creates new form RoomNameFrm
     */
    public RoomNameFrm() {
        initComponents();
         this.setTitle("Caro Game Nhóm 2 Diện nè!");
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        lbl_timkiemphong.setVisible(false);
    }
   private void initComponents() {

        panel_vaophong = new javax.swing.JPanel();
        lbl_vaophong = new javax.swing.JLabel();
        lbl_vaophong.setBackground(new Color(0, 128, 0));
        jtf_maphong = new javax.swing.JTextField();
        lbl_maphong = new javax.swing.JLabel();
        btn_vaophong = new javax.swing.JButton();
        lbl_timkiemphong = new javax.swing.JLabel();
        lbl_matkhauphong = new javax.swing.JLabel();
        jtf_matkhauphong = new javax.swing.JTextField();
        lbl_neuphongkhongcomatkhau = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_vaophong.setBackground(new Color(0, 128, 0));

        lbl_vaophong.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_vaophong.setForeground(new java.awt.Color(255, 255, 255));
        lbl_vaophong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_vaophong.setText("Vào phòng");

        javax.swing.GroupLayout gl_panel_vaophong = new javax.swing.GroupLayout(panel_vaophong);
        panel_vaophong.setLayout(gl_panel_vaophong);
        gl_panel_vaophong.setHorizontalGroup(
            gl_panel_vaophong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_vaophong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        gl_panel_vaophong.setVerticalGroup(
            gl_panel_vaophong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_vaophong.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_vaophong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtf_maphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jtf_maphongActionPerformed(evt);
            }
        });

        lbl_maphong.setText("Nhập mã phòng");

        btn_vaophong.setText("Vào phòng");
        btn_vaophong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vaophongActionPerformed(evt);
            }
        });

        lbl_timkiemphong.setForeground(new java.awt.Color(0, 51, 255));
        lbl_timkiemphong.setText("Đang tìm kiếm phòng");

        lbl_matkhauphong.setText("Mật khẩu phòng");

        lbl_neuphongkhongcomatkhau.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        lbl_neuphongkhongcomatkhau.setText("Nếu phòng không có mật khẩu hãy để trống");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_vaophong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_matkhauphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_maphong, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtf_maphong, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jtf_matkhauphong)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lbl_neuphongkhongcomatkhau))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btn_vaophong))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(lbl_timkiemphong)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_vaophong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_maphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_maphong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_matkhauphong)
                    .addComponent(jtf_matkhauphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_neuphongkhongcomatkhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_vaophong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_timkiemphong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_maphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }

    private void btn_vaophongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vaophongActionPerformed
        String roomName = jtf_maphong.getText();
        if(roomName.equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mã phòng");
            return;
        }
        try {
            String password = " ";
            if(jtf_matkhauphong.getText().length()>0){
                password = jtf_matkhauphong.getText();
            }
            Client.socketHandle.write("go-to-room,"+roomName+","+password);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btn_vaophongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_vaophong;
    private javax.swing.JLabel lbl_vaophong;
    private javax.swing.JLabel lbl_maphong;
    private javax.swing.JLabel lbl_timkiemphong;
    private javax.swing.JLabel lbl_matkhauphong;
    private javax.swing.JLabel lbl_neuphongkhongcomatkhau;
    private javax.swing.JPanel panel_vaophong;
    private javax.swing.JTextField jtf_maphong;
    private javax.swing.JTextField jtf_matkhauphong;
    // End of variables declaration//GEN-END:variables
}
