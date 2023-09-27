package com.project.simplegame.view;

public class GameView extends javax.swing.JFrame {

        public GameView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        connection_panel1 = new javax.swing.JPanel();
        Ibl_ip1 = new javax.swing.JLabel();
        tf_ip1 = new javax.swing.JTextField();
        connection_panel = new javax.swing.JPanel();
        Ibl_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        btn_connect = new javax.swing.JButton();
        canvas_scrollpane = new javax.swing.JScrollPane();
        ta_canvas = new javax.swing.JTextArea();
        btn_up = new javax.swing.JButton();
        btn_down = new javax.swing.JButton();
        btn_left = new javax.swing.JButton();
        btn_right = new javax.swing.JButton();
        btn_fire = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle Chopper");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout();
        flowLayout2.setAlignOnBaseline(true);
        connection_panel1.setLayout(flowLayout2);

        Ibl_ip1.setText("IP");
        connection_panel1.add(Ibl_ip1);

        tf_ip1.setText("10.10.0.107");
        connection_panel1.add(tf_ip1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(connection_panel1, gridBagConstraints);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        connection_panel.setLayout(flowLayout1);

        Ibl_port.setText("Puerto");
        connection_panel.add(Ibl_port);

        tf_port.setText("5492");
        connection_panel.add(tf_port);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(connection_panel, gridBagConstraints);

        btn_connect.setText("Conectar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        getContentPane().add(btn_connect, gridBagConstraints);

        canvas_scrollpane.setPreferredSize(new java.awt.Dimension(420, 240));

        ta_canvas.setColumns(20);
        ta_canvas.setRows(5);
        canvas_scrollpane.setViewportView(ta_canvas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 4;
        getContentPane().add(canvas_scrollpane, gridBagConstraints);

        btn_up.setText("🡹");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        getContentPane().add(btn_up, gridBagConstraints);

        btn_down.setText("🡻");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        getContentPane().add(btn_down, gridBagConstraints);

        btn_left.setText("🡺");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        getContentPane().add(btn_left, gridBagConstraints);

        btn_right.setText("🡸");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        getContentPane().add(btn_right, gridBagConstraints);

        btn_fire.setText("Fire");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(btn_fire, gridBagConstraints);

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
    private javax.swing.JPanel connection_panel;
    private javax.swing.JPanel connection_panel1;
    private javax.swing.JTextArea ta_canvas;
    private javax.swing.JTextField tf_ip1;
    private javax.swing.JTextField tf_port;
    // End of variables declaration//GEN-END:variables
}
