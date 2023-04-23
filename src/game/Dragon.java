package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;



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
    private float leftBorder;
    private float rightBorder;
    private World world;
    private Slingshot slingshotBoy;

    // different states for dragon if hurt or the slingshot character is in range

    private enum State {
            STILL, RIGHT, LEFT
    }

    private State state;



    public Dragon(World world, float x, float y, boolean rightFacing, Slingshot slingshotBoy, float leftBorder, float rightBorder) {
        super(world, dragonShape, 5);

        this.rightFacing = rightFacing;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.move = 1.5f;
        this.slingshotBoy = slingshotBoy;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
        this.setName("Dragon");

        CollisionListener dragonHit = new DragonHit();
        this.addCollisionListener(dragonHit);
        this.world = world;

        this.state = State.STILL;

    }

    public boolean inRangeLeft() {
        if (slingshotBoy.getPosition().x - this.getPosition().x > -2) {
            return true;
        }
        return false;
    }


    public boolean inRangeRight() {
        if (slingshotBoy.getPosition().x - this.getPosition().x < 2) {
            return true;
        }
        return false;
    }

    public void setRightFacing(boolean rightFacing) {
        this.rightFacing = rightFacing;
    }

    @Override
    public void jump(float x) {
        super.jump(x);
        this.removeAllImages();
        this.attackImage();
    }


    public void setLeftBorder(float x) {
        this.leftBorder = x;
    }

    public void setRightBorder(float x) {
        this.rightBorder = x;
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

   public void walk() {
        switch(state) {
            case LEFT :
                this.setRightFacing(false);
                this.removeAllImages();
                this.setImage();
                this.startWalking(-move);
                break;
            case RIGHT :
                this.setRightFacing(true);
                this.removeAllImages();
                this.setImage();
                this.startWalking(move);
                break;
            default:
        }
   }

    @Override
    public void preStep(StepEvent stepEvent) {
//        if (inRangeLeft()) {
//            if (state != State.LEFT) {
//                this.removeAllImages();
//                this.switchImage();
//                this.setImage();
//                this.startWalking(-move);
//                state = State.LEFT;
//            }
//        } else if (inRangeRight()) {
//            if (state != State.RIGHT) {
//                this.removeAllImages();
//                this.switchImage();
//                this.setImage();
//                this.startWalking(move);
//                state = State.RIGHT;
//            }
//        } else {
//            if (state != State.STILL) {
//                this.stopWalking();
//            }
//        }
//        walk();
//        if ((this.getPosition().x > rightBorder)) {
//            this.removeAllImages();
//            this.switchImage();
//            this.setImage();
//        }
//        if (this.getPosition().x < leftBorder) {
//            this.removeAllImages();
//            this.switchImage();
//            this.setImage();
//        }

        if (this.getLives() <= 0) {
            this.destroy();
            this.removeEnemies(this);
        }


    }

    @Override
    public void postStep(StepEvent stepEvent) {
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
            if (collisionEvent.getOtherBody() instanceof StaticBody) {
                collisionEvent.getReportingBody().removeAllImages();
                ((Dragon)collisionEvent.getReportingBody()).setImage();
            }
        }
    }

}

