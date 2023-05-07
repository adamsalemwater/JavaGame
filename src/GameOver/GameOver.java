package GameOver;

import city.cs.engine.SoundClip;
import game.Game;
import game.GameOverBackground;
import game.HighScoreReader;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    private SoundClip buttonClick;

    public GameOver(Game game) throws IOException {


        highScore.setText("High Score : " + getHighScore());
        Font font = new Font("Bernard MT Condensed", Font.BOLD, 20);
        highScore.setForeground(Color.red);
        highScore.setFont(font);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonClick = new SoundClip("sound/ClickSound.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                 ex.printStackTrace();
                }
                buttonClick.play();
            }
        });




        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonClick = new SoundClip("sound/ClickSound.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                buttonClick.play();
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
