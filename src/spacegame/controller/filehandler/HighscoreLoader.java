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

    public static void updateHighscores() {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));) {

            for (Score score : Score.scores.values()) {
                out.writeInt(score.value);
                out.writeUTF(score.date);
            }

        } catch (IOException e) {
            System.out.println("FILE END!");
        }

    }

    public static void readHighscore() {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));) {

            while (true) {
                new Score(in.readInt(), in.readUTF());
            }

        } catch (IOException e) {
            System.out.println("FILE END!");
        }
        for (Score score : Score.scores.values()) {
            System.out.println(score);
        }
    }

}
