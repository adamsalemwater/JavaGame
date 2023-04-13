package game;

import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

import java.util.ArrayList;

public class Enemy extends Walker {

    private float lives;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Enemy(World world, Shape shape, float lives) {
        super(world, shape);
        this.lives = lives;
        addEnemies(this);
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

    public void addEnemies(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void removeEnemies(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
}
