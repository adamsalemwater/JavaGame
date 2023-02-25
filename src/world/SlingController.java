package world;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SlingController implements KeyListener {

    private Slingshot slingshotBoy;

    public SlingController(Slingshot slingshotBoy) {
        this.slingshotBoy = slingshotBoy;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        if (code == KeyEvent.VK_A) {
            slingshotBoy.startWalking(-4);
            System.out.println("move left");
        }
        if (code == KeyEvent.VK_D) {
            slingshotBoy.startWalking(4);
            System.out.println("move right");
        }
        if (code == KeyEvent.VK_W) {
            slingshotBoy.jump(5);
            slingshotBoy.stopWalking();
            System.out.println("jump");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        slingshotBoy.stopWalking();
    }
}
