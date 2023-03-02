package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {

    private World world;


    private Slingshot slingshotBoy;

    public GameWorld() {
        super();

        this.world = new World();

        // make the ground
        BodyImage platformImage = new BodyImage("data/platform.png",14f);
        Shape shape = new PolygonShape(-8.42f,3.73f, 7.08f,3.78f, 7.19f,-1.1f, -8.48f,-1.1f);
        StaticBody ground = new StaticBody(this.world, shape);
        SolidFixture actualGround = new SolidFixture(ground, shape);
        actualGround.setFriction(1f);
        ground.setPosition(new Vec2(0f, -13.5f));
        ground.addImage(platformImage);

        // make the boy with the slingshot character

        slingshotBoy = new Slingshot(world);
        slingshotBoy.setPosition(new Vec2(0, -9));

        // add everything that makes the world such as platforms and other objects

        


        // create two suspended platforms using a for loop

        for (int xCoord=-8; xCoord < 10; xCoord+=15) {

            int yCoord = -4;
            BodyImage suspendedPlatform = new BodyImage("data/platform.png", 5f);
            Shape platformShape = new PolygonShape(-3.05f,1.31f, 2.57f,1.29f, 2.55f,-0.41f, -3.03f,-0.41f);
            StaticBody platform1 = new StaticBody(world, platformShape);
            platform1.setPosition(new Vec2(xCoord, yCoord));
            platform1.addImage(suspendedPlatform);

        }


        this.world.start();
    }

    public Slingshot getSlingshotBoy() {
        return slingshotBoy;
    }



    public World getWorld() {
        return world;
    }
}
