package spacegame.controller.filehandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import spacegame.controller.helper.Score;

public class HighscoreLoader {

    private static String filePath = "highscore.txt";

    public static void saveHighscores() {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));) {

            for (int i = 0; i < Score.scores.size(); i++) {
                if (i == 10)
                    break;
                out.writeInt(Score.scores.get(i).value);
                out.writeUTF(Score.scores.get(i).date);
            }

        } catch (IOException e) {

        }

    }

    public static void loadHighscore() {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));) {

            while (true) {
                new Score(in.readInt(), in.readUTF());
            }

        } catch (IOException e) {

        }
    }

}
