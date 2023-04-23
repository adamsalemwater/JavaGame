package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class LavaPlatform extends StaticBody {

    private static final BodyImage platformImage = new BodyImage("data/RockPlatform.png", 8);
    private static final Shape platformShape = new PolygonShape(-3.39f,1.64f, 3.51f,1.63f, 3.49f,-0.88f, -3.36f,-0.88f);

    public LavaPlatform(World world, float x, float y) {
        super(world, platformShape);
        this.setPosition(new Vec2(x, y));
        this.addImage(platformImage);
    }
}
