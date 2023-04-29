package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;


/**
 * A class which checks if conditions are met to progress to the next level or if the
 * main character had lost all his lives, the frame is repacked to the game over menu.
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
        if (game.getFirstLevel() != null) {
            if(this.slingshotBoy.getLives() <= 0 && game.getFirstLevel().isRunning()) {
                this.game.gameEndedOne();
            }
        }

        if (game.getSecondLevel() != null) {
            if (this.slingshotBoy.getLives() <= 0 && game.getSecondLevel().isRunning()) {
                this.game.gameEndedTwo();
            }
        }

    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
