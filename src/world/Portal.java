package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Portal extends StaticBody{

    private static final Shape portalShape = new PolygonShape(-0.12f,0.268f, 0.12f,0.272f, 0.132f,-0.456f, -0.152f,-0.444f
    );
    private BodyImage portalImage;
    private boolean isRed;
    private Portal otherPortal;

    public Portal(World world, float x, float y, boolean isRed) {

        super(world, portalShape);
        this.isRed = isRed;

        if (isRed) {
            portalImage = new BodyImage("data/RedPortal.png", 2);
        } else {
            portalImage = new BodyImage("data/BluePortal.png", 6);
        }

        this.setPosition(new Vec2(x, y));
        this.addImage(portalImage);
    }

    public Portal(World world, float x, float y, boolean isRed, Portal otherPortal) {

        super(world, portalShape);

        this.isRed = isRed;
        this.otherPortal = otherPortal;

        if (isRed) {
            portalImage = new BodyImage("data/RedPortal.png", 2);
        } else {
            portalImage = new BodyImage("data/BluePortal.png", 6);
        }

        this.setPosition(new Vec2(x, y));
        this.addImage(portalImage);
    }



    public boolean isRed() {
        return isRed;
    }

    public Portal getOtherPortal() {
        return otherPortal;
    }

    }

