package com.project.simplegame;

import com.project.simplegame.model.Direction;
import com.project.simplegame.model.Player;
import com.project.simplegame.model.Stage;
import com.project.simplegame.view.GameView;
import java.io.IOException;

/**
 * @author Barrientos Cárdenas, Gabriel
 * @author Espinoza Rojas, Jhonnatan
 * @author Rivera Gambini, Walter
 */
public class BattleChopper {

    public static void main(String[] args) {
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
            GameView gameView = new GameView();
            gameView.setVisible(true);
            // Obtener el mapa como char[][]
            try {
                Stage stage = new Stage("mapa.txt");
                char[][] map = stage.getMapa();
                // Mostrar el mapa en el TextArea
                gameView.imprimirMapa(map);

                // Crear jugador
                Player player1 = new Player(8, 20, Direction.RIGHT, "A");

                // Actualizar Mapa
                gameView.actualizarMapa(map, player1);
                
                // Mostrar el mapa en el TextArea
                gameView.imprimirMapa(map);

            } catch (IOException e) {
                // Manejar errores de lectura del mapa aquí
                e.printStackTrace();
            }

        });
    }
}
