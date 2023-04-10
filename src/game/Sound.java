package game;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

public class Sound implements ActionListener {

    private Clip clip;
    private HashMap<String, File> urlSound = new HashMap<>();


    public Sound() {
        this.urlSound.put("Jump", new File("sound/Jump.wav"));
        this.urlSound.put("Coin", new File("sound/Coin.wav"));
//        this.urlSound.put("Background", new File("sound/Background.wav"));
        this.urlSound.put("Throw", new File("sound/Throw.wav"));
        this.urlSound.put("Smash", new File("sound/Smash.wav"));
        this.urlSound.put("Spring", new File("sound/Spring.wav"));

    }

    public void setFile(String url) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(this.urlSound.get(url));
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);

        } catch (Exception e) {
        }
    }


    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        this.clip.start();
        Timer timer = new Timer(100, this);
        timer.setRepeats(false);
        timer.start();
    }

    public void stop() {
        this.clip.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.stop();
    }
}
