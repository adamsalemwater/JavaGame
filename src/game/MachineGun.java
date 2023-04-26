package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class MachineGun extends Enemy implements StepListener {

    private static final Shape machineGunShape = new PolygonShape(-1.599f,0.389f, 1.253f,0.45f, 1.233f,-0.426f, -1.579f,-0.447f);
    private static final BodyImage machineGunRightImage = new BodyImage("data/MachineGunRight.png", 2);
    private static final BodyImage machineGunLeftImage = new BodyImage("data/MachineGunLeft.png", 2);
    private boolean rightFacing;
    private float x,y;
    private Slingshot slingshotBoy;
    private float angle;
    private World world;
    private int delay;
    private State state;


    private enum State {
        CLOSE, FAR
    }

    public MachineGun(World world, float x, float y, boolean rightFacing, Slingshot slingshotBoy) {
        super(world, machineGunShape, 5);
        this.x = x;
        this.y = y;
        this.rightFacing = rightFacing;
        this.slingshotBoy = slingshotBoy;
        this.setGravityScale(0);
        this.world = world;
        this.world.addStepListener(this);
        this.delay = 2000;
        state = State.FAR;
    }

    public boolean closeBy() {
        if (Point2D.distance(slingshotBoy.getPosition().x, slingshotBoy.getPosition().y, this.x, this.y) < 40) {
            return true;
        }
        return false;
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
        angle = (float) Math.acos(Math.abs((this.slingshotBoy.getPosition().x - this.x)) / distance);
        this.removeAllImages();


        if (this.slingshotBoy.getPosition().x > this.x && this.rightFacing) {
            this.setAngle(-angle);
        } else if (this.slingshotBoy.getPosition().x < this.x && !this.rightFacing){
            this.setAngle((float) (2*Math.PI - angle));
        }
        this.setImage();
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }

    public class Laser extends DynamicBody implements CollisionListener {

        private static final Shape bulletShape = new PolygonShape(-0.016f,0.068f, -0.151f,0.064f, -0.151f,0.027f, -0.02f,0.023f);
        private static final BodyImage rightLaserImage = new BodyImage("data/LaserRight.png",1);
        private static final BodyImage leftLaserImage = new BodyImage("data/LaserLeft.png", 1);

        private static final BodyImage laserImage = new BodyImage("data/Laser.png", 4);
        private static final Shape laserShape = new PolygonShape(-0.83f,0.37f, -0.85f,0.09f, 0.71f,0.07f, 0.68f,0.41f
        );
        private float x, y;

        public Laser(World world, MachineGun machineGun, Slingshot slingshotBoy) {
//            super(world, bulletShape);
            super(world, laserShape);
            this.x = machineGun.getPosition().x;
            this.y = machineGun.getPosition().y;

            this.addCollisionListener(this);

            this.setGravityScale(0);
            double scaleSpeed = 4;

            if (slingshotBoy.getPosition().x > this.x && slingshotBoy.getPosition().y > machineGun.getPosition().y) {
                this.setPosition(new Vec2((float) (this.x + 3*Math.cos(angle)), (float) (this.y + 3*Math.sin(angle))));
                this.setAngle(angle);
                this.setLinearVelocity(new Vec2((float) ((float) scaleSpeed*Math.cos(angle)), (float) ((float) scaleSpeed*Math.sin(angle))));
            } else if (slingshotBoy.getPosition().x > this.x && slingshotBoy.getPosition().y < machineGun.getPosition().y) {
                this.setPosition(new Vec2((float) (this.x + 3*Math.cos(angle)), (float) (this.y + 3*Math.sin(-angle))));
                this.setAngle(-angle);
                this.setLinearVelocity(new Vec2((float) ((float) scaleSpeed*Math.cos(angle)), (float) ((float) scaleSpeed*Math.sin(-angle))));
            } else if (slingshotBoy.getPosition().x < this.x && slingshotBoy.getPosition().y > this.y) {
                    this.setPosition(new Vec2((float) (-this.x - 3*Math.cos(Math.PI - angle)), (float) (this.y + 3*Math.sin(Math.PI - angle))));
                    this.setAngle((float) (Math.PI - angle));
                    this.setLinearVelocity(new Vec2((float) -((float)  scaleSpeed*Math.cos(angle)), (float) ((float) scaleSpeed*Math.sin(angle))));
                } else if (slingshotBoy.getPosition().x < this.x && slingshotBoy.getPosition().y < this.y) {
                this.setPosition(new Vec2((float) (-this.x - 3*Math.cos(Math.PI - angle)), (float) (-this.y - 3*Math.sin(Math.PI - angle))));
                this.setAngle((float) (-(Math.PI - angle)));
                this.setLinearVelocity(new Vec2((float) ((float) scaleSpeed*Math.cos(angle)), (float) ((float)scaleSpeed*Math.sin(-angle))));
            }


//           if (machineGun.rightFacing) {
//               this.addImage(rightLaserImage);
//           } else {
//               this.addImage(leftLaserImage);
//           }
            this.addImage(laserImage);
        }

        @Override
        public void collide(CollisionEvent collisionEvent) {
            collisionEvent.getReportingBody().destroy();
        }


    }
}
