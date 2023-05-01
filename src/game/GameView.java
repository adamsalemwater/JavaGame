package game;

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
    private Image key;
    private Game game;
    private World world;


    public GameView(World world, int width, int height, Slingshot slingshotBoy, String backgroundString, Game game) {
        super(world, width, height);
        background = new ImageIcon("data/" + backgroundString + ".png").getImage();
        scoreBoard = new ImageIcon("data/Score.png").getImage();
        key = new ImageIcon("data/Key.png").getImage();
        this.heart = new ImageIcon("data/Heart.png").getImage();
        this.halfHeart = new ImageIcon("data/HalfHeart.png").getImage();
        this.slingshotBoy = slingshotBoy;
        this.game = game;
        this.world = world;
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


        Font font = new Font("Aerial",Font.BOLD, 20);
        g.setFont(font);
        g.drawImage(key, 550, 0, 50, 50, this);
        if (this.world instanceof Level1) {
            g.drawString(slingshotBoy.getKeys()  +" / " + this.game.getKeysLevelOne(), 600, 30);
        } else if (this.world instanceof Level2) {
            g.drawString(slingshotBoy.getKeys() + " / " + this.game.getKeysLevelTwo(), 600, 30);
        } else {
            g.drawString(slingshotBoy.getKeys() + " / " + this.game.getKeysLevelThree(), 600, 30);
        }

        float slingshotLives = slingshotBoy.getLives();

      if (slingshotLives == 3) {
          for (int i=0; i<3; i++) {
              g.drawImage(this.heart, 30*i+400, 0, this);
          }
      } else if (slingshotLives == 2.5f) {
          for (int i=0; i<2; i++) {
              g.drawImage(this.heart, 30*i+400, 0, this);
          }
          g.drawImage(this.halfHeart, 460, 0, this);
      } else if (slingshotLives == 2) {
          for (int i=0; i<2; i++) {
              g.drawImage(this.heart, 30*i+400, 0, this);
          }
      }  else if (slingshotLives == 1.5f) {
          g.drawImage(this.heart, 400, 0, this);
          g.drawImage(this.halfHeart, 430, 0, this);
      } else if (slingshotLives == 1) {
        g.drawImage(this.heart, 400, 0, this);
      } else if (slingshotLives == 0.5f) {
          g.drawImage(this.halfHeart, 400, 0, this);
      } else if (slingshotLives <= 0){
          this.heart = null;
          this.halfHeart = null;
          g.setColor(Color.red);
      }







    }
}
