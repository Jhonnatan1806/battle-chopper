package com.project.simplegame.trash;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

public class GameView extends javax.swing.JFrame {
    
    private GameController gameController;
    private Player player;

    public GameView() {
        initComponents();
        player = new Player(8, 20, Direction.RIGHT, "A");
        gameController = new GameController(this, player);
    }
    
    public JTextArea getCanvas(){
        return this.ta_canvas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Ibl_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        Ibl_ip1 = new javax.swing.JLabel();
        tf_ip1 = new javax.swing.JTextField();
        canvas_scrollpane = new javax.swing.JScrollPane();
        ta_canvas = new javax.swing.JTextArea();
        btn_connect = new javax.swing.JButton();
        btn_up = new javax.swing.JButton();
        btn_down = new javax.swing.JButton();
        btn_right = new javax.swing.JButton();
        btn_left = new javax.swing.JButton();
        btn_fire = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle Chopper");
        setResizable(false);

        Ibl_port.setText("Puerto");

        tf_port.setText("5492");

        Ibl_ip1.setText("IP");

        tf_ip1.setText("10.10.0.107");

        canvas_scrollpane.setPreferredSize(new java.awt.Dimension(420, 240));

        ta_canvas.setEditable(false);
        ta_canvas.setColumns(20);
        ta_canvas.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        ta_canvas.setRows(5);
        ta_canvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ta_canvasKeyPressed(evt);
            }
        });
        canvas_scrollpane.setViewportView(ta_canvas);

        btn_connect.setText("Conectar");

        btn_up.setText("🡹");
        btn_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_upActionPerformed(evt);
            }
        });

        btn_down.setText("🡻");
        btn_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_downActionPerformed(evt);
            }
        });

        btn_right.setText("🡺");
        btn_right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rightActionPerformed(evt);
            }
        });

        btn_left.setText("🡸");
        btn_left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_leftActionPerformed(evt);
            }
        });

        btn_fire.setText("Fire");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_down)
                    .addComponent(btn_up))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(Ibl_ip1)
                        .addGap(18, 18, 18)
                        .addComponent(tf_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(Ibl_port)
                        .addGap(18, 18, 18)
                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btn_left)
                        .addGap(48, 48, 48)
                        .addComponent(btn_right)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_connect, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_fire)
                        .addGap(70, 70, 70))))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(canvas_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ibl_ip1)
                    .addComponent(tf_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ibl_port)
                    .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_connect))
                .addGap(18, 18, 18)
                .addComponent(canvas_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_right)
                            .addComponent(btn_left)
                            .addComponent(btn_fire))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_up)
                        .addGap(40, 40, 40)
                        .addComponent(btn_down)
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_upActionPerformed
        this.gameController.move(Direction.UP, player);
    }//GEN-LAST:event_btn_upActionPerformed

    private void btn_rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rightActionPerformed
        player.setDirection(Direction.RIGHT);
        this.gameController.move(Direction.RIGHT, player);
    }//GEN-LAST:event_btn_rightActionPerformed

    private void btn_leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leftActionPerformed
        player.setDirection(Direction.LEFT);
        this.gameController.move(Direction.LEFT, player);
    }//GEN-LAST:event_btn_leftActionPerformed

    private void btn_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_downActionPerformed
        this.gameController.move(Direction.DOWN, player);
    }//GEN-LAST:event_btn_downActionPerformed

    private void ta_canvasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ta_canvasKeyPressed
        int keyCode = evt.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                this.gameController.move(Direction.UP, player);
                break;
            case KeyEvent.VK_D:
                player.setDirection(Direction.RIGHT);
                this.gameController.move(Direction.RIGHT, player);
                break;
            case KeyEvent.VK_A:
                player.setDirection(Direction.LEFT);
                this.gameController.move(Direction.LEFT, player);
                break;
            case KeyEvent.VK_S:
                this.gameController.move(Direction.DOWN, player);
                break;
        }
    }//GEN-LAST:event_ta_canvasKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ibl_ip1;
    private javax.swing.JLabel Ibl_port;
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_down;
    private javax.swing.JButton btn_fire;
    private javax.swing.JButton btn_left;
    private javax.swing.JButton btn_right;
    private javax.swing.JButton btn_up;
    private javax.swing.JScrollPane canvas_scrollpane;
    private javax.swing.JTextArea ta_canvas;
    private javax.swing.JTextField tf_ip1;
    private javax.swing.JTextField tf_port;
    // End of variables declaration//GEN-END:variables
}
