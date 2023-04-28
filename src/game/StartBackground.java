package game;

import city.cs.engine.BodyImage;

import javax.swing.*;
import java.awt.*;

public class StartBackground extends JPanel {

    private Image menuImage;

    public StartBackground() {
        menuImage = new ImageIcon("data/MenuBackground.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(menuImage, 0, 0, 500, 500, null);
    }
}
