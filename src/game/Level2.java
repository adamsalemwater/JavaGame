package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;


public class Level2 extends GameLevel implements ActionListener, StepListener {

    private Slingshot slingshotBoy;
    private Dragon dragon;
    private BlueKnight blueKnight;
    private RedKnight redKnight;
    private MachineGun machineGun;
    private ArrayList<Key> keyList = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Game game;
    private SoundClip clip;
    private boolean flag;

    public Level2(Game game)  {
        super(2);

        this.game = game;

        this.addStepListener(this);

        // adding the background music
        try {
            clip = new SoundClip("sound/Level2.wav");
            clip.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }

        // make the ground
        BodyImage platformImage = new BodyImage("data/RockPlatform.png",30);
        Shape shape = new PolygonShape(-12.8f,6.2f, 13.1f,6.2f, 13.0f,-3.4f, -12.7f,-3.5f);
        StaticBody ground = new StaticBody(this, shape);
        SolidFixture actualGround = new SolidFixture(ground, shape);
        actualGround.setFriction(1f);
        ground.setPosition(new Vec2(0f, -20));
        ground.addImage(platformImage);

        // make the boy with the slingshot character

        slingshotBoy = new Slingshot(this, -5, -9, true, 20, 20);
        slingshotBoy.setImage();

        // Add the dragon to the platform which can jump and moves faster

        dragon = new Dragon(this, 6, -7, false, slingshotBoy, 7, -1);
        dragon.setImage();
        dragon.setMove(5);


        // adding all platforms to the level

        LavaPlatform lavaPlatform = new LavaPlatform(this, -13, 0);
        LavaPlatform lavaPlatform1 = new LavaPlatform(this, -13, 10);
        LavaPlatform lavaPlatform2 = new LavaPlatform(this, 13, 0);
        LavaPlatform lavaPlatform3 = new LavaPlatform(this, 13, 10);

        LavaPlatform lavaPlatform5 = new LavaPlatform(this, 0, 14);
        LavaPlatform lavaPlatform6 = new LavaPlatform(this, 15.5f, -10);

        // placing medium-sized platforms among the smaller platforms

        BodyImage mediumPlatformImage = new BodyImage("data/RockPlatform.png", 15);
        Shape mediumPlatformShape = new PolygonShape(-6.36f,3.05f, 6.55f,3.08f, 6.58f,-1.65f, -6.39f,-1.65f);
        StaticBody mediumPlatform = new StaticBody(this, mediumPlatformShape);
        mediumPlatform.addImage(mediumPlatformImage);
        mediumPlatform.setPosition(new Vec2(0, 3));

        BodyImage mediumPlatformImage2 = new BodyImage("data/RockPlatform.png", 11);
        Shape mediumPlatformShape2 = new PolygonShape(-4.64f,2.24f, 4.78f,2.28f, 4.89f,-1.3f, -4.68f,-1.21f);
        StaticBody mediumPlatform2 = new StaticBody(this, mediumPlatformShape2);
        mediumPlatform2.setPosition(new Vec2(0, -7));
        mediumPlatform2.addImage(mediumPlatformImage2);


        // adding portals

        Portal portal = new Portal(this, -17, -6.25f, true, new Vec2(-12, 12));
        Portal portal2 = new Portal(this, -16, 12.75f, true, new Vec2(-13, -6.25f));

        Portal portal3 = new Portal(this, 17, -7, false, new Vec2(13, 12.5f));
        Portal portal4 = new Portal(this, 15, 13, false, new Vec2(16, -7.5f));

        // add trees on either side of the large platform

        Tree tree1 = new Tree(this, -12.5f, -12.5f);
        Tree tree2 = new Tree(this, 12.5f, -12.5f);

        // add small rocks which slowly fall if the main character stays on for too long


        SmallRock smallRock2 = new SmallRock(this, -12, -10);
        SmallRock smallRock3 = new SmallRock(this, -15, -10);
        SmallRock smallRock = new SmallRock(this, -8, -7);



        // add other enemies to the level

        redKnight = new RedKnight(this, 13, 2, true);
        redKnight.setRightBorder(16);
        redKnight.setLeftBorder(10.5f);
        redKnight.setMove(2.2f);
        redKnight.setImage();

        blueKnight = new BlueKnight(this, -13, 2, false);
        blueKnight.setRightBorder(-10);
        blueKnight.setLeftBorder(-15.5f);
        blueKnight.setMove(2.2f);
        blueKnight.setImage();

        // add machine guns which track the main character

        machineGun = new MachineGun(this, -10,14, true, slingshotBoy);


        // add keys for the slingshot character to collect

        Key key = new Key(this, 0, 11);
        Key key2 = new Key(this, 10, 15);
        Key key3 = new Key(this, 10, -10);
        Key key4 = new Key(this, 0, -4);

        keyList.add(key);
        keyList.add(key2);
        keyList.add(key3);
        keyList.add(key4);

        // add collectibles for extra points for the player to collect

        Coin coin = new Coin(this, 0, -1);
        Coin coin2 = new Coin(this, 4, -1);
        Coin coin3 = new Coin(this, -4, -1);
        Coin coin4 = new Coin(this, -12, -6 );
        Coin coin5 = new Coin(this, 12, -5);
        Coin coin6 = new Coin(this, 2, 16.5f);
        Coin coin7 = new Coin(this, -2, 16.5f);


        Timer timer = new Timer(30, this);
        timer.setDelay(2000);
        timer.setRepeats(true);
        timer.start();

        enemies.add(dragon);
        enemies.add(blueKnight);
        enemies.add(redKnight);
        enemies.add(machineGun);

    }


    public Slingshot getSlingshotBoy() {
        return this.slingshotBoy;
    }

    public SoundClip getClip() {
        return clip;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dragon.jump(3);
        if (this.dragon.getLives() > 0) {
            Fireball fireball = new Fireball(this, this.dragon);
            fireball.setxVel(15);
            fireball.setyVel(20);
        }

        if (this.machineGun.getLives() > 0 && Point2D.distance(slingshotBoy.getPosition().x, slingshotBoy.getPosition().y, machineGun.getPosition().x, machineGun.getPosition().y) < 12) {
            MachineGun.Laser laser = machineGun.new Laser(this, machineGun, slingshotBoy);
        }

    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (slingshotBoy.getPosition().y < -20) {
            slingshotBoy.setPosition(new Vec2(6,10));
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (keyList.size() <= slingshotBoy.getKeys() && !this.flag) {
            Door door = new Door(this, 0, -2, game);
            this.flag = true;
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public MachineGun getMachineGun() {
        return machineGun;
    }

    public BlueKnight getBlueKnight() {
        return blueKnight;
    }

    public RedKnight getRedKnight() {
        return redKnight;
    }


}
