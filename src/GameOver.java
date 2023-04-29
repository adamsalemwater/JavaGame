import game.Game;
import game.GameOverBackground;
//import game.GameOverBackground;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {
    private JButton restartButton;
    private JPanel gameOverPanel;
    private JButton exitButton;

//    public GameOverBackground getGameOverPanel() {
//        return (GameOverBackground) this.gameOverPanel;
//    }


    public GameOver(Game game) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.switchStartMenu();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        gameOverPanel = new GameOverBackground();
    }
}
