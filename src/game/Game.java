package game;


import javax.swing.JFrame;

import GameOver.*;
import StartMenu.StartMenu;

import java.awt.*;

import java.io.IOException;


/**
 * Your main game entry point
 */
public class Game {


    private SlingController slingController;

    private Level1 firstLevel;
    private Level2 secondLevel;
    private JFrame menuFrame;
    private JFrame gameoverFrame;
    private JFrame firstLevelFrame;
    private JFrame secondLevelFrame;
    private Slingshot slingshotBoy;
    private HighScoreWriter highScoreWriter;
    private HighScoreReader highScoreReader;
    private GameOver gameOver;
    private GameView firstView;
    private GameView secondView;
    private StartMenu startMenu;


    /**
     * Initialise a new Game.
     */
    public Game() throws IOException {

        startMenu = new StartMenu(this);

        //4. create a Java window (frame) and add the game
        //   view to it
        this.menuFrame = new JFrame("Slingshot Adventures");

        // enable the frame to quit the application
        // when the x button is pressed
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationByPlatform(true);
        // don't let the frame be resized
        menuFrame.setResizable(false);
        // resize the frame
        startMenu.getMenuPanel().setPreferredSize(new Dimension(500, 500));
        // size the frame to fit the world view
//        frame.pack();
        // finally, make the frame visible
        menuFrame.setVisible(true);


        menuFrame.add(startMenu.getMenuPanel());
        menuFrame.repaint();
        menuFrame.pack();

//        optional: uncomment this to make a debugging view
//         JFrame debugView = new DebugViewer(secondLevel, 500, 500);






    }

    public void gameEndedOne() {
        firstLevel.stop();
        firstLevelFrame.remove(firstView);
        firstLevelFrame.dispose();
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        gameoverFrame = new JFrame("Slingshot Fail");
        gameoverFrame.add(gameOver.getMainPanel());

        // enable the frame to quit the application
        // when the x button is pressed
        gameoverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameoverFrame.setLocationByPlatform(true);
        // don't let the frame be resized
        gameoverFrame.setResizable(false);

        gameoverFrame.setVisible(true);

        gameoverFrame.repaint();
        gameoverFrame.pack();
    }

    public void gameEndedTwo() {
        secondLevel.stop();
        menuFrame.remove(secondView);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        menuFrame.add(gameOver.getMainPanel());
        menuFrame.repaint();
        menuFrame.pack();
    }

    public void switchStartMenu() {
        startMenu = new StartMenu(this);
        this.menuFrame.remove(gameOver.getMainPanel());
        this.menuFrame.add(startMenu.getMenuPanel());
        menuFrame.repaint();
        menuFrame.pack();
    }


    public void switchLevelTwo() throws IOException {
        highScoreWriter = new HighScoreWriter("level");
        highScoreWriter.writeHighScore(firstLevel.getSlingshotBoy().getScore(), firstLevel.getSlingshotBoy().getLives());
        highScoreReader = new HighScoreReader("level");
        highScoreReader.readHighScore();
        firstLevel.stop();
        secondLevel = new Level2(this);

        // dispose of the first level's frame

        firstLevelFrame.dispose();

        // instantiate the second frame

        secondLevelFrame = new JFrame("Slingshot Adventures");

        // enable the frame to quit the application
        // when the x button is pressed
        secondLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        secondLevelFrame.setLocationByPlatform(true);
        // don't let the frame be resized
        secondLevelFrame.setResizable(false);
        // size the frame to fit the world view
        secondLevelFrame.pack();
        // finally, make the frame visible
        secondLevelFrame.setVisible(true);

        Slingshot secondSlingshotBoy = secondLevel.getSlingshotBoy();
        secondSlingshotBoy.setScore(highScoreReader.getReadScore());
        secondSlingshotBoy.setLives(highScoreReader.getReadLives());
        secondView = new GameView(secondLevel, 700, 700, secondSlingshotBoy, "VolcanoBackground", this);
        firstLevelFrame.remove(firstView);
        secondLevelFrame.add(secondView);
        secondLevelFrame.repaint();
        secondLevelFrame.pack();
        SlingController secondSlingController = new SlingController(secondLevel, secondSlingshotBoy);
        secondView.addKeyListener(secondSlingController);
        secondView.requestFocus();
        secondLevel.start();

        Switch checking = new Switch(this, secondSlingshotBoy);
        secondLevel.addStepListener(checking);
    }


    public void switchLevelOne() throws IOException {
        firstLevel = new Level1(this);

        firstLevelFrame = new JFrame("Slingshot Adeventures");

        // enable the frame to quit the application
        // when the x button is pressed
        firstLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstLevelFrame.setLocationByPlatform(true);
        // don't let the frame be resized
        firstLevelFrame.setResizable(false);
        // size the frame to fit the world view
        firstLevelFrame.pack();
        // finally, make the frame visible
        firstLevelFrame.setVisible(true);

        this.menuFrame.dispose();

        this.slingshotBoy = firstLevel.getSlingshotBoy();
        firstView = new GameView(firstLevel, 700, 700, slingshotBoy, "Background", this);
//        frame.remove(startMenu.getMenuPanel());
        firstLevelFrame.add(firstView);
        firstLevelFrame.repaint();
        firstLevelFrame.pack();
        firstLevelFrame.revalidate();
        SlingController firstSlingController = new SlingController(firstLevel, slingshotBoy);
        firstView.addKeyListener(firstSlingController);
        firstView.requestFocus();
        firstLevel.start();

        Switch checking = new Switch(this, slingshotBoy);
        firstLevel.addStepListener(checking);
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
    public static void main(String[] args) throws IOException {

        new Game();
    }

}
