package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.geom.Point2D;


public class Dragon extends Enemy implements StepListener {

    // instance fields for creating dragon

    private static final Shape dragonShape = new PolygonShape(-0.23f,-1.46f, -0.23f,1.24f, -3.9f,1.17f, -3.9f,-1.46f
    );
    private static final BodyImage dragonImageLeft = new BodyImage("data/DragonLeft.png", 25f);
    private static final BodyImage dragonImageRight = new BodyImage("data/DragonRight.png", 25f);
    private static final BodyImage dragonAttackRight = new BodyImage("data/DragonAttackRight.png", 25f);
    private static final BodyImage dragonAttackLeft = new BodyImage("data/DragonAttackLeft.png", 25f);
    private boolean rightFacing;

    // instance field for moving the dragon on the platform

    private float move;
    private World world;
    private Slingshot slingshotBoy;
    private float rightBorder, leftBorder;

    private float fullLife;




    public Dragon(World world, float x, float y, boolean rightFacing, Slingshot slingshotBoy, float rightBorder, float leftBorder) {
        super(world, dragonShape, 5);

        this.rightFacing = rightFacing;
        this.move = 1.5f;
        this.slingshotBoy = slingshotBoy;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
        this.setName("Dragon");

        CollisionListener dragonHit = new DragonHit();
        this.addCollisionListener(dragonHit);
        this.world = world;

        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.fullLife = 5;

    }


    public void setRightFacing(boolean rightFacing) {
        this.rightFacing = rightFacing;
    }

    public float getFullLife() {
        return fullLife;
    }

    @Override
    public void jump(float x) {
        super.jump(x);
        this.removeAllImages();
        this.attackImage();
    }



   public void setImage() {
        if (this.rightFacing) {
            this.addImage(dragonImageRight);
            this.startWalking(move);
        } else {
            this.addImage(dragonImageLeft);
            this.startWalking(-move);
        }
   }

    public void setMove(float move) {
        this.move = move;
   }

   public void switchImage() {
        this.rightFacing = !rightFacing;
   }

   public boolean getRightFacing() {
        return this.rightFacing;
   }

   public void attackImage() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(dragonAttackRight);
        } else {
            this.addImage(dragonAttackLeft);
        }
   }


    @Override
    public void preStep(StepEvent stepEvent) {
        if (this.getPosition().x > rightBorder) {
            this.removeAllImages();
            this.switchImage();
            this.setImage();
        } else if (this.getPosition().x < leftBorder){
            this.removeAllImages();
            this.switchImage();
            this.setImage();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (this.getLives() <= 0) {
            this.destroy();
        }
        }

    public class DragonHit implements CollisionListener{



        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody() instanceof Tree) {
                ((Dragon)collisionEvent.getReportingBody()).removeAllImages();
                ((Dragon)collisionEvent.getReportingBody()).switchImage();
                ((Dragon)collisionEvent.getReportingBody()).setImage();
            }
            if (collisionEvent.getOtherBody().getName() == "Stone") {
                ((Dragon)collisionEvent.getReportingBody()).decrementLives(0.5f);
            }
        }
    }

}

