package world;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Sound {

    private Clip clip;
    private HashMap<String, URL> urlSound = new HashMap<>();

    private URL soundURL[] = new URL[30];

    public Sound() {
        this.urlSound.put("Jump", getClass().getResource("sound\\Jump.wav"));
        this.urlSound.put("Coin", getClass().getResource("sound\\Coin.wav"));
        this.urlSound.put("Background", getClass().getResource("sound\\Background.wav"));
        this.urlSound.put("Roar", getClass().getResource("sound\\LionRoar.wav"));

        soundURL[0] = getClass().getResource("sound/Jump.wav");
        soundURL[1] = getClass().getResource("sound/Coin.wav");
        soundURL[2] = getClass().getResource("sound/Background.wav");
        soundURL[3] = getClass().getResource("sound/LionRoar.wav");
    }

    public void setFile(String url) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(this.urlSound.get(url));
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);

        } catch (Exception e) {
        }
    }

    public void setFile(int i)  {
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
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
    }

    public void stop() {
        this.clip.stop();
    }
}
