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
    private Image halfHeart;

    public GameView(World world, int width, int height, Slingshot slingshotBoy) {
        super(world, width, height);
        background = new ImageIcon("data/Background.png").getImage();
        scoreBoard = new ImageIcon("data/Score.png").getImage();
        this.heart = new ImageIcon("data/Heart.png").getImage();
        this.halfHeart = new ImageIcon("data/HalfHeart.png").getImage();
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

        float slingshotLives = slingshotBoy.getLives();

        switch ((int)slingshotLives) {
            case 3:
                for (int i=0; i<3; i++) {
                    g.drawImage(this.heart, 20*i+100, 0, this);
                }
                break;
            case 2:
                for (int i=0; i<2; i++) {
                    g.drawImage(this.heart, 20*i+100, 0, this);
                }
                break;
            case 1:
                g.drawImage(this.heart, 100, 0, this);
            default:
                this.heart = null;
                this.halfHeart = null;
        }

        if (slingshotLives == 2.5f) {
            for (int i=0; i<2; i++) {
                g.drawImage(this.heart, 20*i+100, 0, this);
            }
            g.drawImage(this.halfHeart, 140, 0, this);
        }

        if (slingshotLives == 1.5f) {
            g.drawImage(this.heart, 100, 0, this);
            g.drawImage(this.halfHeart, 120, 0, this);
        }

        if (slingshotLives == 0.5f) {
            g.drawImage(this.halfHeart, 100, 0, this);
        }

    }
}
