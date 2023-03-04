package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class RedKnight extends Walker {

    private static Shape knightShape = new PolygonShape(-0.21f,1.65f, -1.66f,1.74f, -1.71f,0.16f, -0.34f,0.16f
    );

    private static final BodyImage redKnightLeft = new BodyImage("data/RedKnightLeft.png", 12f);
    private static final BodyImage redKnightRight = new BodyImage("data/RedKnightRight.png", 12f);
    private static final BodyImage redKnightAttackRight = new BodyImage("data/RedKnightAttackRight.png", 12f);
    private static final BodyImage redKnightAttackLeft = new BodyImage("data/RedKnightAttackLeft.png", 12f);


    boolean rightFacing;


    public RedKnight(World world, float x, float y, boolean rightFacing) {
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
            this.addImage(redKnightRight);
        } else {
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
}