package GameOver;

import game.Game;
import game.GameOverBackground;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {


    private JPanel mainPanel;
    private JTextField highScoreTextField;
    private JButton restartButton;
    private JButton exitButton;


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

    public GameOverBackground getMainPanel() {
        return (GameOverBackground) this.mainPanel;
    }

    private void createUIComponents() {
        mainPanel = new GameOverBackground();
    }
}
