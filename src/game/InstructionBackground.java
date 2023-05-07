package game;


import javax.swing.*;
import java.awt.*;

public class InstructionBackground extends JPanel {

    private Image instructionImage;

    public InstructionBackground() {

        instructionImage = new ImageIcon("data/Instructions.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(instructionImage, 0, 0, 500, 500, null);
    }
}
