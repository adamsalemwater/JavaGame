package StartMenu;

import city.cs.engine.SoundClip;
import game.Game;
import game.HighScoreReader;
import game.StartBackground;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Adam Rezzag Salem
 * @date 7th April 2023
 * The StartMenu class creates a JPanel which can be added to a frame which contains three buttons and a background
 */
public class StartMenu {
    private JPanel menuPanel;
    private JButton startButton;
    private JButton exitButton;
    private JButton help;
    private JLabel highScore;
    private SoundClip clip;
    private SoundClip buttonClick;

    public StartBackground getMenuPanel() {
        return (StartBackground) this.menuPanel;
    }

    public StartMenu(Game game) throws IOException {

        highScore.setText("High Score : " + getHighScore());
        Font font = new Font("Bernard MT Condensed", Font.BOLD, 20);
        highScore.setFont(font);

        /**
         * Start button removes the current panel and begins processing the first level
         * It also stops the previous background music to prevent sound clashing
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonClick = new SoundClip("sound/ClickSound.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                buttonClick.play();

                try {
                    game.switchLevelOne();
                    clip.stop();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        /**
         * The help action listener calls the instructionPage() from the game class
         * This removes the start menu panel from the frame and adds the help panel
         */
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonClick = new SoundClip("sound/ClickSound.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                buttonClick.play();
                game.instructionPage();
            }
        });
    }

    /**
     *
     * @return A string which is the current high score to then be displayed on the start screen
     * Firstly, we need to check if the HighScore file which contains the high scores of any previous
     * tries exists or not so that we don't get a FileNotFoundException
     * @throws IOException
     */
    public String getHighScore() throws IOException {
        File file = new File("HighScore");
        if (file.exists()) {
            HighScoreReader highScoreReader = new HighScoreReader("HighScore");
            return Integer.toString(highScoreReader.getReadScore());
        }
        return "0";
    }

    public SoundClip getClip() {
        return clip;
    }

    private void createUIComponents() {
        highScore = new JLabel();
        try {
            clip = new SoundClip("sound/StartMenu.wav");
            clip.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        menuPanel = new StartBackground();
    }
}
