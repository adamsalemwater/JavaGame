package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class Collectible  {

    private World world;
    private static final Shape collectibleShape = new PolygonShape(-0.076f,0.489f, 0.13f,0.014f, -0.022f,-0.386f, -0.151f,0.036f
    );
    private static final BodyImage collectibleImage = new BodyImage("data/Collectible.png", 1);


    public Collectible(World world) {
        this.world = world;

        Random random = new Random();
        int randomCoord = random.nextInt(-5, 5);
        StaticBody collectibleBody = new StaticBody(world, collectibleShape);
        collectibleBody.setPosition(new Vec2(randomCoord, randomCoord));
        collectibleBody.addImage(collectibleImage);
        GhostlyFixture collectible = new GhostlyFixture(collectibleBody, collectibleShape);
        collectibleBody.setName("Collectible");
        collectible.setDensity(0);
    }

}
