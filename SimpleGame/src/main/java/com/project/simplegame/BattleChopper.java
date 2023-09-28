package com.project.simplegame;

import com.project.simplegame.view.GameView;

/**
 * @author Barrientos Cárdenas, Gabriel
 * @author Espinoza Rojas, Jhonnatan
 * @author Rivera Gambini, Walter
 */
public class BattleChopper {

    public static void main(String[] args){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new GameView().setVisible(true);
        }); 
    }
}
