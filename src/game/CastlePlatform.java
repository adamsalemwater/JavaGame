package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class CastlePlatform extends StaticBody {

    private static final BodyImage platformImage = new BodyImage("data/CastlePlatform.png",7);
    private static final Shape platformShape = new PolygonShape(-3.43f,0.83f, -3.47f,-0.12f, 2.99f,-0.27f, 3.01f,0.79f);


    public CastlePlatform(World world, float x, float y) {
        super(world, platformShape);
        this.setPosition(new Vec2(x,y));
        this.addImage(platformImage);
    }
}
