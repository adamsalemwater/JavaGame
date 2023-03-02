package world;

import city.cs.engine.*;

public class Enemy extends Walker {

    private BodyImage enemyImage;
    private Shape enemyShape;

    public Enemy(World world, Shape enemyShape, BodyImage enemyImage) {
        super(world, enemyShape);
        this.enemyImage = enemyImage;
        this.enemyShape = enemyShape;
        addImage(enemyImage);
    }

    public BodyImage getEnemyImage() {
        return enemyImage;
    }

    public Shape getEnemyShape() {
        return enemyShape;
    }
}
