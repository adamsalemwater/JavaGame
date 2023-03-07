package world;

import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Enemy extends Walker {

    private float lives;
    public Enemy(World world, Shape shape, float lives) {
        super(world, shape);
        this.lives = lives;

    }

    public float getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void decrementLives(float decrement) {
        this.lives -= decrement;
    }
}
