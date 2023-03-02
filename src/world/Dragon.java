package world;

import city.cs.engine.*;

public class Dragon extends Enemy{

    private static final Shape dragonShape = new PolygonShape(-2.7f,1.32f, -4.2f,-0.03f, -3.68f,-1.53f, -2.03f,-1.53f, -0.9f,-0.33f
    );
    private static final BodyImage dragonImage = new BodyImage("data/DragonLeft.png", 25f);


    public Dragon(World world) {
        super(world, dragonShape, dragonImage);
    }

    public void changeDirection() {}
}
