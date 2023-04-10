package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class BlueKnight extends Enemy implements StepListener {

    private static final Shape knightShape = new PolygonShape(-1.54f,0.93f, -0.09f,0.93f, -0.09f,-0.82f, -1.54f,-0.74f
    );

    private static final BodyImage blueKnightRight = new BodyImage("data/BlueKnightRight.png", 12f);
    private static final BodyImage blueKnightLeft = new BodyImage("data/BlueKnightLeft.png", 12f);
    private static final BodyImage blueKnightAttackRight = new BodyImage("data/BlueKnightAttackRight.png", 12f);
    private static final BodyImage blueKnightAttackLeft = new BodyImage("data/BlueKnightAttackLeft.png", 12f);

    private boolean rightFacing;
    private float move, rightBorder, leftBorder;

    public BlueKnight(World world, float x, float y, boolean rightFacing) {
        super(world, knightShape, 2);
        this.rightFacing = rightFacing;

        this.rightBorder = -2.5f;
        this.leftBorder = -7.0f;
        this.move = 1.3f;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
        this.setName("BlueKnight");

        BlueKnightHit blueKnightHit = new BlueKnightHit(this);
        this.addCollisionListener(blueKnightHit);
    }

    public void switchDirection() {
        this.rightFacing = !this.rightFacing;
    }

    public void setImage() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(blueKnightRight);
            this.startWalking(move);
        } else {
            this.addImage(blueKnightLeft);
            this.startWalking(-move);
        }
    }

    public void attack() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(blueKnightAttackRight);
        } else {
            this.addImage(blueKnightAttackLeft);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if ((this.getPosition().x > rightBorder)) {
            this.removeAllImages();
            this.switchDirection();
            this.setImage();
        }
        if (this.getPosition().x < leftBorder) {
            this.removeAllImages();
            this.switchDirection();
            this.setImage();
        }

        if (this.getLives() == 0) {
            this.destroy();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    public class BlueKnightHit implements CollisionListener {

        private BlueKnight blueKnight;

        public BlueKnightHit(BlueKnight blueKnight) {
            this.blueKnight = blueKnight;
        }


        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody().getName() == "Stone") {
                this.blueKnight.decrementLives(1f);
            }
        }
    }
}
