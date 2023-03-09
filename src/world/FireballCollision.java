package world;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class FireballCollision implements CollisionListener {

    private Slingshot slingshotBoy;

    public FireballCollision(Slingshot slingshotBoy) {
        this.slingshotBoy = slingshotBoy;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody().getName() == "Fireball") {
            this.slingshotBoy.decrementLives(0.5f);
            collisionEvent.getOtherBody().destroy();
        }
    }
}
