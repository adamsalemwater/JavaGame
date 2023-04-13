package game;

import java.io.FileWriter;
import java.io.IOException;

public class HighScoreWriter {

    private String fileName;

    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeHighScore(int score, float lives) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(score + "," + lives + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
