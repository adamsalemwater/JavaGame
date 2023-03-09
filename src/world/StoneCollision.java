package world;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class StoneCollision implements CollisionListener {

    private Slingshot slingshotBoy;

    public StoneCollision(Slingshot slingshotBoy) {
        this.slingshotBoy = slingshotBoy;
    }





    @Override
    public void collide(CollisionEvent collisionEvent) {
           if (collisionEvent.getOtherBody() instanceof Dragon) {
               this.slingshotBoy.addScore(5);
           }
           if ((collisionEvent.getOtherBody() instanceof BlueKnight) ||
           collisionEvent.getOtherBody() instanceof RedKnight) {
               this.slingshotBoy.addScore(2);
           }
           collisionEvent.getReportingBody().destroy();
    }
}
