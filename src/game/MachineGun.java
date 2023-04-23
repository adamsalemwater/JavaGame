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
    private float angle;

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

    public float retrieveAngle() {
        return this.angle;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        float distance = (float) Point2D.distance(this.slingshotBoy.getPosition().x, this.slingshotBoy.getPosition().y, this.x, this.y);
        angle = (float) Math.acos((this.slingshotBoy.getPosition().x - this.x) / distance);
        this.removeAllImages();


        if (this.slingshotBoy.getPosition().x > this.x) {
            this.setAngle(-angle);
        } else {
            this.setAngle((float) (2*Math.PI - angle));
        }
        this.setImage();
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }

    public class Bullet extends DynamicBody implements CollisionListener {

        private static final Shape bulletShape = new PolygonShape(-0.003f,0.014f, -0.11f,0.02f, -0.105f,0.0f, -0.02f,-0.006f);
        private static final BodyImage bulletRightImage = new BodyImage("data/BulletRight.png",1);
        private static final BodyImage bulletLeftImage = new BodyImage("data/BulletLeft.png",1);
        private float x, y;

        public Bullet(World world, MachineGun machineGun, Slingshot slingshotBoy) {
            super(world, bulletShape);
            this.x = machineGun.getPosition().x;
            this.y = machineGun.getPosition().y;

            this.addCollisionListener(this);

            this.setPosition(new Vec2((float) (x + 2*Math.sin(machineGun.retrieveAngle())), (float) (y + 2*Math.sin(machineGun.retrieveAngle()))));

            if (slingshotBoy.getPosition().x > this.x) {
                this.setPosition(new Vec2((float) (this.x + 2*Math.cos(-angle)), (float) (this.y + 2*Math.sin(-angle))));
                this.setAngle(-angle);
                this.setLinearVelocity(new Vec2((float) Math.cos(-angle), (float) Math.cos(-angle)));
            } else {
                this.setPosition(new Vec2((float) (this.x + 2*Math.cos(2*Math.PI - angle)), (float) (this.y + 2*Math.sin(2*Math.PI - angle))));
                this.setAngle((float) (2*Math.PI - angle));
                this.setLinearVelocity(new Vec2((float) Math.cos(2*Math.PI - angle), (float) Math.sin(2*Math.PI - angle)));
            }

            if (machineGun.rightFacing) {
                this.addImage(bulletRightImage);
            } else {
                this.addImage(bulletLeftImage);
            }
        }

        @Override
        public void collide(CollisionEvent collisionEvent) {
            collisionEvent.getReportingBody().destroy();
        }


    }
}
