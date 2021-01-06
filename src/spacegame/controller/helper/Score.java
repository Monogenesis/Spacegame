package spacegame.controller.helper;

import java.time.LocalDate;
import java.util.HashMap;

public class Score {
    // public static ArrayList<Score> scores = new ArrayList<>();
    public static HashMap<Integer, Score> scores = new HashMap<>();
    public final int value;
    public final String date;
    public final int id;
    private static int amountOfScores = 0;

    public Score(int value) {
        this.id = amountOfScores++;
        this.value = value;
        this.date = LocalDate.now().toString();
        scores.put(id, this);
    }

    public Score(int value, String date) {
        this.id = amountOfScores++;
        this.value = value;
        this.date = date;
        scores.put(id, this);
    }

    @Override
    public String toString() {
        return "{" + " value='" + value + "'" + ", date='" + date + "'" + ", id='" + id + "'" + "}";
    }

}
