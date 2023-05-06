package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Level1 extends GameLevel implements ActionListener, StepListener {


        private Slingshot slingshotBoy;
        private Dragon dragon;
        private BlueKnight blueKnight;
        private RedKnight redKnight;
        private Fireball fireball;
        private Game game;
        private ArrayList<Key> keyList = new ArrayList<>();
        private ArrayList<Enemy> enemies = new ArrayList<>();
        private SoundClip clip;
        boolean flag = false;

        public Level1(Game game)  {
            super(1);

            try {
                clip = new SoundClip("sound/Level1.wav");
                clip.loop();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            this.game = game;

            this.addStepListener(this);


            // make the ground
            BodyImage platformImage = new BodyImage("data/platform.png",18f);
            Shape shape = new PolygonShape(-10.97f,4.52f, 9.18f,4.38f, 9.18f,-1.35f, -10.9f,-1.69f);
            StaticBody ground = new StaticBody(this, shape);
            SolidFixture actualGround = new SolidFixture(ground, shape);
            actualGround.setFriction(1f);
            ground.setPosition(new Vec2(0f, -13.5f));
            ground.addImage(platformImage);

            // make the boy with the slingshot character

            slingshotBoy = new Slingshot(this, -5, -9f, true, -10, -2.5f, -20);
            slingshotBoy.setImage();



            // create a dragon object

            dragon = new Dragon(this, 4, -8, true, slingshotBoy, 6,-2.5f);
            dragon.setImage();




            // add everything that makes the world such as platforms and other objects




            // create three suspended platforms using a for loop on the same y-axis



            for (int xCoord=-5; xCoord < 15; xCoord+=10) {

                int yCoord = 0;
                BodyImage platformImage2 = new BodyImage("data/platform.png", 5f);
                Shape platformShape = new PolygonShape(-3.01f,1.31f, 2.53f,1.33f, 2.57f,-0.51f, -3.03f,-0.53f
                );
                StaticBody platform = new StaticBody(this, platformShape);
                platform.setPosition(new Vec2(xCoord, yCoord));
                platform.addImage(platformImage2);

            }

            /*
            Create another three platforms which are contain the most collectibles and where the
            door will pop up.
            * */

            for (int xCoord=-15; xCoord < 20; xCoord+=15) {

                int yCoord = 10;
                BodyImage platformImage2 = new BodyImage("data/platform.png", 5f);
                Shape platformShape = new PolygonShape(-3.01f,1.31f, 2.53f,1.33f, 2.57f,-0.51f, -3.03f,-0.53f
                );
                StaticBody platform = new StaticBody(this, platformShape);
                platform.setPosition(new Vec2(xCoord, yCoord));
                platform.addImage(platformImage2);
            }

            Coin coin = new Coin(this, -11, 15, slingshotBoy);
            Coin coin2 = new Coin(this, -9, 15, slingshotBoy);
            Coin coin3 = new Coin(this, -7, 15, slingshotBoy);
            Coin coin4 = new Coin(this, -5, 15, slingshotBoy);
            Coin coin5 = new Coin(this, 4, 15, slingshotBoy);
            Coin coin6 = new Coin(this, 6, 15, slingshotBoy);
            Coin coin7 = new Coin(this, 8, 15, slingshotBoy);
            Coin coin8 = new Coin(this, 10, 15, slingshotBoy);

            // Another two platforms to be able to get onto the other three

            for (float xCoord=-7.5f; xCoord<20; xCoord+=15) {

                float yCoord = 12.5f;
                BodyImage platformImage2 = new BodyImage("data/platform.png", 4);
                Shape platformShape2 = new PolygonShape(2.04f,1.0f, 2.05f,-0.35f, -2.42f,-0.25f, -2.39f,1.0f);
                StaticBody platform2 = new StaticBody(this, platformShape2);
                platform2.setPosition(new Vec2(xCoord, yCoord));
                platform2.addImage(platformImage2);
            }




            // create a blue knight on one platform and a red knight on the other

            blueKnight = new BlueKnight(this, -5, 3, true);
            blueKnight.setImage();


            redKnight = new RedKnight(this, 5,0,false);
            redKnight.setImage();




            // create a two small platform which are near the edges of the screen

            for (int xCoord=-12; xCoord<15; xCoord+=24) {

                float yCoord = -4.5f;
                BodyImage platformImage3 = new BodyImage("data/platform.png", 3.5f);
                Shape platformShape = new PolygonShape(-2.11f,0.88f, 1.8f,0.88f, 1.78f,-0.29f, -2.11f,-0.32f);
                StaticBody platform = new StaticBody(this, platformShape);
                platform.setPosition(new Vec2(xCoord, yCoord));
                platform.addImage(platformImage3);
            }



            // add two mushrooms either side of the ground which the boy can use to bounce onto the suspended platforms

            for (int xCoord=-9; xCoord<10; xCoord+=17) {
                BodyImage mushroomImage = new BodyImage("data/Mushroom.png", 2.5f);
                Shape mushroomShape = new PolygonShape(-0.067f,1.245f, 1.244f,0.206f, 0.175f,-1.131f, -1.265f,0.047f
                );
                StaticBody mushroomBody = new StaticBody(this, mushroomShape);
                mushroomBody.setPosition(new Vec2(xCoord, -8));
                mushroomBody.addImage(mushroomImage);
                SolidFixture mushroomBounce = new SolidFixture(mushroomBody, mushroomShape);
                mushroomBounce.setRestitution(2);
                mushroomBody.setName("Mushroom");
            }


            // add another two suspended platforms which are the destinations for future portals to be placed

            for (int xCoord=-10; xCoord<15; xCoord+=10) {

                BodyImage platformImage3 = new BodyImage("data/platform.png", 4f);
                Shape platformShape = new PolygonShape(-2.45f,1.05f, 2.07f,1.05f, 2.07f,-0.36f, -2.42f,-0.36f);
                StaticBody platform = new StaticBody(this, platformShape);
                platform.setPosition(new Vec2(xCoord, 4));
                platform.addImage(platformImage3);
            }

            // adding four portals which place the character on to the suspended platforms above

            Portal redPortal = new Portal(this, -12, 6, true, new Vec2(10, -2.2f));
            Portal bluePortal = new Portal(this, 12, 6.3f, false, new Vec2(-10, -2.5f));

            Portal bluePortal2 = new Portal(this, -12, -2.5f,false, new Vec2(10,6.3f));
            Portal redPortal2 = new Portal(this, 12, -2.2f, true, new Vec2(-10, 6));

            Portal bluePortal3 = new Portal(this, 0, 7.5f, false, new Vec2(2, 15));
            Portal bluePortal4 = new Portal(this, 0, 14.5f, false, new Vec2(2, 5));



            // Add keys to the game
            Key key = new Key(this, 0, -2);
            Key key2 = new Key(this, -15, 12.5f);
            Key key3 = new Key(this, 15, 12.5f);

            keyList.add(key);
            keyList.add(key2);
            keyList.add(key3);


            Timer timer = new Timer(100, this);
            timer.setDelay(3000);

            enemies.add(dragon);
            enemies.add(redKnight);
            enemies.add(blueKnight);


        }

        public SoundClip getClip() {
            return clip;
        }

         public ArrayList<Enemy> getEnemies() {
            return enemies;
         }

        public Slingshot getSlingshotBoy() {
            return slingshotBoy;
        }

        public void setSlingshotBoy(Slingshot slingshotBoy) {
            this.slingshotBoy = slingshotBoy;
        }

        public Dragon getDragon() {
            return dragon;
        }

        public void setDragon(Dragon dragon) {
            this.dragon = dragon;
        }

        public BlueKnight getBlueKnight() {
            return blueKnight;
        }

        public void setBlueKnight(BlueKnight blueKnight) {
            this.blueKnight = blueKnight;
        }

        public RedKnight getRedKnight() {
            return redKnight;
        }

        public void setRedKnight(RedKnight redKnight) {
            this.redKnight = redKnight;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.getDragon().getLives() > 0) {
                this.fireball = new Fireball(this, dragon);
            }
        }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (keyList.size() <= slingshotBoy.getKeys() && !this.flag) {
            Door door = new Door(this, 17, 12, this.game);
            this.flag = true;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}

