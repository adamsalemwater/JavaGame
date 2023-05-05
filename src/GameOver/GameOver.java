package GameOver;

import game.Game;
import game.GameOverBackground;
import game.HighScoreReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameOver {


    private JPanel mainPanel;
    private JButton restartButton;
    private JButton exitButton;
    private JLabel highScore;

    public GameOver(Game game) throws IOException {

        highScore.setText("High Score : " + getHighScore());

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

    public String getHighScore() throws IOException {
        File file = new File("HighScore");
        if (file.exists()) {
            HighScoreReader highScoreReader = new HighScoreReader("HighScore");
            return Integer.toString(highScoreReader.getReadScore());
        }
        return "0";
    }

    private void createUIComponents() throws IOException {
        mainPanel = new GameOverBackground();
        highScore = new JLabel();
    }
}
