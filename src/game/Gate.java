package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Gate extends StaticBody implements CollisionListener, StepListener {

    private static final BodyImage gateRightImage = new BodyImage("data/GateRight.png", 16);
    private static final BodyImage gateLeftImage = new BodyImage("data/GateLeft.png",16);
    private static final Shape gateShape = new PolygonShape(0.05f,2.37f, -0.62f,2.37f, -0.66f,0.83f, -0.02f,0.93f);
    private float lives = 2;

    public Gate(World world, float x, float y, boolean rightFacing) {
        super(world, gateShape);
        this.setPosition(new Vec2(x,y));
        if (rightFacing) {
            this.addImage(gateRightImage);
        } else {
            this.addImage(gateLeftImage);
        }

        this.addCollisionListener(this);
        world.addStepListener(this);
    }

    public void reduceHealth() {
        this.lives -= 0.5f;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody().getName() == "Stone") {
            reduceHealth();
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (this.lives <= 0) {
            this.destroy();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
