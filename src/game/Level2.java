package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Level2 extends GameLevel implements ActionListener, StepListener {

    private Slingshot slingshotBoy;
    private Dragon dragon;
    private MachineGun machineGun;

    public Level2()  {
        super(2);

        this.addStepListener(this);

        // adding the background music
        try {
            SoundClip backgroundMusic = new SoundClip("sound/BackgroundLevel2.mp3");
            backgroundMusic.loop();
            backgroundMusic.play();
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

        dragon = new Dragon(this, 6, -7, false, slingshotBoy, -5, 7);
        dragon.setMove(2.5f);
        dragon.setImage();
        dragon.setLives(8);


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
        mediumPlatform2.setPosition(new Vec2(0, -2));
        mediumPlatform2.addImage(mediumPlatformImage2);


        // adding portals

        Portal portal = new Portal(this, -17, -6.25f, true, new Vec2(-14, 0));
        Portal portal2 = new Portal(this, -16, 12.75f, true, new Vec2(-15, -6.25f));

        Portal portal3 = new Portal(this, 17, -7, false, new Vec2(13, 12.5f));
        Portal portal4 = new Portal(this, 15, 13, false, new Vec2(16, -7.5f));

        // add trees on either side of the large platform

        Tree tree1 = new Tree(this, -12.5f, -12.5f);
        Tree tree2 = new Tree(this, 12.5f, -12.5f);

        // add small rocks which slowly fall if the main character stays on for too long

        SmallRock smallRock = new SmallRock(this, -13, -11);
        SmallRock smallRock2 = new SmallRock(this, -14, -10);
        SmallRock smallRock3 = new SmallRock(this, -15, -9);



        // add other enemies to the level

        RedKnight redKnight = new RedKnight(this, 13, 2, true);
        redKnight.setRightBorder(16);
        redKnight.setLeftBorder(10.5f);
        redKnight.setMove(2.2f);
        redKnight.setImage();

        BlueKnight blueKnight = new BlueKnight(this, -13, 2, false);
        blueKnight.setRightBorder(-10);
        blueKnight.setLeftBorder(-15.5f);
        blueKnight.setMove(2.2f);
        blueKnight.setImage();

        machineGun = new MachineGun(this, -10,15, true, slingshotBoy);




        this.start();

        Timer timer = new Timer(30, this);
        timer.setDelay(2000);
        timer.setRepeats(true);
        timer.start();



    }


    public Slingshot getSlingshotBoy() {
        return this.slingshotBoy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dragon.jump(3);
        if (this.dragon.getLives() > 0) {
            Fireball fireball = new Fireball(this, this.dragon);
            fireball.setxVel(15);
            fireball.setyVel(20);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (slingshotBoy.getPosition().y < -20) {
            slingshotBoy.setPosition(new Vec2(10,10));
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        MachineGun.Bullet bullet = machineGun.new Bullet(this, machineGun, this.slingshotBoy);
    }
}
