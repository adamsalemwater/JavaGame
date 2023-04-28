package game;

import javax.swing.*;
import java.awt.*;

public class GameOverBackground extends JPanel {

    private boolean won;
    private Image backgroundWon;
    private Image backgroundLost;

    public GameOverBackground() {
        backgroundWon = new ImageIcon("data/Background.png").getImage();
        backgroundLost = new ImageIcon("data/VolcanoBackground.png").getImage();
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (won) {
            g.drawImage(backgroundWon, 0, 0, 500, 500, null);
        } else {
            g.drawImage(backgroundLost, 0, 0, 500, 500, null);
        }
    }
}
