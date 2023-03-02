package world;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

public class Dragon extends Enemy{

    private static final Shape dragonShape = new BoxShape(3f,2f);
    private static final BodyImage dragonImage = new BodyImage("data/DragonLeft.png", 30f);


    public Dragon(World world) {
        super(world, dragonShape, dragonImage);
    }

    public void changeDirection() {}
}
