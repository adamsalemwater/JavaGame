package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Tree extends StaticBody {

    private static final Shape treeShape = new PolygonShape(-0.48f,1.31f, 0.74f,1.28f, 0.36f,-1.11f, -0.35f,-1.11f);
    private static final BodyImage treeImage = new BodyImage("data/Tree.png", 3);

    public Tree(World world, float x, float y) {
        super(world, treeShape);
        this.setPosition(new Vec2(x, y));
        this.addImage(treeImage);
    }
}
