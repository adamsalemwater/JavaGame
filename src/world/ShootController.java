package world;

import city.cs.engine.World;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShootController implements MouseListener {

    private World world;
    private Slingshot slingshotBoy;

    public ShootController(Slingshot slingshotBoy, World world) {
        this.world = world;
        this.slingshotBoy = slingshotBoy;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
