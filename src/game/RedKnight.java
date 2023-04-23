package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class RedKnight extends Enemy implements StepListener {

    private static Shape knightShape = new PolygonShape(-0.21f,1.65f, -1.66f,1.74f, -1.71f,0.16f, -0.34f,0.16f
    );

    private static final BodyImage redKnightLeft = new BodyImage("data/RedKnightLeft.png", 12f);
    private static final BodyImage redKnightRight = new BodyImage("data/RedKnightRight.png", 12f);
    private static final BodyImage redKnightAttackRight = new BodyImage("data/RedKnightAttackRight.png", 12f);
    private static final BodyImage redKnightAttackLeft = new BodyImage("data/RedKnightAttackLeft.png", 12f);


    private boolean rightFacing;

    private float move, rightBorder, leftBorder;


    public RedKnight(World world, float x, float y, boolean rightFacing) {
        super(world, knightShape, 2);
        this.rightFacing = rightFacing;
        this.leftBorder = 3f;
        this.rightBorder = 7.5f;
        this.move = 1.2f;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
        this.setName("RedKnight");

        RedKnightHit redKnightHit = new RedKnightHit(this);
        this.addCollisionListener(redKnightHit);
    }

    public void setMove(float move) {
        this.move = move;
    }


    public void switchDirection() {
        this.rightFacing = !this.rightFacing;
    }

    public void setImage() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.startWalking(move);
            this.addImage(redKnightRight);
        } else {
            this.startWalking(-move);
            this.addImage(redKnightLeft);
        }
    }

    public void attack() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(redKnightAttackRight);
        } else {
            this.addImage(redKnightAttackLeft);
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
            this.removeEnemies(this);
        }
    }

    public void setRightBorder(float rightBorder) {
        this.rightBorder = rightBorder;
    }

    public void setLeftBorder(float leftBorder) {
        this.leftBorder = leftBorder;
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    public class RedKnightHit implements CollisionListener {

        private RedKnight redKnight;

        public RedKnightHit(RedKnight redKnight) {
            this.redKnight = redKnight;
        }


        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody().getName() == "Stone") {
                this.redKnight.decrementLives(1f);
            }
        }
    }
}