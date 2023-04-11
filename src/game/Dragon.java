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
    private static final BodyImage fireballRight = new BodyImage("data/FireballRight.png", 0.75f);
    private static final BodyImage fireballLeft = new BodyImage("data/FireballLeft.png", 0.75f);
    private boolean rightFacing;

    // instance field for moving the dragon on the platform

    private float move;
    private float leftBorder;
    private float rightBorder;
    private World world;
    private Slingshot slingshotBoy;

    private static final Shape fireballShape = new PolygonShape(0.572f,0.032f, -0.059f,-0.135f, -0.482f,0.042f, -0.028f,0.184f
    );
    private DynamicBody fireball;


    public Dragon(World world, float x, float y, boolean rightFacing, Slingshot slingshotBoy) {
        super(world, dragonShape, 5);

        this.rightFacing = rightFacing;
        this.leftBorder = -3.5f;
        this.rightBorder = 5.6f;
        this.move = 1.5f;
        this.slingshotBoy = slingshotBoy;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
        this.setName("Dragon");

        CollisionListener dragonHit = new DragonHit(this);
        this.addCollisionListener(dragonHit);
        this.world = world;


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
        if ((this.getPosition().x > rightBorder)) {
            this.removeAllImages();
            this.switchImage();
            this.setImage();
        }
        if (this.getPosition().x < leftBorder) {
            this.removeAllImages();
            this.switchImage();
            this.setImage();
        }

        if (this.getLives() <= 0) {
            this.destroy();
        }


    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }


    public class DragonHit implements CollisionListener{

        private Dragon dragon;

        public DragonHit(Dragon dragon) {
            this.dragon = dragon;
        }


        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody().getName() == "Stone") {
                    this.dragon.decrementLives(0.5f);
            }
        }
    }

}
