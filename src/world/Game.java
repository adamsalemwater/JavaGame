package world;

import city.cs.engine.*;

import javax.swing.JFrame;

/**
 * Your main game entry point
 */
public class Game {


    /** Initialise a new Game. */
    public Game() {


        GameWorld world = new GameWorld();


       Slingshot slingshotBoy = world.getSlingshotBoy();





//        student.setLinearVelocity(new Vec2(0, 4));







        //3. make a view to look into the game world
//        UserView view = new UserView(game.getWorld(), 500, 500);
        GameView view = new GameView(world.getWorld(), 500, 500);






        //optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("City Game");
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
         JFrame debugView = new DebugViewer(world.getWorld(), 500, 500);

        // start our game world simulation!
//        world.start();



        SlingController slingshotStudentController = new SlingController(slingshotBoy, world);

        view.addKeyListener(slingshotStudentController);

       view.requestFocus();

        view.requestFocus();
        
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
