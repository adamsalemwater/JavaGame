package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import java.io.IOException;

/**
 * A class which checks if conditions are met to progress to the next level
 */
public class Switch implements StepListener{

    Game game;
    Slingshot slingshotBoy;
    public Switch(Game game, Slingshot slingshotBoy) {
        this.game = game;
        this.slingshotBoy = slingshotBoy;
    }
    @Override
    public void preStep(StepEvent stepEvent) {
        if(this.slingshotBoy.getLives() <= 0) {
            this.game.gameEnded();
        }
//        if (allEnemiesDeadLevelOne() == true) {
//            try {
//                this.game.switchLevelOne();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        if (this.slingshotBoy.getScore() == 0) {
            try {
                this.game.switchLevelOne();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     *
     * @return Returns a boolean expression to check whether all enemies within the first level are dead.
     */
    public boolean allEnemiesDeadLevelOne() {
        if (this.game.getFirstLevel().getDragon().getEnemies().size() == 0
                && this.game.getFirstLevel().getBlueKnight().getEnemies().size() == 0
        && this.game.getFirstLevel().getRedKnight().getEnemies().size() == 0) {
            return true;
        }
        return false;
    }


    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
