package world;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Your main game entry point
 */
public class Game {

    private SlingController slingController;


    /** Initialise a new Game. */
    public Game() {


        GameWorld world = new GameWorld();



       Slingshot slingshotBoy = world.getSlingshotBoy();





//        student.setLinearVelocity(new Vec2(0, 4));







        //3. make a view to look into the game world
//        UserView view = new UserView(game.getWorld(), 500, 500);
        GameView view = new GameView(world.getWorld(), 500, 500, slingshotBoy);






        //optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("Slingshot Boy");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
//         JFrame debugView = new DebugViewer(world.getWorld(), 500, 500);

         slingController = new SlingController(world, slingshotBoy);

        view.addKeyListener(slingController);

        view.requestFocus();

        Tracker slingshotTracker = new Tracker(view, slingshotBoy);
        world.addStepListener(slingshotTracker);

        // add background music to the game

        Sound sound = new Sound();
        sound.setFile("Background");
        sound.play();
        sound.loop();


        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/Background.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            System.out.println(clip);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
        }


        // start our game world simulation
        world.start();

        
    }

    public SlingController getSlingController() {
        return slingController;
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
