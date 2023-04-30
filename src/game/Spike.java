package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Spike extends StaticBody {

    private static final BodyImage spikeImage = new BodyImage("data/Spike.png",2);
    private static final Shape spikeShape = new PolygonShape(-0.868f,0.088f, 0.848f,0.088f, 0.848f,-0.732f, -0.912f,-0.74f);

    public Spike(World world, float x, float y) {
        super(world, spikeShape);
        this.setPosition(new Vec2(x, y));
        this.addImage(spikeImage);
    }
}
