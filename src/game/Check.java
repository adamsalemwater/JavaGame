package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import java.io.IOException;

public class Check implements StepListener{

    Game game;
    Slingshot slingshotBoy;
    public Check(Game game, Slingshot slingshotBoy) {
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
        if (this.slingshotBoy.getScore() == 40) {
            try {
                this.game.switchLevelOne();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

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
        if (this.slingshotBoy.getPosition().y < -20) {
            this.slingshotBoy.decrementLives(1);
            this.slingshotBoy.setPosition(new Vec2(-10, -2.2f));
        }
    }
}
