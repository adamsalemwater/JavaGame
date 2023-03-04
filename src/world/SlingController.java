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

    public SlingController(Slingshot slingshotBoy, World world) {
        this.slingshotBoy = slingshotBoy;
        this.world = world;
    }

    // override methods for the KeyListener interface
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        if (code == KeyEvent.VK_A) {
              if (slingshotBoy.isRightFacing()) {
                  slingshotBoy.removeAllImages();
                  slingshotBoy.switchRightFacing();
                  slingshotBoy.setImage();
              }
            slingshotBoy.startWalking(-4);
        }
        if (code == KeyEvent.VK_D) {
            if (!slingshotBoy.isRightFacing()) {
                slingshotBoy.removeAllImages();
                slingshotBoy.switchRightFacing();
                slingshotBoy.setImage();
            }
            slingshotBoy.startWalking(4);
        }
        if (code == KeyEvent.VK_W) {
            slingshotBoy.jump(7);
        }

        // when the space bar key is pressed, remove the image and add the shooting image followed by a return to the original image using a timer handler

        if (code  == KeyEvent.VK_SPACE) {
            slingshotBoy.shootingImage();
            Timer timer = new Timer(100, this);
            timer.setRepeats(false);
            timer.start();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        slingshotBoy.stopWalking();
    }

    // override methods for ActionListener interface

    @Override
    public void actionPerformed(ActionEvent e) {
        slingshotBoy.removeAllImages();
        slingshotBoy.setImage();
    }
}
