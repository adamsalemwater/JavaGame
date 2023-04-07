package world;

import city.cs.engine.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SlingController implements KeyListener, ActionListener {

    private Slingshot slingshotBoy;
    private World world;

    private Sound sound;

    public SlingController(World world, Slingshot slingshotBoy) {
        this.slingshotBoy = slingshotBoy;
        this.world = world;
        this.sound = new Sound();
    }

    // override methods for the KeyListener interface
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (slingshotBoy.getLives() > 0) {
            if (code == KeyEvent.VK_LEFT) {
                if (slingshotBoy.isRightFacing()) {
                    slingshotBoy.removeAllImages();
                    slingshotBoy.switchRightFacing();
                    slingshotBoy.setImage();
                }
                slingshotBoy.startWalking(-4);
            }
            if (code == KeyEvent.VK_RIGHT) {
                if (!slingshotBoy.isRightFacing()) {
                    slingshotBoy.removeAllImages();
                    slingshotBoy.switchRightFacing();
                    slingshotBoy.setImage();
                }
                slingshotBoy.startWalking(4);
            }
            if (code == KeyEvent.VK_UP) {
                slingshotBoy.jump(7);
                this.sound.setFile("Jump");
                this.sound.play();
            }

            // when the space bar key is pressed, remove the image and add the shooting image followed by a return to the original image using a timer handler

            if (code  == KeyEvent.VK_SPACE) {
                slingshotBoy.shootingImage();
                slingshotBoy.shoot();
                this.sound.setFile("Throw");
                this.sound.play();
                Timer timer = new Timer(500, this);
                timer.setRepeats(false);
                timer.start();
            }
        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        slingshotBoy.stopWalking();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slingshotBoy.removeAllImages();
        slingshotBoy.setImage();
    }
}
