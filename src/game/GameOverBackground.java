package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameOverBackground extends JPanel {

    private boolean won;
    private Image backgroundWon;
    private Image backgroundLost;
    private SoundClip clip;

    public GameOverBackground() {
        backgroundWon = new ImageIcon("data/GameSuccess.png").getImage();
        backgroundLost = new ImageIcon("data/GameOver.png").getImage();
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (won) {
            g.drawImage(backgroundWon, 0, 0, 500, 500, null);
            try {
                clip = new SoundClip("sound/Success.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            clip.play();
        } else {
            g.drawImage(backgroundLost, 0, 0, 500, 500, null);
            try {
                clip = new SoundClip("sound/Failure.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
             e.printStackTrace();
            }
            clip.play();
        }
    }
}
