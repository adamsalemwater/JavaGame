package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dragon extends Enemy implements StepListener, ActionListener {

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


    public Dragon(World world, float x, float y, boolean rightFacing) {
        super(world, dragonShape, 5);

        this.rightFacing = rightFacing;
        this.leftBorder = -3.5f;
        this.rightBorder = 5.6f;
        this.move = 1.5f;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
        this.setName("Dragon");

        CollisionListener dragonHit = new DragonHit(this);
        this.addCollisionListener(dragonHit);
        this.world = world;

        Timer timer = new Timer(10000, this);
        timer.start();
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


   public void attackImage() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(dragonAttackRight);
        } else {
            this.addImage(dragonAttackLeft);
        }
   }

   public void fireball() {

        Shape fireballShape = new PolygonShape(0.572f,0.032f, -0.059f,-0.135f, -0.482f,0.042f, -0.028f,0.184f
        );
        DynamicBody fireball = new DynamicBody(world, fireballShape);
        fireball.setName("Fireball");

       if (this.rightFacing) {
           fireball.setPosition(new Vec2(this.getPosition().x + 1, this.getPosition().y - 2));
           fireball.addImage(fireballRight);
           fireball.setLinearVelocity(new Vec2(3, 0));
       } else {
           fireball.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y - 2));
           fireball.addImage(fireballLeft);
           fireball.setLinearVelocity(new Vec2(-3, 0));
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

        if (this.getLives() == 0) {
            this.destroy();
        }


    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.attackImage();
        this.fireball();
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
