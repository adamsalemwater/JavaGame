package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Coin extends StaticBody {

    private World world;
    private static final Shape collectibleShape = new PolygonShape(-0.076f,0.489f, 0.13f,0.014f, -0.022f,-0.386f, -0.151f,0.036f
    );
    private static final BodyImage collectibleImage = new BodyImage("data/Collectible.png", 1);
    private float x,y;


    public Coin(World world, float x, float y) {
        super(world,collectibleShape);
        this.world = world;
        this.x = x;
        this.y = y;

        this.setPosition(new Vec2(x, y));
        this.addImage(collectibleImage);
        GhostlyFixture collectible = new GhostlyFixture(this, collectibleShape);
        this.setName("Collectible");
    }



}
