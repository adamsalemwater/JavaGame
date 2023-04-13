package game;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import GameOver.*;
import city.cs.engine.DebugViewer;

import java.awt.*;

import java.io.File;
import java.io.IOException;


/**
 * Your main game entry point
 */
public class Game {


    private SlingController slingController;

    private Level1 firstLevel;
    private Level2 secondLevel;
    private GameView view;
    private JFrame frame;
    private Slingshot slingshotBoy;


    /**
     * Initialise a new Game.
     */
    public Game() {


        firstLevel = new Level1();


        this.slingshotBoy = firstLevel.getSlingshotBoy();


//        student.setLinearVelocity(new Vec2(0, 4));


        //3. make a view to look into the game world
//        UserView view = new UserView(game.getWorld(), 500, 500);
        this.view = new GameView(firstLevel.getWorld(), 500, 500, slingshotBoy, "Background", this);


        //optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        this.frame = new JFrame("Slingshot Boy");
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

//        optional: uncomment this to make a debugging view
//         JFrame debugView = new DebugViewer(secondLevel, 500, 500);

        slingController = new SlingController(firstLevel, slingshotBoy);

        view.addKeyListener(slingController);

        view.requestFocus();

        Tracker slingshotTracker = new Tracker(view, slingshotBoy);
        firstLevel.addStepListener(slingshotTracker);

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
        firstLevel.start();

        Check checking = new Check(this, this.slingshotBoy);
        firstLevel.addStepListener(checking);


    }

    public void gameEnded() {
        firstLevel.stop();
        frame.remove(view);
        GameOver gameOver = new GameOver();
        gameOver.getPanel1().setSize(new Dimension(500, 500));
        frame.add(gameOver.getPanel1());
        frame.pack();
    }

    public void switchLevelOne() throws IOException {
        HighScoreWriter scoreWriter = new HighScoreWriter("FirstLevel");
        scoreWriter.writeHighScore(firstLevel.getSlingshotBoy().getScore(), firstLevel.getSlingshotBoy().getLives());
        HighScoreReader scoreReader = new HighScoreReader("FirstLevel");
        scoreReader.readHighScore();
        firstLevel.stop();
        secondLevel = new Level2();
        Slingshot secondSlingshotBoy = secondLevel.getSlingshotBoy();
        GameView secondView = new GameView(secondLevel, 700, 700, secondSlingshotBoy, "VolcanoBackground", this);
        frame.remove(view);
        frame.add(secondView);
        frame.pack();
        SlingController secondSlingController = new SlingController(secondLevel, secondSlingshotBoy);
        secondView.addKeyListener(secondSlingController);
        secondView.requestFocus();
        DebugViewer secondLevelDebugViewer = new DebugViewer(secondLevel, 700, 700);
        secondLevel.start();
    }

    public Level1 getFirstLevel() {
        return firstLevel;
    }

    public Level2 getSecondLevel() {
        return secondLevel;
    }

    public SlingController getSlingController() {
        return slingController;
    }


    /**
     * Run the game.
     */
    public static void main(String[] args) {

        new Game();
    }

}
