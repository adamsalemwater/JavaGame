package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel {

    private Slingshot slingshotBoy;
    private Game game;
    public Level3(Game game) {
        super(3);

        this.game = game;

        // platform addition
        BodyImage platformImage = new BodyImage("data/CastlePlatform.png",7);
        Shape shape = new PolygonShape(-3.43f,0.83f, -3.47f,-0.12f, 2.99f,-0.27f, 3.01f,0.79f);
        StaticBody ground = new StaticBody(this, shape);
        SolidFixture actualGround = new SolidFixture(ground, shape);
        actualGround.setFriction(1f);
        ground.setPosition(new Vec2(-9, 0));
        ground.addImage(platformImage);


        // add rotated platform

        StaticBody ground2 = new StaticBody(this, shape);
        ground2.rotate(new Vec2(-10, -2), (float)-Math.PI/4);
        ground2.setPosition(new Vec2(-3, -2));
        ground2.addImage(platformImage);

        StaticBody ground3 = new StaticBody(this, shape);
        ground3.setPosition(new Vec2(7.5f, -5));
        ground3.addImage(platformImage);

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

        // add main character

        slingshotBoy = new Slingshot(this, 0, 10, true, 5, -14);
        slingshotBoy.setImage();

        // start level

        this.start();

    }

    public Slingshot getSlingshotBoy() {
        return this.slingshotBoy;
    }
}
