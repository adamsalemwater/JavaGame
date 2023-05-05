package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.io.IOException;

public class Door extends StaticBody implements CollisionListener {

    private static final Shape doorShape = new PolygonShape(-0.611f,0.82f, 0.581f,0.832f, 0.585f,-0.88f, -0.627f,-0.872f);
    private static final BodyImage doorImage = new BodyImage("data/Door.png", 2);
    private World world;
    private Game game;
    public Door(World world, float x, float y, Game game) {
        super(world, doorShape);
        this.world = world;
        this.setPosition(new Vec2(x,y));
        this.addImage(doorImage);
        this.game = game;
        this.addCollisionListener(this);
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {

        if (collisionEvent.getOtherBody() instanceof Slingshot) {
            if (world instanceof Level1) {
                try {
                    game.switchLevelTwo();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (world instanceof Level2) {
                try {
                    game.switchLevelThree();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (world instanceof Level3) {
                try {
                    game.gameSuccess();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }



        }
        }
    }

