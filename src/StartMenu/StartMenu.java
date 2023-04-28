package StartMenu;

import game.Game;
import game.StartBackground;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartMenu {
    private JPanel menuPanel;
    private JButton startButton;
    private JButton exitButton;

    public StartBackground getMenuPanel() {
        return (StartBackground) this.menuPanel;
    }

    public StartMenu(Game game) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.switchLevelOne();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


    }

    private void createUIComponents() {
        menuPanel = new StartBackground();
    }
}
