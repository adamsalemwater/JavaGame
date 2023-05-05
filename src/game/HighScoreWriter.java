package game;

import java.io.FileWriter;
import java.io.IOException;

public class HighScoreWriter {

    private String fileName;

    public HighScoreWriter(String fileName, int score, float lives, boolean append) throws IOException {
        this.fileName = fileName;


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
