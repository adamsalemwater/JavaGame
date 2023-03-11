package world;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.World;

public class StoneCollision implements CollisionListener {

    private Slingshot slingshotBoy;
    private Sound sound;

    public StoneCollision(Slingshot slingshotBoy) {
        this.slingshotBoy = slingshotBoy;
        this.sound = new Sound();
    }





    @Override
    public void collide(CollisionEvent collisionEvent) {
        this.sound.setFile("Smash");
        this.sound.play();
           if (collisionEvent.getOtherBody() instanceof Dragon) {
               this.slingshotBoy.addScore(20);
           }
           if ((collisionEvent.getOtherBody() instanceof BlueKnight) ||
           collisionEvent.getOtherBody() instanceof RedKnight) {
               this.slingshotBoy.addScore(10);
           }
           collisionEvent.getReportingBody().destroy();
    }
}
