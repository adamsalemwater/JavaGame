package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Level3 extends GameLevel implements ActionListener, StepListener {

    private Slingshot slingshotBoy;
    private Game game;
    private SoundClip clip;
    private ArrayList<Key> keyList = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private MachineGun machineGun, machineGun2;
    private Dragon dragon, dragon2;
    private RedKnight redKnight, redKnight2;
    private BlueKnight blueKnight, blueKnight2;
    boolean flag = false;
    public Level3(Game game) {
        super(3);

        try {
            clip = new SoundClip("sound/Level3.wav");
            clip.loop();
        } catch ( UnsupportedAudioFileException|LineUnavailableException| IOException e) {
            System.out.println(e);
        }

        this.game = game;


        CastlePlatform platform = new CastlePlatform(this, -8.5f, -3);


        // add rotated platform


        CastlePlatform platform2 = new CastlePlatform(this, -2, 0);
        platform2.rotate(new Vec2(-10,-2),(float)-Math.PI/4);


        CastlePlatform platform3 = new CastlePlatform(this, 7.5f, -5);

        CastlePlatform platform4 = new CastlePlatform(this, 14.5f, -5);

        for (int i=-2; i<10; i+=5) {
            CastlePlatform loopPlatform = new CastlePlatform(this, i, 5);
        }


        for (int i=-10; i<0; i+=5) {
            CastlePlatform upperLeftPlatforms = new CastlePlatform(this, i, 13);
        }

        CastlePlatform platform9 = new CastlePlatform(this, 10, 14);

        for (int i=-15; i<20; i+=10) {
            CastlePlatform morePlatforms = new CastlePlatform(this, i, -13);
        }



        // spikes to the end of the rotated platform

        Spike spike = new Spike(this, 2, -8);

        // add borders around the spikes

        BodyImage smallPlatformImage = new BodyImage("data/CastlePlatform.png", 4);
        Shape smallShape = new PolygonShape(-1.98f,0.5f, -1.97f,-0.06f, 1.57f,-0.1f, 1.6f,0.51f);
        StaticBody smallBody = new StaticBody(this, smallShape);
        smallBody.rotate(new Vec2(-3.5f,-6), (float) (3*Math.PI / 2));
        smallBody.setPosition(new Vec2(0.5f, -6));
        smallBody.addImage(smallPlatformImage);

        StaticBody smallBody2 = new StaticBody(this, smallShape);
        smallBody2.rotate(new Vec2(-0.5f, -6), (float) Math.PI/2);
        smallBody2.setPosition(new Vec2(3.5f, -6));
        smallBody2.addImage(smallPlatformImage);

        StaticBody smallBody3 = new StaticBody(this, smallShape);
        smallBody3.setPosition(new Vec2(-13, 5));
        smallBody3.addImage(smallPlatformImage);

        StaticBody smallBody4 = new StaticBody(this, smallShape);
        smallBody4.setPosition(new Vec2(3, 13));
        smallBody4.addImage(smallPlatformImage);


        // add main character

        slingshotBoy = new Slingshot(this, 10, -3, true, 10, -3, -25);
        slingshotBoy.setImage();

        // add knights

        redKnight = new RedKnight(this, -4, -6, true);
        redKnight.setRightBorder(-2);
        redKnight.setLeftBorder(-7.5f);
        redKnight.setImage();
        redKnight.setLives(3.5f);
        redKnight.setName("RedKnight");
        redKnight.setFullLife(3.5f);

        redKnight2 = new RedKnight(this, -6, 0, false);
        redKnight2.setRightBorder(-4.5f);
        redKnight2.setLeftBorder(-10);
        redKnight2.setImage();
        redKnight2.setLives(3.5f);
        redKnight2.setName("RedKnight2");

        blueKnight = new BlueKnight(this, 12,17, true);
        blueKnight.setRightBorder(13);
        blueKnight.setLeftBorder(9);
        blueKnight.setImage();
        blueKnight.setLives(3.5f);
        blueKnight.setName("BlueKnight");
        blueKnight.setFullLife(3.5f);

        blueKnight2 = new BlueKnight(this, 5, -12, false);
        blueKnight2.setRightBorder(7.5f);
        blueKnight2.setLeftBorder(3);
        blueKnight2.setImage();
        blueKnight2.setLives(3.5f);
        blueKnight2.setName("BlueKnight2");
        blueKnight.setFullLife(3.5f);

        // add machinegun

        machineGun = new MachineGun(this, -14, 11, true, slingshotBoy);
        machineGun.setName("MachineGun");

        machineGun2 = new MachineGun(this, -16, 3, true, slingshotBoy);
        machineGun2.setName("MachineGun2");

        // add dragons

        dragon = new Dragon(this, 10, 10, true, slingshotBoy, 11, 0);
        dragon.setImage();
        dragon.setMove(4);
        dragon.setLives(6.5f);
        dragon.setName("Dragon");
        dragon.setFullLife(6.5f);

        dragon2 = new Dragon(this, -5, 16, false, slingshotBoy, -2, -10);
        dragon2.setImage();
        dragon2.setMove(3);
        dragon2.setLives(6.5f);
        dragon2.setName("Dragon2");
        dragon2.setFullLife(6.5f);

        // adding all keys to the game

        Key key = new Key(this, -13, 6);
        Key key2 = new Key(this, 14, 6);
        Key key3 = new Key(this, 16, -11.5f);
        Key key4 = new Key(this, -15.5f, -11.25f);
        Key key5 = new Key(this, -6, -8);

        // add gates and small platforms which has to be broken to get to the key

        StaticBody smallPlatform4 = new StaticBody(this, smallShape);
        smallPlatform4.setPosition(new Vec2(16, -10f));
        smallPlatform4.addImage(smallPlatformImage);

        Gate gate = new Gate(this, 14.85f,-12, false);

        StaticBody smallPlatform5 = new StaticBody(this, smallShape);
        smallPlatform5.setPosition(new Vec2(-16, -10));
        smallPlatform5.addImage(smallPlatformImage);

        Gate gate2 = new Gate(this, -14f, -12.5f, true);


        // add portals to level

        Portal portal = new Portal(this, 14, -2, true, new Vec2(5, 15));
        Portal portal2 = new Portal(this, 3, 15, true, new Vec2(12, -2));
        Portal portal3 = new Portal(this, -1, 0, false, new Vec2(-14.5f, -6));
        Portal portal4 = new Portal(this, -16.5f, -6, false, new Vec2(1, 0));

        // add coins to the level

        Coin coin = new Coin(this, -14, -6.5f, slingshotBoy);
        Coin coin2 = new Coin(this, 0, 14.5f, slingshotBoy);
        Coin coin3 = new Coin(this, 2, 14.5f, slingshotBoy);
        Coin coin4 = new Coin(this, 1, -2, slingshotBoy);
        Coin coin5 = new Coin(this, 3, -2, slingshotBoy);
        Coin coin6 = new Coin(this, 15, 3.5f, slingshotBoy);
        Coin coin7 = new Coin(this, 14, -8, slingshotBoy);
        Coin coin8 = new Coin(this, 16, -8, slingshotBoy);



        enemies.add(machineGun);
        enemies.add(machineGun2);
        enemies.add(dragon);
        enemies.add(dragon2);
        enemies.add(blueKnight);
        enemies.add(blueKnight2);
        enemies.add(redKnight);
        enemies.add(redKnight2);

        Timer timer = new Timer(0, this);
        timer.setDelay(1500);
        timer.start();


    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public Slingshot getSlingshotBoy() {
        return this.slingshotBoy;
    }

    public SoundClip getClip() {
        return clip;
    }


    public ArrayList<Key> getKeyList() {
        return keyList;
    }

    public MachineGun getMachineGun() {
        return machineGun;
    }

    public MachineGun getMachineGun2() {
        return machineGun2;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public Dragon getDragon2() {
        return dragon2;
    }

    public RedKnight getRedKnight() {
        return redKnight;
    }

    public RedKnight getRedKnight2() {
        return redKnight2;
    }

    public BlueKnight getBlueKnight() {
        return blueKnight;
    }

    public BlueKnight getBlueKnight2() {
        return blueKnight2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double distanceFromMG1 = Point2D.distance(slingshotBoy.getPosition().x, slingshotBoy.getPosition().y, machineGun.getPosition().x, machineGun.getPosition().y);
        double distanceFromMG2 = Point2D.distance(slingshotBoy.getPosition().x, slingshotBoy.getPosition().y, machineGun2.getPosition().x, machineGun2.getPosition().y);

        if (machineGun.getLives() > 0 && distanceFromMG1 < 20) {
            MachineGun.Laser laser = machineGun.new Laser(this, machineGun, slingshotBoy);
        }

        if (machineGun2.getLives() > 0 && distanceFromMG2 < 20) {
            MachineGun.Laser laser = machineGun2.new Laser(this, machineGun2, slingshotBoy);
        }

        if (dragon.getLives() > 0) {
            dragon.jump(4);
            Fireball fireball = new Fireball(this, dragon);
        }

        if (dragon2.getLives() > 0) {
            dragon2.jump(4);
            Fireball fireball = new Fireball(this, dragon2);
        }

        if (blueKnight.getLives() > 0) {
            blueKnight.jump(4);
        }
        if (blueKnight2.getLives() > 0) {
            blueKnight2.jump(4);
        }
        if (redKnight.getLives() > 0) {
            redKnight.jump(4);
        }
        if (redKnight2.getLives() > 0) {
            redKnight2.jump(4);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (keyList.size() == slingshotBoy.getKeys() && !this.flag) {
            Door door = new Door(this, 15, -8.5f, this.game);
            this.flag = true;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
