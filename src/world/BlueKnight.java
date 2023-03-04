package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class BlueKnight extends Walker {

    private static final Shape knightShape = new PolygonShape(-1.54f,0.93f, -0.09f,0.93f, -0.09f,-0.82f, -1.54f,-0.74f
    );

    private static final BodyImage blueKnightRight = new BodyImage("data/BlueKnightRight.png", 12f);
    private static final BodyImage blueKnightLeft = new BodyImage("data/BlueKnightLeft.png", 12f);
    private static final BodyImage blueKnightAttackRight = new BodyImage("data/BlueKnightAttackRight.png", 12f);
    private static final BodyImage blueKnightAttackLeft = new BodyImage("data/BlueKnightAttackLeft.png", 12f);

    boolean rightFacing;

    public BlueKnight(World world, float x, float y, boolean rightFacing) {
        super(world, knightShape);
        this.rightFacing = rightFacing;
        this.setPosition(new Vec2(x, y));
    }

    public void switchDirection() {
        this.rightFacing = !this.rightFacing;
    }

    public void setImage() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(blueKnightRight);
        } else {
            this.addImage(blueKnightLeft);
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
}
