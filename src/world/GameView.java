package world;

import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    private Image background;
    private Slingshot slingshotBoy;

    public GameView(World world, int width, int height, Slingshot slingshotBoy) {
        super(world, width, height);
        background = new ImageIcon("data/Background.png").getImage();
        this.slingshotBoy = slingshotBoy;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.drawString(Integer.toString(slingshotBoy.getScore()), 0, 0);
    }
}
