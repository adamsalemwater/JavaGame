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



    private Level1 firstLevel;
    private Level2 secondLevel;
    private Level3 thirdLevel;
    private JFrame frame;
    private JFrame gameoverFrame;

    private Slingshot slingshotBoy;
    private GameOver gameOver;
    private GameView firstView;
    private GameView secondView;
    private GameView thirdView;
    private StartMenu startMenu;
    private int highScore;
    private HighScoreWriter scoreWriterLevelOne;
    private HighScoreReader scoreReaderLevelOne;
    private HighScoreWriter scoreWriterLevelTwo;
    private HighScoreReader scoreReaderLevelTwo;
    private HighScoreWriter scoreWriterLevelThree;
    private HighScoreReader scoreReaderLevelThree;

    /**
     * Initialise a new Game.
     */
    public Game() throws IOException {

        startMenu = new StartMenu(this);
        thirdLevel = new Level3(this);

        //4. create a Java window (frame) and add the game
        //   view to it
        this.frame = new JFrame("Slingshot Adventures");

        thirdView = new GameView(thirdLevel, 700, 700, thirdLevel.getSlingshotBoy(), "CastleBackground", this);



//        frame.add(thirdView);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // resize the frame
        startMenu.getMenuPanel().setPreferredSize(new Dimension(500, 500));
        // size the frame to fit the world view
//        frame.pack();
        // finally, make the frame visible

        frame.add(thirdView);

        frame.setVisible(true);
        frame.repaint();
        frame.pack();
        frame.revalidate();




        SlingController thirdLevelController = new SlingController(thirdLevel, thirdLevel.getSlingshotBoy());
        thirdView.addKeyListener(thirdLevelController);
        thirdView.requestFocus();

        thirdLevel.start();

        Switch check = new Switch(this, thirdLevel.getSlingshotBoy());
        thirdLevel.addStepListener(check);
//
//
//
//        thirdLevel.start();



//        optional: uncomment this to make a debugging view
//         JFrame debugView = new DebugViewer(secondLevel, 500, 500);






    }

    public void gameEndedOne() throws IOException {
        firstLevel.stop();
        firstLevel.getClip().stop();
        checkHighScore(firstLevel.getSlingshotBoy().getScore(), (int) firstLevel.getSlingshotBoy().getLives());
        frame.setVisible(false);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setPreferredSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        gameoverFrame = new JFrame("Slingshot Fail");
        frame.remove(firstView);
        frame.add(gameOver.getMainPanel());
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }

    public void gameEndedTwo() throws IOException {
        secondLevel.stop();
        secondLevel.getClip().stop();
        checkHighScore(secondLevel.getSlingshotBoy().getScore(),(int) secondLevel.getSlingshotBoy().getLives());
        frame.setVisible(false);
        frame.remove(secondView);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setPreferredSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        frame.add(gameOver.getMainPanel());
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }

    public void gameEndedThree() throws IOException {
        thirdLevel.stop();
        thirdLevel.getClip().stop();
        checkHighScore(thirdLevel.getSlingshotBoy().getScore(),(int) thirdLevel.getSlingshotBoy().getLives());
        frame.setVisible(false);
        frame.remove(thirdView);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setPreferredSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(false);
        frame.add(gameOver.getMainPanel());
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }

    public void gameSuccess() throws IOException {
        thirdLevel.stop();
        thirdLevel.getClip().stop();
        checkHighScore(thirdLevel.getSlingshotBoy().getScore(),(int) thirdLevel.getSlingshotBoy().getLives());
        frame.setVisible(false);
        frame.remove(thirdView);
        gameOver = new GameOver(this);
        gameOver.getMainPanel().setPreferredSize(new Dimension(500, 500));
        gameOver.getMainPanel().setWon(true);
        frame.add(gameOver.getMainPanel());
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }



    public void switchStartMenu() {
        frame.setVisible(false);
        gameOver.getMainPanel().setPreferredSize(new Dimension(500, 500));
        this.frame.remove(gameOver.getMainPanel());
        this.frame.add(startMenu.getMenuPanel());
        this.frame.setVisible(true);
        this.frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    public void switchLevelThree() throws IOException {
        scoreWriterLevelTwo = new HighScoreWriter("File", secondLevel.getSlingshotBoy().getScore(), secondLevel.getSlingshotBoy().getLives(), true);
        scoreReaderLevelThree = new HighScoreReader("File");

        secondLevel.stop();
        secondLevel.getClip().stop();

        thirdLevel = new Level3(this);

        this.frame.setVisible(false);

        Slingshot thirdSlingshotBoy = thirdLevel.getSlingshotBoy();
        slingshotBoy.setScore(scoreReaderLevelThree.getReadScore());
        slingshotBoy.setLives(scoreReaderLevelThree.getReadLives());

        thirdView = new GameView(thirdLevel, 700, 700, thirdSlingshotBoy , "CastleBackground", this);




        frame.remove(secondView);

        frame.add(thirdView);
        frame.repaint();
        frame.pack();
        frame.revalidate();
        SlingController thirdSlingController = new SlingController(thirdLevel, thirdSlingshotBoy);
        thirdView.addKeyListener(thirdSlingController);
        thirdView.requestFocus();
        thirdLevel.start();
        this.frame.setVisible(true);

        Switch checking = new Switch(this, thirdSlingshotBoy);
        thirdLevel.addStepListener(checking);
    }


    public void switchLevelTwo() throws IOException {
        scoreWriterLevelOne = new HighScoreWriter("Level", firstLevel.getSlingshotBoy().getScore(), firstLevel.getSlingshotBoy().getLives(), true);
        scoreReaderLevelTwo = new HighScoreReader("Level");
        secondLevel = new Level2(this);

        firstLevel.stop();
        firstLevel.getClip().stop();




        this.frame.setVisible(false);

        Slingshot secondLevelSlingshotBoy = secondLevel.getSlingshotBoy();
        secondLevelSlingshotBoy.setLives(scoreReaderLevelTwo.getReadLives());
        secondLevelSlingshotBoy.setScore(scoreReaderLevelTwo.getReadScore());
        secondView = new GameView(secondLevel, 700, 700, secondLevelSlingshotBoy, "VolcanoBackground", this);
        frame.remove(firstView);



        frame.add(secondView);
        frame.repaint();
        frame.pack();
        frame.revalidate();
        SlingController secondSlingController = new SlingController(secondLevel, secondLevelSlingshotBoy);
        secondView.addKeyListener(secondSlingController);
        secondView.requestFocus();
        secondLevel.start();
        this.frame.setVisible(true);

        Switch checking = new Switch(this, secondLevelSlingshotBoy);
        secondLevel.addStepListener(checking);
    }


    public void switchLevelOne() throws IOException {
        firstLevel = new Level1(this);


        this.frame.setVisible(false);

        startMenu.getClip().stop();

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

    public Level3 getThirdLevel() { return thirdLevel; }

    public int getKeysLevelOne() {
        return 3;
    }

    public int getKeysLevelTwo() {
        return 4;
    }

    public int getKeysLevelThree() {
        return 5;
    }

    public void checkHighScore(int currentScore, int currentLives) throws IOException {
        if (currentScore > highScore) {
            highScore = currentScore;
            HighScoreWriter newHighScore = new HighScoreWriter("HighScore", highScore, currentLives, false);
        }
    }




    /**
     * Run the game.
     */
    public static void main(String[] args) throws IOException {

        new Game();
    }

}
