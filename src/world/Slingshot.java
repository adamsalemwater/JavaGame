package world;

import city.cs.engine.*;

public class Slingshot extends Walker {

    private static final Shape slingshotBody = new PolygonShape(-0.26f,0.83f, 0.43f,-0.03f, 0.03f,-0.64f, -0.64f,-0.61f, -0.74f,0.05f
    );

    private static final BodyImage slingshotImage = new BodyImage("data/SlingshotRight.png", 4f);

    public Slingshot(World w) {
        super(w, slingshotBody);
        addImage(slingshotImage);
    }
}
