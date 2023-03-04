package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class BlueKnight extends Walker implements StepListener {

    private static final Shape knightShape = new PolygonShape(-1.54f,0.93f, -0.09f,0.93f, -0.09f,-0.82f, -1.54f,-0.74f
    );

    private static final BodyImage blueKnightRight = new BodyImage("data/BlueKnightRight.png", 12f);
    private static final BodyImage blueKnightLeft = new BodyImage("data/BlueKnightLeft.png", 12f);
    private static final BodyImage blueKnightAttackRight = new BodyImage("data/BlueKnightAttackRight.png", 12f);
    private static final BodyImage blueKnightAttackLeft = new BodyImage("data/BlueKnightAttackLeft.png", 12f);

    private boolean rightFacing;
    private float move, rightBorder, leftBorder;

    public BlueKnight(World world, float x, float y, boolean rightFacing) {
        super(world, knightShape);
        this.rightFacing = rightFacing;

        this.rightBorder = -2.5f;
        this.leftBorder = -7.0f;
        this.move = 1.3f;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
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
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
