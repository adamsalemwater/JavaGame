package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Slingshot extends Walker {

    private static final Shape slingshotBody = new PolygonShape(-0.26f,0.83f, 0.43f,-0.03f, 0.03f,-0.64f, -0.64f,-0.61f, -0.74f,0.05f
    );
    private static final String rightFacingURL = "data/SlingshotRight.png";
    private static final String leftFacingURL = "data/SlingshotLeft.png";

    private BodyImage slingshotImage;
    private boolean rightFacing;
    private float x, y;


    public Slingshot(World w, float x, float y, boolean rightFacing) {
        super(w, slingshotBody);
        this.rightFacing = rightFacing;
        this.x = x;
        this.y = y;

        this.setPosition(new Vec2(x, y));
    }

    public boolean isRightFacing() {
        return rightFacing;
    }

    public void switchRightFacing() {
        this.rightFacing = !this.rightFacing;
    }

    public void setImage() {
        if (this.rightFacing) {
            slingshotImage = new BodyImage(rightFacingURL, 4f);
        } else {
            slingshotImage = new BodyImage(leftFacingURL, 4f);
        }
        this.addImage(slingshotImage);
    }

    public void shootingImage() {
//        BodyImage shootingImage = new BodyImage("")
//        this.removeAllImages();
//        this.addImage()
    }

}
