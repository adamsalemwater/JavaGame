package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmallRock extends StaticBody {

    private static final Shape smallRockShape = new PolygonShape(-0.71f,0.5f, 0.6f,0.5f, -0.08f,-0.51f);
    private static final BodyImage smallRockImage = new BodyImage("data/FallingRock.png", 3f);
    private float x;
    private float y;
    private World world;

    public SmallRock(World world, float x, float y) {
        super(world, smallRockShape);
        this.world = world;
        this.x = x;
        this.y = y;
        this.setPosition(new Vec2(x, y));
        this.addImage(smallRockImage);

    }

    public World getWorld() {
        return this.world;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}


