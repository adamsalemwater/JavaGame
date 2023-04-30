package game;


import javax.swing.JFrame;

import GameOver.*;
import StartMenu.StartMenu;
import city.cs.engine.DebugViewer;

import java.awt.*;

import java.io.IOException;


/**
 * Your main game entry point
 */
public class Game {



    private Level1 firstLevel;
    private Level2 secondLevel;
    private Level3 thirdLevel;
    private JFrame frame;
    private JFrame gameoverFrame;

    private Slingshot slingshotBoy;
    private HighScoreWriter highScoreWriter;
    private HighScoreReader highScoreReader;
    private GameOver gameOver;
    private GameView firstView;
    private GameView secondView;
    private GameView thirdView;
    private StartMenu startMenu;


    /**
     * Initialise a new Game.
     */
    public Game() throws IOException {

//        startMenu = new StartMenu(this);
        thirdLevel = new Level3(this);

        //4. create a Java window (frame) and add the game
        //   view to it
        this.frame = new JFrame("Slingshot Adventures");

        thirdView = new GameView(thirdLevel, 700, 700, thirdLevel.getSlingshotBoy(), "CastleBackground", this);



        frame.add(thirdView);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // resize the frame
//        startMenu.getMenuPanel().setPreferredSize(new Dimension(500, 500));
        // size the frame to fit the world view
//        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
        frame.revalidate();


//        frame.add(startMenu.getMenuPanel());
        SlingController thirdLevelController = new SlingController(thirdLevel, thirdLevel.getSlingshotBoy());
        thirdView.addKeyListener(thirdLevelController);
        thirdView.requestFocus();



        thirdLevel.start();



//        optional: uncomment this to make a debugging view
//         JFrame debugView = new DebugViewer(secondLevel, 500, 500);






    }

    public void gameEndedOne() {
        firstLevel.stop();
        frame.setVisible(false);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        gameoverFrame = new JFrame("Slingshot Fail");
        frame.remove(firstView);
        frame.add(gameOver.getMainPanel());
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }

    public void gameEndedTwo() {
        secondLevel.stop();
        frame.setVisible(false);
        frame.remove(secondView);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        frame.add(gameOver.getMainPanel());
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }

    public void switchStartMenu() {
        frame.setVisible(false);
        this.frame.remove(gameOver.getMainPanel());
        this.frame.add(startMenu.getMenuPanel());
        this.frame.setVisible(true);
        this.frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    public void switchLevel3() {

        thirdLevel = new Level3(this);

        this.frame.setVisible(false);

//        thirdView = new GameView(thirdLevel, 700, 700, , "CastleBackground", this);
    }


    public void switchLevelTwo() throws IOException {
//        highScoreWriter = new HighScoreWriter("level1");
//        highScoreWriter.writeHighScore(firstLevel.getSlingshotBoy().getScore(), firstLevel.getSlingshotBoy().getLives());
//        highScoreReader = new HighScoreReader("level1");
//        highScoreReader.readHighScore();
        secondLevel = new Level2(this);

        firstLevel.stop();




        this.frame.setVisible(false);

        Slingshot secondLevelSlingshotBoy = secondLevel.getSlingshotBoy();
        secondView = new GameView(secondLevel, 700, 700, secondLevelSlingshotBoy, "VolcanoBackground", this);
        frame.getContentPane().removeAll();



        frame.add(secondView);
        frame.repaint();
        frame.pack();
        frame.revalidate();
        SlingController firstSlingController = new SlingController(secondLevel, secondLevelSlingshotBoy);
        secondView.addKeyListener(firstSlingController);
        secondView.requestFocus();
        secondLevel.start();
        this.frame.setVisible(true);

        Switch checking = new Switch(this, secondLevelSlingshotBoy);
        secondLevel.addStepListener(checking);
    }


    public void switchLevelOne() throws IOException {
        firstLevel = new Level1(this);


        this.frame.setVisible(false);

        this.slingshotBoy = firstLevel.getSlingshotBoy();
        firstView = new GameView(firstLevel, 700, 700, slingshotBoy, "Background", this);
        frame.remove(startMenu.getMenuPanel());
        frame.add(firstView);
        frame.repaint();
        frame.pack();
        frame.revalidate();
        SlingController firstSlingController = new SlingController(firstLevel, slingshotBoy);
        firstView.addKeyListener(firstSlingController);
        firstView.requestFocus();
        firstLevel.start();
        this.frame.setVisible(true);

        Switch checking = new Switch(this, slingshotBoy);
        firstLevel.addStepListener(checking);
    }

    public Level1 getFirstLevel() {
        return firstLevel;
    }

    public Level2 getSecondLevel() {
        return secondLevel;
    }




    /**
     * Run the game.
     */
    public static void main(String[] args) throws IOException {

        new Game();
    }

}
