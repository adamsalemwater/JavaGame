package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Portal extends StaticBody{

    private static final Shape portalShape = new PolygonShape(-0.12f,0.268f, 0.12f,0.272f, 0.132f,-0.456f, -0.152f,-0.444f
    );
    private BodyImage portalImage;
    private boolean isRed;
    private Vec2 finalDestination;

    public Portal(World world, float x, float y, boolean isRed, Vec2 finalDestination) {

        super(world, portalShape);
        this.isRed = isRed;
        this.finalDestination = finalDestination;

        if (isRed) {
            portalImage = new BodyImage("data/RedPortal.png", 2);
        } else {
            portalImage = new BodyImage("data/BluePortal.png", 6);
        }

        this.setPosition(new Vec2(x, y));
        this.addImage(portalImage);

        CollisionListener portalHit = new PortalCollision(this);
        this.addCollisionListener(portalHit);
    }



    public Vec2 getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(Vec2 finalDestination) {
        this.finalDestination = finalDestination;
    }

    public boolean isRed() {
        return isRed;
    }


    public class PortalCollision implements CollisionListener {

        private Portal portal;

        public PortalCollision(Portal portal) {
            this.portal = portal;
        }

        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody() instanceof Slingshot) {
                collisionEvent.getOtherBody().setPosition(this.portal.getFinalDestination());
            }
        }
    }

    }

