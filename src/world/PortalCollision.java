package world;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class PortalCollision implements CollisionListener {


    private Portal portal;

    public PortalCollision(Portal portal) {
        this.portal = portal;
    }


    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Portal) {
            collisionEvent.getReportingBody().setPosition(this.portal.getOtherPortal().getPosition());
        }
    }
}
