package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Key extends StaticBody implements CollisionListener {

    private static final Shape keyShape = new PolygonShape(-0.29f,0.482f, 0.322f,0.48f, 0.3f,-0.418f, -0.234f,-0.426f
    );
    private static final BodyImage keyImage = new BodyImage("data/Key.png", 1);
    private World world;

    public Key(World world, float x, float y) {
        super(world, keyShape);
        this.world = world;
        this.setPosition(new Vec2(x, y));
        this.addImage(keyImage);
        this.addCollisionListener(this);
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        collisionEvent.getReportingBody().destroy();
        if (collisionEvent.getOtherBody() instanceof Slingshot) {
            ((Slingshot)collisionEvent.getOtherBody()).addKey();
        }
    }

}
