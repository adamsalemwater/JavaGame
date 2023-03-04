package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Knight extends Walker {

    private static Shape knightShape = new PolygonShape(-0.13f,1.69f, -1.02f,1.69f, -1.06f,0.41f, -0.13f,0.37f
    );
    private static final BodyImage blueKnightRight = new BodyImage("data/BlueKnightRight.png", 9f);
    private static final BodyImage blueKnightLeft = new BodyImage("data/BlueKnightLeft.png", 9f);
    private static final BodyImage redKnightLeft = new BodyImage("data/RedKnightLeft.png", 9f);
    private static final BodyImage redKnightRight = new BodyImage("data/RedKnightRight.png", 9f);
    private static final BodyImage redKnightAttackRight = new BodyImage("data/RedKnightAttackRight.png", 9f);
    private static final BodyImage redKnightAttackLeft = new BodyImage("data/RedKnightAttackLeft.png", 9f);
    private static final BodyImage blueKnightAttackRight = new BodyImage("data/BlueKnightAttackRight.png", 9f);
    private static final BodyImage blueKnightAttackLeft = new BodyImage("data/BlueKnightAttackLeft.png", 9f);


    boolean rightFacing;
    boolean red;


    public Knight(World world, float x, float y, boolean rightFacing, boolean red) {
            super(world, knightShape);
            this.rightFacing = rightFacing;
            this.red = red;
            this.setPosition(new Vec2(x, y));
    }

    public void switchColours() {
        this.red = !this.red;
    }

    public void switchDirection() {
        this.rightFacing = !this.rightFacing;
    }

    public void setImage() {
        if (this.rightFacing && this.red) {
            this.addImage(redKnightRight);
        } else if (!this.rightFacing && this.red) {
            this.addImage(redKnightLeft);
        } else if (this.rightFacing && !this.red) {
            this.addImage(blueKnightRight);
        } else {
            this.addImage(blueKnightLeft);
        }
    }

    public void attack() {
        this.removeAllImages();
        if (this.rightFacing && this.red) {
            this.addImage(redKnightAttackRight);
        } else if (!this.rightFacing && this.red) {
            this.addImage(redKnightAttackLeft);
        } else if (this.rightFacing && !this.red) {
            this.addImage(blueKnightAttackRight);
        } else {
            this.addImage(blueKnightAttackLeft);
        }
    }
}
