package world;

import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    private Image background;

    public GameView(World world, int width, int height) {
        super(world, width, height);
        background = new ImageIcon("data/Background.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }
}
