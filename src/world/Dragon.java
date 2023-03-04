package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dragon extends Walker implements ActionListener, StepListener {

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


    public Dragon(World world, float x, float y, boolean rightFacing) {
        super(world, dragonShape);

        this.rightFacing = rightFacing;
        this.leftBorder = -3.5f;
        this.rightBorder = 6;
        this.move = 1.5f;

        this.setPosition(new Vec2(x, y));
        world.addStepListener(this);
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

   public void dragonAttack() {
        this.removeAllImages();
        if (this.rightFacing) {
            this.addImage(dragonAttackRight);
        } else {
            this.addImage(dragonAttackLeft);
        }
   }

    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
