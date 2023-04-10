package game;


import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;


public class Tracker implements StepListener {

    private GameView view;
    private Slingshot slingshotBoy;

    public Tracker(GameView view, Slingshot slingshotBoy) {
        this.view = view;
        this.slingshotBoy = slingshotBoy;
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        view.setCentre(new Vec2(slingshotBoy.getPosition()));
    }
}
