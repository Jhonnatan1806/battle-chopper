package com.project.battlechopper.client;

import com.project.battlechopper.model.Direction;
import com.project.battlechopper.model.Player;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientView extends JFrame implements KeyListener, ActionListener {
    private JTextArea ta_canvas;
    private JButton btn_connect;
    private JButton btn_disconnect;
    private ClientController clientController;

    public ClientView() {
        initComponents();
        clientController = new ClientController(this);
    }

    private void initComponents() {
        setTitle("Battle Chopper");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        ta_canvas = new JTextArea();
        ta_canvas.setEditable(false);
        ta_canvas.setFont(new Font(Font.MONOSPACED, 0, 10));
        ta_canvas.setBounds(10, 40, 580, 325);
        ta_canvas.setFocusable(true);
        ta_canvas.addKeyListener(this);

        JScrollPane scrollPane = new JScrollPane(ta_canvas);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 40, 580, 370);
        add(scrollPane);

        btn_connect = new JButton("Connect");
        btn_connect.setBounds(10, 10, 100, 20);
        btn_connect.setFocusable(false);
        btn_connect.addActionListener(this);
        btn_connect.setEnabled(true);
        add(btn_connect);

        btn_disconnect = new JButton("Disconnect"); 
        btn_disconnect.setBounds(120, 10, 100, 20);
        btn_disconnect.setFocusable(false);
        btn_disconnect.addActionListener(this);
        btn_disconnect.setEnabled(false);
        add(btn_disconnect);

        setSize(620, 460);
        setLocationRelativeTo(null);
    }

    public JTextArea getCanvas() {
        return ta_canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_connect) {
            String input = JOptionPane.showInputDialog("Ingrese la dirección IP y el puerto", "127.0.0.1:8188");
            if (input != null) {
                String[] parts = input.split(":");
                if (parts.length == 2) {
                    String ip = parts[0];
                    int port = Integer.parseInt(parts[1]);
                    clientController.connect(ip, port);
                    btn_connect.setEnabled(false);
                    btn_disconnect.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Formato incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(e.getSource() == btn_disconnect){
            clientController.disconnect();
            btn_connect.setEnabled(true);
            btn_disconnect.setEnabled(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(clientController == null){
            return;
        }

        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
                clientController.movePlayer(0,-1,Direction.UP,false);
                break;
            case KeyEvent.VK_S:
                clientController.movePlayer(0,1,Direction.DOWN,false);
                break;
            case KeyEvent.VK_A:
                clientController.movePlayer(-1,0,Direction.LEFT,false);
                break;
            case KeyEvent.VK_D:
                clientController.movePlayer(1,0,Direction.RIGHT,false);
                break;
            case KeyEvent.VK_SPACE:
                clientController.movePlayer(0,0,Direction.NONE,true);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

}
