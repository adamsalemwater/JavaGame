package game;

import city.cs.engine.World;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLevel extends World {

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int level;

    public GameLevel(int level) {
        this.level = level;
    }

    public void gameEnded() {
        if (this.enemies.size() == 0) {
            level++;
        }
    }

    public void switchLevel() {
        System.out.println("Switching levels to 2");
    }

    public void addEnemies(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public int getLevel() {
        return this.level;
    }
}
