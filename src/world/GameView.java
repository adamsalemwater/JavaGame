package world;

import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    private Image background;
    private Image scoreBoard;
    private Slingshot slingshotBoy;

    private Image heart;

    public GameView(World world, int width, int height, Slingshot slingshotBoy) {
        super(world, width, height);
        background = new ImageIcon("data/Background.png").getImage();
        scoreBoard = new ImageIcon("data/Score.png").getImage();
        this.slingshotBoy = slingshotBoy;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.yellow);
        g.drawImage(scoreBoard, 0, 0,100, 100, this);
        g.drawString(Integer.toString(slingshotBoy.getScore()), 65, 55);
    }
}
