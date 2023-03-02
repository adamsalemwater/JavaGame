package world;

import city.cs.engine.*;

public class Slingshot extends Walker {

    private static final Shape slingshotBody = new BoxShape(0.7f,0.6f);

    private static final BodyImage slingshotImage = new BodyImage("data/SlingshotRight.png", 4f);

    public Slingshot(World w) {
        super(w, slingshotBody);
        addImage(slingshotImage);
    }
}
