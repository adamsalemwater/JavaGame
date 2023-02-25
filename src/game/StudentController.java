package game;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StudentController implements KeyListener {
    private Student student;

    public StudentController(Student student) {
        this.student = student;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT) {
            student.startWalking(-4);
        } else if (code == KeyEvent.VK_RIGHT) {
            student.startWalking(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
