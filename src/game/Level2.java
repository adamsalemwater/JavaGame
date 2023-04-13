package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Level2 extends GameLevel implements ActionListener {

    private Slingshot slingshotBoy;
    private Dragon dragon;

    public Level2() {
        super(2);

        // make the ground
        BodyImage platformImage = new BodyImage("data/RockPlatform.png",20f);
        Shape shape = new PolygonShape(-8.56f,4.11f, -8.47f,-2.33f, 8.69f,-2.12f, 8.77f,4.15f);
        StaticBody ground = new StaticBody(this, shape);
        SolidFixture actualGround = new SolidFixture(ground, shape);
        actualGround.setFriction(1f);
        ground.setPosition(new Vec2(0f, -13.5f));
        ground.addImage(platformImage);

        // make the boy with the slingshot character

        slingshotBoy = new Slingshot(this, -5, -9, true);
        slingshotBoy.setImage();

        // Add the dragon to the platform which can jump and moves faster

        dragon = new Dragon(this, 6, -7, false, slingshotBoy, -5, 7);
        dragon.setMove(2.5f);
        dragon.setImage();

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
        dragon.jump(6);
        if (this.dragon.getLives() > 0) {
            Fireball fireball = new Fireball(this, this.dragon);
            fireball.setxVel(15);
            fireball.setyVel(20);
        }
    }
}
