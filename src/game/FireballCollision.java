package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class FireballCollision implements CollisionListener {


    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Slingshot) {
             ((Slingshot) collisionEvent.getOtherBody()).decrementLives(0.5f);
        }
            collisionEvent.getReportingBody().destroy();
    }
}
