package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.geom.Point2D;

public class MachineGun extends DynamicBody implements StepListener {

    private static final Shape machineGunShape = new PolygonShape(-1.599f,0.389f, 1.253f,0.45f, 1.233f,-0.426f, -1.579f,-0.447f);
    private static final BodyImage machineGunRightImage = new BodyImage("data/MachineGunRight.png", 2);
    private static final BodyImage machineGunLeftImage = new BodyImage("data/MachineGunLeft.png", 2);
    private boolean rightFacing;
    private float x,y;
    private Slingshot slingshotBoy;

    public MachineGun(World world, float x, float y, boolean rightFacing, Slingshot slingshotBoy) {
        super(world, machineGunShape);
        this.x = x;
        this.y = y;
        this.rightFacing = rightFacing;
        this.slingshotBoy = slingshotBoy;
        this.setGravityScale(0);
        world.addStepListener(this);
    }

    public void setImage() {
        this.setPosition(new Vec2(x, y));
        if (this.rightFacing) {
            this.addImage(machineGunRightImage);
        } else {
            this.addImage(machineGunLeftImage);
        }
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        float distance = (float) Point2D.distance(this.slingshotBoy.getPosition().x, this.slingshotBoy.getPosition().y, this.x, this.y);
        float angle = (float) Math.acos((this.slingshotBoy.getPosition().x - this.x) / distance);
        this.removeAllImages();


        if (this.slingshotBoy.getPosition().x > this.x) {
            this.setAngle((float) - angle);
        } else {
            this.setAngle((float) (2*Math.PI - angle));
        }
        this.setImage();
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
