package StartMenu;

import city.cs.engine.SoundClip;
import game.Game;
import game.StartBackground;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartMenu {
    private JPanel menuPanel;
    private JButton startButton;
    private JButton exitButton;
    private SoundClip clip;

    public StartBackground getMenuPanel() {
        return (StartBackground) this.menuPanel;
    }

    public StartMenu(Game game) {
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
                    game.switchLevelOne();
                    clip.stop();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


    }

    public SoundClip getClip() {
        return clip;
    }

    private void createUIComponents() {
        try {
            clip = new SoundClip("sound/StartMenu.wav");
            clip.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        menuPanel = new StartBackground();
    }
}
