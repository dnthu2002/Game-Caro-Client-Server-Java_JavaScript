package view;


import controller.Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;
import model.User;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GameClientFrm extends javax.swing.JFrame{

    private User competitor;
    private JButton[][] button;
    private int[][] competitorMatrix;
    private int[][] matrix;
    private int[][] userMatrix;
    
    //if you change size you will need to redesign icon
    private final int size = 15;
    // Server Socket
    private Timer timer;
    private Integer second, minute;
    
    private int numberOfMatch;
    private String normalItem[];
    private String winItem[];
    private String iconItem[];
    private String preItem[];
    
    private JButton preButton;
    private int userWin;
    private int competitorWin;
    private Thread sendThread;
    private boolean isSending;
    private Thread listenThread;
    private boolean isListening;
    private String competitorIP;

    public GameClientFrm(User competitor, int room_ID, int isStart, String competitorIP) {
    	getContentPane().setBackground(new Color(60, 179, 113));
    	initComponents();
        numberOfMatch = isStart;
        this.competitor = competitor;
        this.competitorIP = competitorIP;
        //
        isSending = false;
        isListening = false;
        btn_mic.setIcon(new ImageIcon("assets/game/mute.png"));
        btn_loa.setIcon(new ImageIcon("assets/game/mutespeaker.png"));
        //init score
        userWin = 0;
        competitorWin = 0;
        //
        this.setTitle("Caro Game Nhóm 2 Diện nè");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("assets/image/caroicon.jpg").getImage());
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        //Set layout dạng lưới cho panel chứa button
        panel_tablecaro.setLayout(new GridLayout(size, size));
        //Setup play button
        button = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j] = new JButton("");
                button[i][j].setBackground(Color.white);
                button[i][j].setDisabledIcon(new ImageIcon("assets/image/border.jpg"));
                panel_tablecaro.add(button[i][j]);
            }
        }
        //SetUp play Matrix
        competitorMatrix = new int[size][size];
        matrix = new int[size][size];
        userMatrix = new int[size][size];
        //Setup UI
        lbl_ban.setFont(new Font("Arial", Font.BOLD, 15));
        jLabel6.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_tenphong.setFont(new Font("Arial", Font.BOLD, 15));
        lbl_tenphong.setAlignmentX(JLabel.CENTER);
        btn_guitinnhan.setBackground(Color.white);
        btn_guitinnhan.setIcon(new ImageIcon("assets/image/send2.png"));
        lbl_ban_nickname1.setText(Client.user.getNickname());
        lbl_ban_sovanchoi1.setText(Integer.toString(Client.user.getNumberOfGame()));
        lbl_ban_sovanthang1.setText(Integer.toString(Client.user.getNumberOfWin()));
        lbl_ban_avatar.setIcon(new ImageIcon("assets/game/"+Client.user.getAvatar()+".gif"));
        lbl_tenphong.setText("Phòng: " + room_ID);
        lbl_iconvs.setIcon(new ImageIcon("assets/game/swords-1.png"));
        lbl_doithu_nickname1.setText(competitor.getNickname());
        lbl_doithu_sovanchoi1.setText(Integer.toString(competitor.getNumberOfGame()));
        lbl_doithu_sovanthang1.setText(Integer.toString(competitor.getNumberOfWin()));
        btn_doithu_avatar.setIcon(new ImageIcon("assets/game/"+competitor.getAvatar()+".gif"));
        btn_doithu_avatar.setToolTipText("Xem thông tin đối thủ");
        lbl_luotban.setVisible(false);
        lbl_luotdoithu.setVisible(false);
        btn_xinhoaco.setVisible(false);
        lbl_denluotban.setVisible(false);
        lbl_denluotdoithu.setVisible(false);
        lbl_thoigian.setVisible(false);
        jTextArea_chat.setEditable(false);
        lbl_tiso.setText("Tỉ số: 0-0");
        //Setup timer
        second = 60;
        minute = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = minute.toString();
                String temp1 = second.toString();
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                if (temp1.length() == 1) {
                    temp1 = "0" + temp1;
                }
                if (second == 0) {
                    lbl_thoigian.setText("Thời Gian:" + temp + ":" + temp1);
                    second = 60;
                    minute = 0;
                    try {
                        Client.openView(Client.View.GAMECLIENT, "Bạn đã thua do quá thời gian", "Đang thiết lập ván chơi mới");
                        increaseWinMatchToCompetitor();
                        Client.socketHandle.write("lose,");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
                    
                } else {
                    lbl_thoigian.setText("Thời Gian:" + temp + ":" + temp1);
                    second--;
                }

            }

        });
       
        //Setup icon
        normalItem = new String[2];
        normalItem[1] = "assets/image/o2.jpg";
        normalItem[0] = "assets/image/x2.jpg";
        winItem = new String[2];
        winItem[1] = "assets/image/owin.jpg";
        winItem[0] = "assets/image/xwin.jpg";
        iconItem = new String[2];
        iconItem[1] = "assets/image/o3.jpg";
        iconItem[0] = "assets/image/x3.jpg";
        preItem = new String[2];
        preItem[1] = "assets/image/o2_pre.jpg";
        preItem[0] = "assets/image/x2_pre.jpg";
        setupButton();

        setEnableButton(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitGame();
            }
        });
        
    }

    public void exitGame() {
        try {
            timer.stop();
            voiceCloseMic();
            voiceStopListening();
            Client.socketHandle.write("left-room,");
            Client.closeAllViews();
            Client.openView(Client.View.HOMEPAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        Client.closeAllViews();
        Client.openView(Client.View.HOMEPAGE);
    }
    
    public void stopAllThread(){
        timer.stop();
        voiceCloseMic();
        voiceStopListening();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        lbl_ban_sovanthang = new javax.swing.JLabel();
        lbl_ban_sovanthang.setForeground(new Color(255, 255, 255));
        lbl_denluotban = new javax.swing.JLabel();
        lbl_ban_nickname = new javax.swing.JLabel();
        lbl_ban_nickname.setForeground(new Color(255, 255, 255));
        lbl_ban_sovanchoi = new javax.swing.JLabel();
        lbl_ban_sovanchoi.setForeground(new Color(255, 255, 255));
        lbl_doithu_sovanthang = new javax.swing.JLabel();
        lbl_doithu_sovanthang.setForeground(new Color(255, 255, 255));
        lbl_doithu_nickname = new javax.swing.JLabel();
        lbl_doithu_nickname.setForeground(new Color(255, 255, 255));
        lbl_doithu_sovanchoi = new javax.swing.JLabel();
        lbl_doithu_sovanchoi.setForeground(new Color(255, 255, 255));
        jScrollPane_chat = new javax.swing.JScrollPane();
        jTextArea_chat = new javax.swing.JTextArea();
        jtf_tinnhan = new javax.swing.JTextField();
        lbl_ban_nickname1 = new javax.swing.JLabel();
        lbl_ban_nickname1.setForeground(new Color(255, 255, 255));
        lbl_ban_sovanchoi1 = new javax.swing.JLabel();
        lbl_ban_sovanthang1 = new javax.swing.JLabel();
        lbl_doithu_nickname1 = new javax.swing.JLabel();
        lbl_doithu_nickname1.setForeground(new Color(255, 255, 255));
        lbl_doithu_sovanchoi1 = new javax.swing.JLabel();
        lbl_doithu_sovanthang1 = new javax.swing.JLabel();
        lbl_thoigian = new javax.swing.JLabel();
        panel_tablecaro = new javax.swing.JPanel();
        panel_ban = new javax.swing.JPanel();
        lbl_ban = new javax.swing.JLabel();
        panel_doithu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        panel_phong = new javax.swing.JPanel();
        lbl_tenphong = new javax.swing.JLabel();
        lbl_tenphong.setBackground(new Color(0, 128, 0));
        btn_mic = new javax.swing.JButton();
        btn_loa = new javax.swing.JButton();
        lbl_tiso = new javax.swing.JLabel();
        lbl_tiso.setForeground(new Color(255, 255, 255));
        jPanel5 = new javax.swing.JPanel();
        btn_xinhoaco = new javax.swing.JButton();
        btn_guitinnhan = new javax.swing.JButton();
        lbl_denluotdoithu = new javax.swing.JLabel();
        lbl_luotban = new javax.swing.JLabel();
        lbl_luotban.setForeground(new Color(255, 255, 255));
        lbl_luotdoithu = new javax.swing.JLabel();
        lbl_luotdoithu.setForeground(new Color(255, 255, 255));
        panel_banvsdoithu = new javax.swing.JPanel();
        lbl_ban_avatar = new javax.swing.JLabel();
        lbl_iconvs = new javax.swing.JLabel();
        btn_doithu_avatar = new javax.swing.JButton();
        btn_doithu_avatar.setBackground(new Color(255, 255, 255));
        jProgressBar_amluong = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        lbl_ban_sovanthang.setText("Số ván thắng");

        lbl_denluotban.setForeground(new java.awt.Color(255, 0, 0));
        lbl_denluotban.setText("Đến lượt bạn");

        lbl_ban_nickname.setText("Nickname");

        lbl_ban_sovanchoi.setText("Số ván chơi");

        lbl_doithu_sovanthang.setText("Số ván thắng");

        lbl_doithu_nickname.setText("Nickname");

        lbl_doithu_sovanchoi.setText("Số ván chơi");

        jTextArea_chat.setColumns(20);
        jTextArea_chat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea_chat.setRows(5);
        jScrollPane_chat.setViewportView(jTextArea_chat);

        jtf_tinnhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtf_tinnhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
            	jtf_tinnhanKeyPressed(evt);
            }
        });

        lbl_ban_nickname1.setText("{nickname}");

        lbl_ban_sovanchoi1.setText("{sovanchoi}");

        lbl_ban_sovanthang1.setText("{sovanthang}");

        lbl_doithu_nickname1.setText("{nickname}");

        lbl_doithu_sovanchoi1.setText("{sovanchoi}");

        lbl_doithu_sovanthang1.setText("{sovanthang}");

        lbl_thoigian.setForeground(new java.awt.Color(255, 0, 0));
        lbl_thoigian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_thoigian.setText("Thời gian:00:20");

        panel_tablecaro.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout gl_panel_tablecaro = new javax.swing.GroupLayout(panel_tablecaro);
        panel_tablecaro.setLayout(gl_panel_tablecaro);
        gl_panel_tablecaro.setHorizontalGroup(
            gl_panel_tablecaro.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        gl_panel_tablecaro.setVerticalGroup(
            gl_panel_tablecaro.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        panel_ban.setBackground(new Color(0, 128, 0));

        lbl_ban.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ban.setText("Bạn");

        javax.swing.GroupLayout gl_panel_ban = new javax.swing.GroupLayout(panel_ban);
        panel_ban.setLayout(gl_panel_ban);
        gl_panel_ban.setHorizontalGroup(
            gl_panel_ban.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_ban.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_ban.setVerticalGroup(
            gl_panel_ban.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_ban.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_doithu.setBackground(new Color(0, 128, 0));
        panel_doithu.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Đối thủ");

        javax.swing.GroupLayout gl_panel_doithu = new javax.swing.GroupLayout(panel_doithu);
        panel_doithu.setLayout(gl_panel_doithu);
        gl_panel_doithu.setHorizontalGroup(
            gl_panel_doithu.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_doithu.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        gl_panel_doithu.setVerticalGroup(
            gl_panel_doithu.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel_phong.setBackground(new Color(0, 128, 0));

        lbl_tenphong.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tenphong.setText("{Tên Phòng}");

        btn_mic.setToolTipText("Bật mic để nói chuyện cùng nhau");
        btn_mic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_micActionPerformed(evt);
            }
        });

        btn_loa.setToolTipText("Âm thanh trò chuyện đang tắt");
        btn_loa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_loaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gl_panel_phong = new javax.swing.GroupLayout(panel_phong);
        panel_phong.setLayout(gl_panel_phong);
        gl_panel_phong.setHorizontalGroup(
            gl_panel_phong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_phong.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tenphong, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_mic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_loa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_phong.setVerticalGroup(
            gl_panel_phong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_tenphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(gl_panel_phong.createSequentialGroup()
                .addGroup(gl_panel_phong.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_mic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_loa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lbl_tiso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tiso.setText("Tỉ số:  0-0");

        jPanel5.setBackground(new Color(0, 128, 0));

        btn_xinhoaco.setBackground(new Color(0, 128, 128));
        btn_xinhoaco.setForeground(new java.awt.Color(255, 255, 255));
        btn_xinhoaco.setText("Cầu hòa");
        btn_xinhoaco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btn_xinhoacoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btn_xinhoaco, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_xinhoaco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        btn_guitinnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guitinnhanActionPerformed(evt);
            }
        });

        lbl_denluotdoithu.setForeground(new java.awt.Color(0, 0, 204));
        lbl_denluotdoithu.setText("Đến lượt đối thủ");

        lbl_luotban.setText("x/o");

        lbl_luotdoithu.setText("x/o");

        panel_banvsdoithu.setBackground(new Color(0, 128, 0));

        lbl_ban_avatar.setBackground(new Color(255, 255, 255));

        btn_doithu_avatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gl_panel_banvsdoithu = new javax.swing.GroupLayout(panel_banvsdoithu);
        panel_banvsdoithu.setLayout(gl_panel_banvsdoithu);
        gl_panel_banvsdoithu.setHorizontalGroup(
            gl_panel_banvsdoithu.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_banvsdoithu.createSequentialGroup()
                .addContainerGap()
                .addGroup(gl_panel_banvsdoithu.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gl_panel_banvsdoithu.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_iconvs, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                        .addComponent(lbl_ban_avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btn_doithu_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        gl_panel_banvsdoithu.setVerticalGroup(
            gl_panel_banvsdoithu.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panel_banvsdoithu.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ban_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_iconvs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_doithu_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jMenu1.setText("Menu");
        jMenu1.setToolTipText("");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Game mới");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem2.setText("Thoát");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Trợ giúp");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(panel_phong, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(panel_doithu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addGroup(layout.createSequentialGroup()
        									.addContainerGap()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addGroup(layout.createSequentialGroup()
        											.addComponent(lbl_ban_sovanthang, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        											.addGap(26)
        											.addComponent(lbl_ban_sovanthang1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
        										.addGroup(layout.createSequentialGroup()
        											.addComponent(lbl_doithu_nickname, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
        											.addGap(39)
        											.addComponent(lbl_doithu_nickname1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
        										.addGroup(layout.createSequentialGroup()
        											.addGroup(layout.createParallelGroup(Alignment.LEADING)
        												.addComponent(lbl_doithu_sovanchoi, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        												.addComponent(lbl_doithu_sovanthang, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        											.addGap(27)
        											.addGroup(layout.createParallelGroup(Alignment.LEADING)
        												.addComponent(lbl_doithu_sovanchoi1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        												.addComponent(lbl_doithu_sovanthang1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))))))
        							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
        							.addComponent(panel_banvsdoithu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addComponent(panel_ban, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addGap(29)
        									.addComponent(lbl_luotban, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        									.addGap(39)
        									.addComponent(lbl_tiso, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        									.addGap(41)
        									.addComponent(lbl_luotdoithu, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        								.addGroup(layout.createSequentialGroup()
        									.addContainerGap()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(lbl_ban_sovanchoi, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        										.addComponent(lbl_ban_nickname, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
        									.addGap(26)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(lbl_ban_nickname1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
        										.addComponent(lbl_ban_sovanchoi1)))
        								.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGap(0, 0, Short.MAX_VALUE))
        						.addGroup(layout.createSequentialGroup()
        							.addContainerGap()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jtf_tinnhan, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(btn_guitinnhan, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(lbl_denluotban, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        									.addGap(28)
        									.addComponent(lbl_thoigian, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        									.addGap(28)
        									.addComponent(lbl_denluotdoithu, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
        								.addComponent(jScrollPane_chat, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))))
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(0, 202, Short.MAX_VALUE)
        					.addComponent(jProgressBar_amluong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(29)))
        			.addComponent(panel_tablecaro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panel_ban, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(panel_banvsdoithu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_ban_nickname)
        						.addComponent(lbl_ban_nickname1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_ban_sovanchoi)
        						.addComponent(lbl_ban_sovanchoi1))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_ban_sovanthang)
        						.addComponent(lbl_ban_sovanthang1))
        					.addGap(18)
        					.addComponent(panel_doithu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_doithu_nickname)
        						.addComponent(lbl_doithu_nickname1))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_doithu_sovanchoi)
        						.addComponent(lbl_doithu_sovanchoi1))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbl_doithu_sovanthang)
        						.addComponent(lbl_doithu_sovanthang1))))
        			.addGap(18)
        			.addComponent(panel_phong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jProgressBar_amluong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lbl_luotdoithu, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lbl_tiso)
        				.addComponent(lbl_luotban, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lbl_thoigian)
        				.addComponent(lbl_denluotdoithu)
        				.addComponent(lbl_denluotban))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane_chat, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jtf_tinnhan, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        				.addComponent(btn_guitinnhan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addComponent(panel_tablecaro, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        //for(int i=0; i<5; i++){
            //    for(int j=0;j<5;j++){
                //        jPanel1.add(button[i][j]);
                //    }
            //}

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Thông báo", "Tính năng đang được phát triển", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        exitGame();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn_guitinnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guitinnhanActionPerformed
        try {
            if (jtf_tinnhan.getText().isEmpty()) {
                throw new Exception("Vui lòng nhập nội dung tin nhắn");
            }
            String temp = jTextArea_chat.getText();
            temp += "Tôi: " + jtf_tinnhan.getText() + "\n";
            jTextArea_chat.setText(temp);
            Client.socketHandle.write("chat," + jtf_tinnhan.getText());
            jtf_tinnhan.setText("");
            jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btn_guitinnhanActionPerformed

    private void btn_xinhoacoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xinhoacoActionPerformed
        
        try {
            int res = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn cầu hòa ván chơi này", "Yêu cầu cầu hòa", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                Client.socketHandle.write("draw-request,");
                timer.stop();
                setEnableButton(false);
                Client.openView(Client.View.GAMENOTICE, "Yêu cầu hòa", "Đang chờ phản hồi từ đối thủ");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btn_xinhoacoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Luật chơi: luật quốc tế 5 nước chặn 2 đầu\n"
                + "Hai người chơi luân phiên nhau chơi trước\n"
                + "Người chơi trước đánh X, người chơi sau đánh O\n"
                + "Bạn có 20 giây cho mỗi lượt đánh, quá 20 giây bạn sẽ thua\n"
                + "Khi cầu hòa, nếu đối thủ đồng ý thì ván hiện tại được hủy kết quả\n"
                + "Với mỗi ván chơi bạn có thêm 1 điểm, nếu hòa bạn được thêm 5 điểm,\n"
                + "nếu thắng bạn được thêm 10 điểm\n"
                + "Chúc bạn chơi game vui vẻ");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

         Client.openView(Client.View.COMPETITORINFO, competitor);
            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_micActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_micActionPerformed
        if(isSending){
            try {
                Client.socketHandle.write("voice-message,close-mic");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
            }
            btn_mic.setIcon(new ImageIcon("assets/game/mute.png"));
            voiceCloseMic();
            btn_mic.setToolTipText("Mic đang tắt");

        }
        else{
            try {
                Client.socketHandle.write("voice-message,open-mic");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
            }
            btn_mic.setIcon(new ImageIcon("assets/game/88634.png"));
            voiceOpenMic();
            btn_mic.setToolTipText("Mic đang bật");
        }
    }//GEN-LAST:event_btn_micActionPerformed

    private void btn_loaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loaActionPerformed
        if (isListening) {
            try {
                Client.socketHandle.write("voice-message,close-speaker");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
            }
            btn_loa.setIcon(new ImageIcon("assets/game/mutespeaker.png"));
            voiceStopListening();
            btn_loa.setToolTipText("Âm thanh trò chuyện đang tắt");
        } else {
            try {
                Client.socketHandle.write("voice-message,open-speaker");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
            }
            voiceListening();
            btn_loa.setIcon(new ImageIcon("assets/game/speaker.png"));
            btn_loa.setToolTipText("Âm thanh trò chuyện đang bật");
        }
    }//GEN-LAST:event_btn_loaActionPerformed

    private void jtf_tinnhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_tinnhanKeyPressed
        if (evt.getKeyCode() == 10) {
            try {
                if (jtf_tinnhan.getText().isEmpty()) {
                    return;
                }
                String temp = jTextArea_chat.getText();
                temp += "Tôi: " + jtf_tinnhan.getText() + "\n";
                jTextArea_chat.setText(temp);
                Client.socketHandle.write("chat," + jtf_tinnhan.getText());
                jtf_tinnhan.setText("");
                jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtf_tinnhanKeyPressed

    public void showMessage(String message){
        JOptionPane.showMessageDialog(rootPane, message);
    }
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/click.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playSound1() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/1click.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playSound2() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/win.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public void stopTimer(){
        timer.stop();
    }
    int not(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        return 0;
    }

    void setupButton() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int a = i, b = j;

                button[a][b].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            button[a][b].setDisabledIcon(new ImageIcon(normalItem[not(numberOfMatch % 2)]));
                            button[a][b].setEnabled(false);
                            playSound();
                            second = 60;
                            minute = 0;
                            matrix[a][b] = 1;
                            userMatrix[a][b] = 1;
                            button[a][b].setEnabled(false);
                            try {
                                if (checkRowWin() == 1 || checkColumnWin() == 1 || checkRightCrossWin() == 1 || checkLeftCrossWin() == 1) {
                                    //Xử lý khi người chơi này thắng
                                    setEnableButton(false);
                                    increaseWinMatchToUser();
                                    Client.openView(Client.View.GAMENOTICE,"Bạn đã thắng","Đang thiết lập ván chơi mới");
                                    Client.socketHandle.write("win,"+a+","+b);
                                }
                                else{
                                    Client.socketHandle.write("caro," + a + "," + b);
                                    displayCompetitorTurn();
                                    
                                }
                                setEnableButton(false);
                                timer.stop();
                            } catch (Exception ie) {
                                ie.printStackTrace();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                        }
                    }
                });
                button[a][b].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if(button[a][b].isEnabled()) {
                            button[a][b].setBackground(Color.GREEN);
                            button[a][b].setIcon(new ImageIcon(normalItem[not(numberOfMatch % 2)]));
                        }
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        if(button[a][b].isEnabled()){
                            button[a][b].setBackground(null);
                            button[a][b].setIcon(new ImageIcon("assets/image/blank.jpg"));
                        }
                    }
                });
            }
        }
    }

    public void displayDrawRefuse(){
        JOptionPane.showMessageDialog(rootPane, "Đối thủ không chấp nhận hòa, mời bạn chơi tiếp");
        timer.start();
        setEnableButton(true);
    }
    
    public void displayCompetitorTurn() {
        lbl_thoigian.setVisible(false);
        lbl_denluotdoithu.setVisible(true);
        lbl_luotdoithu.setVisible(true);
        lbl_denluotban.setVisible(false);
        btn_xinhoaco.setVisible(false);
        lbl_luotban.setVisible(false);
    }
    public void displayUserTurn(){
        lbl_thoigian.setVisible(false);
        lbl_denluotdoithu.setVisible(false);
        lbl_luotdoithu.setVisible(false);
        lbl_denluotban.setVisible(true);
        btn_xinhoaco.setVisible(true);
        lbl_luotban.setVisible(true);
    }
    
    public void startTimer(){
        lbl_thoigian.setVisible(true);
        second = 60;
        minute = 0;
        timer.start();
    }
    public void addMessage(String message){
        String temp = jTextArea_chat.getText();
        temp += competitor.getNickname() + ": " + message+"\n";
        jTextArea_chat.setText(temp);
        jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
    }
    
    public void addCompetitorMove(String x, String y){
        displayUserTurn();
        startTimer();
        setEnableButton(true);
        caro(x, y);
    }
    
    public void setLose(String xx, String yy){
        caro(xx, yy);
    }
    
    public void increaseWinMatchToUser(){
        Client.user.setNumberOfWin(Client.user.getNumberOfWin()+1);
        lbl_ban_sovanthang1.setText(""+Client.user.getNumberOfWin());
        userWin++;
        lbl_tiso.setText("Tỉ số: "+userWin+"-"+competitorWin);
        String tmp = jTextArea_chat.getText();
        tmp += "--Bạn đã thắng, tỉ số hiện tại là "+userWin+"-"+competitorWin+"--\n";
        jTextArea_chat.setText(tmp);
        jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
    }
    public void increaseWinMatchToCompetitor(){
        competitor.setNumberOfWin(competitor.getNumberOfWin()+1);
        lbl_doithu_sovanthang1.setText(""+competitor.getNumberOfWin());
        competitorWin++;
        lbl_tiso.setText("Tỉ số: "+userWin+"-"+competitorWin);
        String tmp = jTextArea_chat.getText();
        tmp += "--Bạn đã thua, tỉ số hiện tại là "+userWin+"-"+competitorWin+"--\n";
        jTextArea_chat.setText(tmp);
        jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
    }
    public void displayDrawGame(){
        String tmp = jTextArea_chat.getText();
        tmp += "--Ván chơi hòa--\n";
        jTextArea_chat.setText(tmp);
        jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
    }
    public void showDrawRequest() {
        int res = JOptionPane.showConfirmDialog(rootPane, "Đối thử muốn cầu hóa ván này, bạn đồng ý chứ", "Yêu cầu cầu hòa", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            try {
                timer.stop();
                setEnableButton(false);
                Client.socketHandle.write("draw-confirm,");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
        else{
            try {
                Client.socketHandle.write("draw-refuse,");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }
    public void voiceOpenMic() {

        sendThread = new Thread() {

            @Override
            public void run() {
                AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
                TargetDataLine microphone;
                try {
                    microphone = AudioSystem.getTargetDataLine(format);

                    DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                    microphone = (TargetDataLine) AudioSystem.getLine(info);
                    microphone.open(format);

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int numBytesRead;
                    int CHUNK_SIZE = 1024;
                    byte[] data = new byte[microphone.getBufferSize() / 5];
                    microphone.start();

                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);

                    int port = 5555;

                    InetAddress address = InetAddress.getByName(competitorIP);
                    DatagramSocket socket = new DatagramSocket();
                    byte[] buffer = new byte[1024];
                    isSending = true;
                    while(isSending) {
                        numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                        out.write(data, 0, numBytesRead);
                        DatagramPacket request = new DatagramPacket(data, numBytesRead, address, port);
                        socket.send(request);

                    }
                    out.close();
                    socket.close();
                    microphone.close();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (SocketException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        };
        sendThread.start();

    }

    public void voiceCloseMic() {
        isSending = false;
    }

    
    public void voiceListening() {
        listenThread = new Thread() {
            @Override
            public void run() {
                try {
                    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
                    TargetDataLine microphone;
                    SourceDataLine speakers;
//                    microphone = AudioSystem.getTargetDataLine(format);
//                    DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//                    microphone = (TargetDataLine) AudioSystem.getLine(info);
//                    microphone.open(format);
//                    microphone.start();
                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                    speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                    speakers.open(format);
                    speakers.start();
                    try {
                        DatagramSocket serverSocket = new DatagramSocket(5555);
                        isListening = true;
                        while (isListening) {
                            byte[] buffer = new byte[1024];
                            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                            serverSocket.receive(response);
                            speakers.write(response.getData(), 0, response.getData().length);
                            jProgressBar_amluong.setValue((int) volumeRMS(response.getData()));
                        }
                        speakers.close();
                        serverSocket.close();
                    } catch (SocketTimeoutException ex) {
                        System.out.println("Timeout error: " + ex.getMessage());
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        System.out.println("Client error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }

        };
        listenThread.start();
    }
    private int getMax(byte[] bytes){
        int max = bytes[0];
        for(int i=1; i<bytes.length ; i++){
            if(bytes[i]>max) max=bytes[i];
        }
        return max;
    }
    public double volumeRMS(byte[] raw) {
        double sum = 0d;
        if (raw.length == 0) {
            return sum;
        } else {
            for (int ii = 0; ii < raw.length; ii++) {
                sum += raw[ii];
            }
        }
        double average = sum / raw.length;

        double sumMeanSquare = 0d;
        for (int ii = 0; ii < raw.length; ii++) {
            sumMeanSquare += Math.pow(raw[ii] - average, 2d);
        }
        double averageMeanSquare = sumMeanSquare / raw.length;
        double rootMeanSquare = Math.sqrt(averageMeanSquare);

        return rootMeanSquare;
    }
    public void voiceStopListening(){
        isListening = false;
    }
    
    public void addVoiceMessage(String message){
        String temp = jTextArea_chat.getText();
        temp += competitor.getNickname() + " " + message+"\n";
        jTextArea_chat.setText(temp);
        jTextArea_chat.setCaretPosition(jTextArea_chat.getDocument().getLength());
    }
    public void newgame() {
        
        if (numberOfMatch % 2 == 0) {
            JOptionPane.showMessageDialog(rootPane, "Đến lượt bạn đi trước");
            startTimer();
            displayUserTurn();
            lbl_thoigian.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Đối thủ đi trước");
            displayCompetitorTurn();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j].setIcon(new ImageIcon("assets/image/blank.jpg"));
                button[i][j].setDisabledIcon(new ImageIcon("assets/image/border.jpg"));
                button[i][j].setText("");
                competitorMatrix[i][j] = 0;
                matrix[i][j] = 0;
                userMatrix[i][j] = 0;
            }
        }
        setEnableButton(true);
        if(numberOfMatch % 2 != 0){
            blockgame();
        }
        
        lbl_luotban.setIcon(new ImageIcon(iconItem[numberOfMatch % 2]));
        lbl_luotdoithu.setIcon(new ImageIcon(iconItem[not(numberOfMatch % 2)]));
        preButton = null;
        numberOfMatch++;
    }
    public void updateNumberOfGame(){
        competitor.setNumberOfGame(competitor.getNumberOfGame() + 1);
        lbl_doithu_sovanchoi1.setText(Integer.toString(competitor.getNumberOfGame()));
        Client.user.setNumberOfGame(Client.user.getNumberOfGame() + 1);
        lbl_ban_sovanchoi1.setText(Integer.toString(Client.user.getNumberOfGame()));
    }
    
    public void blockgame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j].setBackground(Color.white);
                button[i][j].setDisabledIcon(new ImageIcon("assets/image/border.jpg"));
                button[i][j].setText("");
                competitorMatrix[i][j] = 0;
                matrix[i][j] = 0;
                btn_xinhoaco.setVisible(false);
            }
        }
        timer.stop();
        setEnableButton(false);
    }

    public void setEnableButton(boolean b) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    button[i][j].setEnabled(b);
                }
            }
        }
    }
    //thuat toan tinh thang thua

    public int checkRow() {
        int win = 0, hang = 0, n = 0, k = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (competitorMatrix[i][j] == 1) {
                        hang++;
                        list.add(button[i][j]);
                        if (hang > 4) {
                            for (JButton jButton : list) {
                                button[i][j].setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        hang = 0;
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    hang++;
                } else {
                    list = new ArrayList<>();
                    check = false;
                }
            }
            list = new ArrayList<>();
            hang = 0;
        }
        return win;
    }

    public int checkColumn() {
        int win = 0, cot = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (check) {
                    if (competitorMatrix[i][j] == 1) {
                        cot++;
                        list.add(button[i][j]);
                        if (cot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        check = false;
                        cot = 0;
                        list = new ArrayList<>();
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    cot++;
                } else {
                    list = new ArrayList<>();
                    check = false;
                }
            }
            list = new ArrayList<>();
            cot = 0;
        }
        return win;
    }

    public int checkRightCross() {
        int win = 0, cheop = 0, n = 0, k = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = size-1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (n - j>=0 && competitorMatrix[n - j][j] == 1) {
                        cheop++;
                        list.add(button[n - j][j]);
                        if (cheop > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheop = 0;
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    n = i + j;
                    check = true;
                    list.add(button[i][j]);
                    cheop++;
                } else {
                    check = false;
                    list = new ArrayList<>();
                }
            }
            cheop = 0;
            check = false;
            list = new ArrayList<>();
        }
        return win;
    }

    public int checkLeftCross() {
        int win = 0, cheot = 0, n = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = size-1; j >= 0; j--) {
                if (check) {
                    if (n - j - 2 * cheot>=0 && competitorMatrix[n - j - 2 * cheot][j] == 1) {
                        list.add(button[n - j - 2 * cheot][j]);
                        cheot++;
                        System.out.print("+" + j);
                        if (cheot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheot = 0;
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    list.add(button[i][j]);
                    n = i + j;
                    check = true;
                    cheot++;
                } else {
                    check = false;
                }
            }
            list = new ArrayList<>();
            n = 0;
            cheot = 0;
            check = false;
        }
        return win;
    }

    public int checkRowWin() {
        int win = 0, hang = 0, n = 0, k = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (userMatrix[i][j] == 1) {
                        hang++;
                        list.add(button[i][j]);
                        if (hang > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        hang = 0;
                    }
                }
                if (userMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    hang++;
                } else {
                    list = new ArrayList<>();
                    check = false;
                }
            }
            list = new ArrayList<>();
            hang = 0;
        }
        return win;
    }

    public int checkColumnWin() {
        int win = 0, cot = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (check) {
                    if (userMatrix[i][j] == 1) {
                        cot++;
                        list.add(button[i][j]);
                        if (cot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        check = false;
                        cot = 0;
                        list = new ArrayList<>();
                    }
                }
                if (userMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    cot++;
                } else {
                    check = false;
                }
            }
            list = new ArrayList<>();
            cot = 0;
        }
        return win;
    }

    public int checkRightCrossWin() {
        int win = 0, cheop = 0, n = 0, k = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = size-1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (n>=j && userMatrix[n - j][j] == 1) {
                        cheop++;
                        list.add(button[n - j][j]);
                        if (cheop > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheop = 0;
                    }
                }
                if (userMatrix[i][j] == 1) {
                    n = i + j;
                    check = true;
                    list.add(button[i][j]);
                    cheop++;
                } else {
                    check = false;
                    list = new ArrayList<>();
                }
            }
            cheop = 0;
            check = false;
            list = new ArrayList<>();
        }
        return win;
    }

    public int checkLeftCrossWin() {
        int win = 0, cheot = 0, n = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = size-1; j >= 0; j--) {
                if (check) {
                    if (n - j - 2 * cheot>=0 && userMatrix[n - j - 2 * cheot][j] == 1) {
                        list.add(button[n - j - 2 * cheot][j]);
                        cheot++;
                        System.out.print("+" + j);
                        if (cheot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheot = 0;
                    }
                }
                if (userMatrix[i][j] == 1) {
                    list.add(button[i][j]);
                    n = i + j;
                    check = true;
                    cheot++;
                } else {
                    check = false;
                }
            }
            list = new ArrayList<>();
            n = 0;
            cheot = 0;
            check = false;
        }
        return win;
    }

    public void caro(String x, String y) {
        int xx, yy;
        xx = Integer.parseInt(x);
        yy = Integer.parseInt(y);
        // danh dau vi tri danh
        competitorMatrix[xx][yy] = 1;
        matrix[xx][yy] = 1;
        button[xx][yy].setEnabled(false);
        playSound1();
        if(preButton!=null){
            preButton.setDisabledIcon(new ImageIcon(normalItem[numberOfMatch % 2]));
        }
        preButton = button[xx][yy];
        button[xx][yy].setDisabledIcon(new ImageIcon(preItem[numberOfMatch % 2]));
        if(checkRow()==1||checkColumn()==1||checkLeftCross()==1||checkRightCross()==1){
            timer.stop();
            setEnableButton(false);
            increaseWinMatchToCompetitor();
            Client.openView(Client.View.GAMENOTICE,"Bạn đã thua","Đang thiết lập ván chơi mới");
        }
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_denluotdoithu;
    private javax.swing.JButton btn_guitinnhan;
    private javax.swing.JButton btn_xinhoaco;
    private javax.swing.JButton btn_doithu_avatar;
    private javax.swing.JButton btn_loa;
    private javax.swing.JButton btn_mic;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel lbl_ban;
    private javax.swing.JLabel lbl_doithu_nickname;
    private javax.swing.JLabel lbl_doithu_sovanchoi;
    private javax.swing.JLabel lbl_ban_nickname1;
    private javax.swing.JLabel lbl_ban_sovanchoi1;
    private javax.swing.JLabel lbl_ban_sovanthang1;
    private javax.swing.JLabel lbl_doithu_nickname1;
    private javax.swing.JLabel lbl_doithu_sovanchoi1;
    private javax.swing.JLabel lbl_doithu_sovanthang1;
    private javax.swing.JLabel lbl_tenphong;
    private javax.swing.JLabel lbl_ban_avatar;
    private javax.swing.JLabel lbl_ban_sovanthang;
    private javax.swing.JLabel lbl_tiso;
    private javax.swing.JLabel lbl_iconvs;
    private javax.swing.JLabel lbl_luotban;
    private javax.swing.JLabel lbl_ban_sovanchoi;
    private javax.swing.JLabel lbl_luotdoithu;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbl_ban_nickname;
    private javax.swing.JLabel lbl_doithu_sovanthang;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel panel_tablecaro;
    private javax.swing.JPanel panel_ban;
    private javax.swing.JPanel panel_doithu;
    private javax.swing.JPanel panel_phong;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel panel_banvsdoithu;
    private javax.swing.JProgressBar jProgressBar_amluong;
    private javax.swing.JScrollPane jScrollPane_chat;
    private javax.swing.JTextArea jTextArea_chat;
    private javax.swing.JTextField jtf_tinnhan;
    private javax.swing.JLabel lbl_thoigian;
    private javax.swing.JLabel lbl_denluotban;
    // End of variables declaration//GEN-END:variables

    

}
