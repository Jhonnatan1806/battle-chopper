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
                if(clientController.isPossibleToMove(Direction.UP)){
                    Player player = clientController.player;
                    player.setDirection(Direction.UP);
                    player.setY(player.getY()-1);
                    player.setIsShooting(false);
                }
                return;
            case KeyEvent.VK_S:
                if(clientController.isPossibleToMove(Direction.DOWN)){
                    Player player = clientController.player;
                    player.setDirection(Direction.DOWN);
                    player.setY(player.getY()+1);
                    player.setIsShooting(false);
                }
                return;
            case KeyEvent.VK_A:
                if(clientController.isPossibleToMove(Direction.LEFT)){
                    Player player = clientController.player;
                    player.setDirection(Direction.LEFT);
                    player.setX(player.getX()-1);
                    player.setIsShooting(false);
                    moveCanvasLeft();
                }
                return;
            case KeyEvent.VK_D:
                if(clientController.isPossibleToMove(Direction.RIGHT)){
                    Player player = clientController.player;
                    player.setDirection(Direction.RIGHT);
                    player.setX(player.getX()+1);
                    player.setIsShooting(false);
                    moveCanvasRight();
                }
                return;
            case KeyEvent.VK_SPACE:
                Player player = clientController.player;
                if(player.getDirection() != Direction.NONE){
                    player.setIsShooting(true);
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    private void moveCanvasLeft() {
        JTextArea canvas = getCanvas();
        JViewport viewport = (JViewport) canvas.getParent();
        int viewPosition = viewport.getViewPosition().x;

        int scrollAmount = 6;

        if(clientController.player.getX() <= 322){
            int newViewPosition = viewPosition - scrollAmount;

            if (newViewPosition < 0) {
                newViewPosition = 0;
            }

            viewport.setViewPosition(new Point(newViewPosition, 0));
        }
    }

    private void moveCanvasRight() {
        JTextArea canvas = getCanvas();
        JViewport viewport = (JViewport) canvas.getParent();
        int viewPosition = viewport.getViewPosition().x;

        int scrollAmount = 6;

        if(clientController.player.getX() >= 48){
            int newViewPosition = viewPosition + scrollAmount;

            int maxX = canvas.getWidth() - viewport.getWidth();
            if (newViewPosition > maxX) {
                newViewPosition = maxX;
            }

            viewport.setViewPosition(new Point(newViewPosition, 0));
        }
    }
}
