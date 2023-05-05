package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreReader {

    private String fileName;
    private int readScore;
    private float readLives;

    public HighScoreReader(String fileName) throws IOException {
        this.fileName = fileName;

        FileReader fr = null;
        BufferedReader reader = null;
        int totalScore = 0;
        float totalLives = 0;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                int score = Integer.parseInt(tokens[0]);
                float lives = Float.parseFloat(tokens[1]);
                line = reader.readLine();
                totalScore += score;
                totalLives = lives;
            }
            readScore = totalScore;
            readLives = totalLives;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }



    public int getReadScore() {
        return readScore;
    }

    public float getReadLives() {
        return readLives;
    }
}
