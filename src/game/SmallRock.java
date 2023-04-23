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

        SmallRockHit rockHit = new SmallRockHit(this);
        this.addCollisionListener(rockHit);
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

    public class SmallRockHit implements CollisionListener, StepListener, ActionListener {

        private SmallRock smallRock;
        private float prevX;
        private float prevY;
        private World prevWorld;

        public SmallRockHit(SmallRock smallRock) {
            this.smallRock = smallRock;
            this.prevX = smallRock.getX();
            this.prevY = smallRock.getY();
            this.prevWorld = smallRock.getWorld();
            this.smallRock.getWorld().addStepListener(this);
        }


        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody() instanceof Slingshot &&
                    collisionEvent.getOtherBody().getPosition().y - collisionEvent.getReportingBody().getPosition().y > 0 &&
                    (collisionEvent.getReportingBody().getPosition().x - 0.79) < collisionEvent.getOtherBody().getPosition().x  &&
                    (collisionEvent.getReportingBody().getPosition().x + 0.68) > collisionEvent.getOtherBody().getPosition().x) {
                this.smallRock.move(new Vec2(0, -0.05f));
            }
        }


        @Override
        public void preStep(StepEvent stepEvent) {

        }

        @Override
        public void postStep(StepEvent stepEvent) {
            float displacementY = Math.abs(this.prevY - this.smallRock.getPosition().y);
            if (displacementY > 3) {
                this.smallRock.destroy();
                Timer timer = new Timer(3000, this);
                timer.setRepeats(false);
                timer.start();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SmallRock newSmallRock = new SmallRock(this.prevWorld, this.prevX, this.prevY);
        }
    }
}


