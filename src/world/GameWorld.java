package world;

import city.cs.engine.*;
import org.jbox2d.collision.Collision;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameWorld extends World implements ActionListener {

    private World world;


    private Slingshot slingshotBoy;
    private Dragon dragon;
    private BlueKnight blueKnight;
    private RedKnight redKnight;
    private Fireball fireball;


    public GameWorld()  {
        super();

        this.world = new World();

        // make the ground
        BodyImage platformImage = new BodyImage("data/platform.png",18f);
        Shape shape = new PolygonShape(-10.97f,4.52f, 9.18f,4.38f, 9.18f,-1.35f, -10.9f,-1.69f);
        StaticBody ground = new StaticBody(this.world, shape);
        SolidFixture actualGround = new SolidFixture(ground, shape);
        actualGround.setFriction(1f);
        ground.setPosition(new Vec2(0f, -13.5f));
        ground.addImage(platformImage);

        // make the boy with the slingshot character

        slingshotBoy = new Slingshot(world, -5, -9f, true);
        slingshotBoy.setImage();


        // create a dragon object

        dragon = new Dragon(world, 4, -8, true, slingshotBoy);
        dragon.setImage();


        // add everything that makes the world such as platforms and other objects




        // create two suspended platforms using a for loop on the same y-axis



        for (int xCoord=-5; xCoord < 15; xCoord+=10) {

            int yCoord = 0;
            BodyImage platformImage2 = new BodyImage("data/platform.png", 5f);
            Shape platformShape = new PolygonShape(-3.01f,1.31f, 2.53f,1.33f, 2.57f,-0.51f, -3.03f,-0.53f
            );
            StaticBody platform = new StaticBody(world, platformShape);
            platform.setPosition(new Vec2(xCoord, yCoord));
            platform.addImage(platformImage2);

        }

        // create a blue knight on one platform and a red knight on the other

        blueKnight = new BlueKnight(world, -5, 3, true);
        blueKnight.setImage();

        redKnight = new RedKnight(world, 5,0,false);
        redKnight.setImage();

        // create a two small platform which are near the edges of the screen

        for (int xCoord=-12; xCoord<15; xCoord+=24) {

            float yCoord = -4.5f;
            BodyImage platformImage3 = new BodyImage("data/platform.png", 3.5f);
            Shape platformShape = new PolygonShape(-2.11f,0.88f, 1.8f,0.88f, 1.78f,-0.29f, -2.11f,-0.32f);
            StaticBody platform = new StaticBody(world, platformShape);
            platform.setPosition(new Vec2(xCoord, yCoord));
            platform.addImage(platformImage3);
        }



        // add two mushrooms either side of the ground which the boy can use to bounce onto the suspended platforms

        for (int xCoord=-9; xCoord<10; xCoord+=17) {
            BodyImage mushroomImage = new BodyImage("data/Mushroom.png", 2.5f);
            Shape mushroomShape = new PolygonShape(-0.067f,1.245f, 1.244f,0.206f, 0.175f,-1.131f, -1.265f,0.047f
            );
            StaticBody mushroomBody = new StaticBody(world, mushroomShape);
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
            StaticBody platform = new StaticBody(world, platformShape);
            platform.setPosition(new Vec2(xCoord, 4));
            platform.addImage(platformImage3);
        }

        // adding four portals which place the character on to the suspended platforms above

        Portal redPortal = new Portal(world, -12, 6, true, new Vec2(10, -2.2f));
        Portal bluePortal = new Portal(world, 12, 6.3f, false, new Vec2(-10, -2.5f));

        Portal bluePortal2 = new Portal(world, -12, -2.5f,false, new Vec2(10,6.3f));
        Portal redPortal2 = new Portal(world, 12, -2.2f, true, new Vec2(-10, 6));


        Timer timer = new Timer(100, this);
        timer.setDelay(3000);
        timer.start();


        this.world.start();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
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
            this.fireball = new Fireball(world, dragon);
        }

    }
}
