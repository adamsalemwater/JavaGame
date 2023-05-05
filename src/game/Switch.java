package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

import java.io.IOException;


/**
 * A class which checks if conditions are met to progress to the next level or if the
 * main character had lost all his lives, the frame is repacked to the game over menu.
 */
public class Switch implements StepListener{

    Game game;
    Slingshot slingshotBoy;
    private boolean flagOne = false;
    private boolean flagTwo = false;
    private boolean flagThree = false;
    public Switch(Game game, Slingshot slingshotBoy) {
        this.game = game;
        this.slingshotBoy = slingshotBoy;
    }
    @Override
    public void preStep(StepEvent stepEvent) {
        if (game.getFirstLevel() != null) {
            if(this.slingshotBoy.getLives() <= 0 && game.getFirstLevel().isRunning() && !flagOne) {
                try {
                    this.game.gameEndedOne();
                    flagOne = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (game.getSecondLevel() != null) {
            if (this.slingshotBoy.getLives() <= 0 && game.getSecondLevel().isRunning() && !flagTwo) {
                try {
                    this.game.gameEndedTwo();
                    flagTwo = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (game.getThirdLevel() != null) {
            if (this.slingshotBoy.getLives() <= 0 && game.getThirdLevel().isRunning() && !flagThree) {
                try {
                    this.game.gameEndedThree();
                    flagThree = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
