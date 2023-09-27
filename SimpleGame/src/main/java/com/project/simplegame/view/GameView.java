package com.project.simplegame.view;

public class GameView extends javax.swing.JFrame {

        public GameView() {
        initComponents();
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
        btn_left = new javax.swing.JButton();
        btn_right = new javax.swing.JButton();
        btn_fire = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle Chopper");
        setResizable(false);

        Ibl_port.setText("Puerto");

        tf_port.setText("5492");

        Ibl_ip1.setText("IP");

        tf_ip1.setText("10.10.0.107");

        canvas_scrollpane.setPreferredSize(new java.awt.Dimension(420, 240));

        ta_canvas.setColumns(20);
        ta_canvas.setRows(5);
        canvas_scrollpane.setViewportView(ta_canvas);

        btn_connect.setText("Conectar");

        btn_up.setText("🡹");

        btn_down.setText("🡻");

        btn_left.setText("🡺");

        btn_right.setText("🡸");

        btn_fire.setText("Fire");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Ibl_ip1)
                                .addGap(25, 25, 25)
                                .addComponent(tf_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Ibl_port)
                                .addGap(18, 18, 18)
                                .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_connect))
                            .addComponent(canvas_scrollpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_right)
                                .addGap(46, 46, 46)
                                .addComponent(btn_left)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_fire))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_down)
                            .addComponent(btn_up))))
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addComponent(canvas_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_up)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_left)
                    .addComponent(btn_right)
                    .addComponent(btn_fire))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_down)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
