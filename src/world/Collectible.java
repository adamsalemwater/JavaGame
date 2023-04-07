package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;


public class Collectible extends StaticBody {

    private World world;
    private static final Shape collectibleShape = new PolygonShape(-0.076f,0.489f, 0.13f,0.014f, -0.022f,-0.386f, -0.151f,0.036f
    );
    private static final BodyImage collectibleImage = new BodyImage("data/Collectible.png", 1);
    private float x,y;


    public Collectible(World world) {
        super(world,collectibleShape);
        this.world = world;
        Random random = new Random();
        float randomY = random.nextFloat(-5,5);
        int switching = random.nextInt();
        if (switching == 0) {
            this.x = -4;
        } else {
            this.x = 4;
        }
        this.y = randomY;

        this.setPosition(new Vec2(x, y));
        this.addImage(collectibleImage);
        GhostlyFixture collectible = new GhostlyFixture(this, collectibleShape);
        this.setName("Collectible");
    }



}
