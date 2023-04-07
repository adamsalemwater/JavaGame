package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fireball extends DynamicBody {

    private Dragon dragon;
    private World world;
    private static final Shape fireballShape = new PolygonShape(0.572f,0.032f, -0.059f,-0.135f, -0.482f,0.042f, -0.028f,0.184f
    );
    private static final BodyImage fireballRight = new BodyImage("data/FireballRight.png", 0.75f);
    private static final BodyImage fireballLeft = new BodyImage("data/FireballLeft.png", 0.75f);


    public Fireball(World w, Dragon dragon) {
        super(w, fireballShape);
        this.dragon = dragon;
        this.world = w;

        CollisionListener fireballHit = new FireballCollision();
        this.addCollisionListener(fireballHit);

        if (this.dragon.getRightFacing()) {
            this.setPosition(new Vec2(dragon.getPosition().x + 1, dragon.getPosition().y));
            this.addImage(fireballRight);
            this.setLinearVelocity(new Vec2(4, 1));
        } else {
            this.setPosition(new Vec2(dragon.getPosition().x - 5, dragon.getPosition().y));
            this.addImage(fireballLeft);
            this.setLinearVelocity(new Vec2(-4, 1));

        }

    }

}
