package game;

import city.cs.engine.World;


import java.util.ArrayList;

public class GameLevel extends World {

    private int level;

    public GameLevel(int level) {
        this.level = level;
    }


    public int getLevel() {
        return this.level;
    }



}
