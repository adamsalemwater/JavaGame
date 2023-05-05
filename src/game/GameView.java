package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class GameView extends UserView {

    private Image background;
    private Image scoreBoard;
    private Slingshot slingshotBoy;

    private Image heart;
    private Image halfHeart;
    private Image key;
    private Game game;
    private World world;
    private int width, height;
    private Graphics2D g;

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
        this.height = height;
        this.width = width;

    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        this.g = g;

        // draw health bars for each level

        drawHealthBarsLevelOne();
        drawHealthBarsLevelTwo();
        drawHealthBarsLevelThree();








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

    public ImageIcon changeHealthBar(float ratio) {
        ImageIcon image = new ImageIcon("data/HealthBar100.png");
        if (ratio >= 0.9 && ratio <1.0) {
            image = new ImageIcon("data/HealthBar90.png");
        } else if (ratio >= 0.7 && ratio < 0.9) {
            image = new ImageIcon("data/HealthBar70.png");
        } else if (ratio >= 0.5 && ratio < 0.7) {
            image = new ImageIcon("data/HealthBar60.png");
        } else if (ratio >= 0.2 && ratio < 0.5) {
            image = new ImageIcon("data/HealthBar40.png");
        } else if (ratio <= 0.2 && ratio > 0) {
            image = new ImageIcon("data/HealthBar20.png");
        }
        return image;
    }

    public void drawHealthBarsLevelOne() {

        if (world instanceof Level1) {
            for (Enemy enemy : this.game.getFirstLevel().getEnemies()) {
                if (enemy instanceof Dragon) {
                    float ratioLife = this.game.getFirstLevel().getDragon().getLives() / this.game.getFirstLevel().getDragon().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
                if (enemy instanceof RedKnight) {
                    float ratioLife = this.game.getFirstLevel().getRedKnight().getLives() / this.game.getFirstLevel().getRedKnight().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
                if (enemy instanceof BlueKnight) {
                    float ratioLife = this.game.getFirstLevel().getBlueKnight().getLives() / this.game.getFirstLevel().getBlueKnight().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
            }
        }

    }

    public void drawHealthBarsLevelTwo() {

        if (world instanceof Level2) {
            for (Enemy enemy : this.game.getSecondLevel().getEnemies()) {
                if (enemy instanceof Dragon) {
                    float ratioLife = this.game.getSecondLevel().getDragon().getLives() / this.game.getSecondLevel().getDragon().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
                if (enemy instanceof RedKnight) {
                    float ratioLife = this.game.getSecondLevel().getRedKnight().getLives() / this.game.getSecondLevel().getRedKnight().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
                if (enemy instanceof BlueKnight) {
                    float ratioLife = this.game.getSecondLevel().getBlueKnight().getLives() / this.game.getSecondLevel().getBlueKnight().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
                if (enemy instanceof MachineGun) {
                    float ratioLife = this.game.getSecondLevel().getMachineGun().getLives() / this.game.getSecondLevel().getMachineGun().getFullLife();
                    if (ratioLife > 0) {
                        Image healthImage = changeHealthBar(ratioLife).getImage();
                        Point2D.Float enemyCoord =  this.worldToView(enemy.getPosition().add(new Vec2(-2.5f, 4)));
                        g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y,50, 50, this);
                    }
                }
            }
        }
    }

    public void drawHealthBarsLevelThree() {
            if (world instanceof Level3) {
                for (Enemy enemy : this.game.getThirdLevel().getEnemies()) {
                    if (enemy.getName() ==  "Dragon") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getDragon().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "Dragon2") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getDragon2().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "BlueKnight") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getBlueKnight().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "BlueKnight2") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getBlueKnight2().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "RedKnight") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getRedKnight().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "RedKnight2") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getRedKnight2().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "MachineGun") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getMachineGun().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }

                    if (enemy.getName() ==  "MachineGun2") {
                        float ratio = enemy.getLives() / this.game.getThirdLevel().getMachineGun2().getFullLife();
                        if (ratio > 0) {
                            Image healthImage = changeHealthBar(ratio).getImage();
                            Point2D.Float enemyCoord = this.worldToView(enemy.getPosition().add(new Vec2(-2,4)));
                            g.drawImage(healthImage, (int) enemyCoord.x, (int) enemyCoord.y, 50, 50, this);
                        }
                    }
                }
            }
    }

}
