package world;

import city.cs.engine.World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SlingController implements KeyListener {

    private Slingshot slingshotBoy;
    private World world;

    public SlingController(Slingshot slingshotBoy, World world) {
        this.slingshotBoy = slingshotBoy;
        this.world = world;
    }
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
            slingshotBoy.jump(5);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        slingshotBoy.stopWalking();
    }
}
