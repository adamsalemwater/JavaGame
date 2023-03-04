package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dragon extends Walker implements ActionListener {

    private static final Shape dragonShape = new PolygonShape(-2.7f,1.32f, -4.2f,-0.03f, -3.68f,-1.53f, -2.03f,-1.53f, -0.9f,-0.33f
    );
    private static final BodyImage dragonImageLeft = new BodyImage("data/DragonLeft.png", 25f);
    private static final BodyImage dragonImageRight = new BodyImage("data/DragonRight.png", 25f);
    private boolean rightFacing;


    public Dragon(World world, float x, float y, boolean rightFacing) {
        super(world, dragonShape);
        this.rightFacing = rightFacing;
        this.setPosition(new Vec2(x,y));
    }

   public void setImage() {
        if (this.rightFacing) {
            this.addImage(dragonImageRight);
        } else {
            this.addImage(dragonImageLeft);
        }
   }

   public void switchImage() {
        this.rightFacing = !rightFacing;
   }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
