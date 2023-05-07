package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.EventListener;

/**
 * Class which creates an object simulating a coin which can be collected by the player
 * The coins are ghostly fixtures and so do not collide with any other body
 */
public class Coin extends StaticBody implements EventListener {

    private World world;
    private static final Shape collectibleShape = new PolygonShape(-0.076f,0.489f, 0.13f,0.014f, -0.022f,-0.386f, -0.151f,0.036f
    );
    private static final BodyImage collectibleImage = new BodyImage("data/Collectible.png", 1);
    private float x,y;
    private Slingshot slingshotBoy;


    public Coin(World world, float x, float y, Slingshot slingshotBoy) {
        super(world);
        this.world = world;
        this.x = x;
        this.y = y;
        this.slingshotBoy = slingshotBoy;

        this.setPosition(new Vec2(x, y));
        this.addImage(collectibleImage);
        GhostlyFixture fixture = new GhostlyFixture(this, collectibleShape);

        Sensor sensor = new Sensor(this, collectibleShape);
        CoinSensor coinSensor = new CoinSensor(this.slingshotBoy, this);
        sensor.addSensorListener(coinSensor);

    }



    public class CoinSensor implements SensorListener {

        private SoundClip coinSound;
        private Slingshot slingshotBoy;
        private Coin coin;

        public CoinSensor(Slingshot slingshotBoy, Coin coin) {
            this.slingshotBoy = slingshotBoy;
            this.coin = coin;
        }


        /**
         * Overrides the beginContact method to allow for the main character to collect coins
         * and increase their score as they do so.
         * @param sensorEvent
         */
        @Override
        public void beginContact(SensorEvent sensorEvent) {

            if (sensorEvent.getContactBody() instanceof Slingshot) {
                try {
                    coinSound = new SoundClip("sound/Coin.wav");
                    this.coin.destroy();
                    this.slingshotBoy.addScore(10);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
                coinSound.play();
            }
        }

        @Override
        public void endContact(SensorEvent sensorEvent) {

        }
    }





}
