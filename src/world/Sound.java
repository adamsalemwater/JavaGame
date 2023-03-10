package world;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Sound {

    private Clip clip;
    private HashMap<String, File> urlSound = new HashMap<>();

    private URL soundURL[] = new URL[30];

    public Sound() {
        this.urlSound.put("Jump", new File("sound/Jump.wav"));
        this.urlSound.put("Coin", new File("sound/Coin.wav"));
        this.urlSound.put("Background", new File("sound/Background.wav"));
        this.urlSound.put("Roar", new File("sound/LionRoar.wav"));



    }

    public void setFile(String url) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(this.urlSound.get(url));
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
            System.out.println(clip);

        } catch (Exception e) {
        }
    }


    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        this.clip.start();
    }

    public void stop() {
        this.clip.stop();
    }
}
