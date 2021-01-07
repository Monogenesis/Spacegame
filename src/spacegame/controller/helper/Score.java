package spacegame.controller.helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Score implements Comparable {
    // public static ArrayList<Score> scores = new ArrayList<>();
    public static ArrayList<Score> scores = new ArrayList<>();
    public final int value;
    public final String date;
    public final int id;
    private boolean isLastPlayerScore;
    private static int amountOfScores = 0;

    public Score(int value) {
        this.id = amountOfScores++;
        this.value = value;
        this.date = LocalDate.now().toString();
        scores.add(this);
        setLastScoreFlag();
        sortHighscores();
    }

    public Score(int value, String date) {
        this.id = amountOfScores++;
        this.value = value;
        this.date = date;
        scores.add(this);
        sortHighscores();
    }

    public void sortHighscores() {
        scores.sort(null);
        Collections.reverse(scores);
    }

    private void setLastScoreFlag() {
        for (Score score : scores) {
            score.isLastPlayerScore = false;
        }
        this.isLastPlayerScore = true;
    }

    @Override
    public String toString() {
        return "{" + " value='" + value + "'" + ", date='" + date + "'" + ", id='" + id + "'" + "}";
    }

    public boolean isLastPlayerScore() {
        return this.isLastPlayerScore;
    }

    @Override
    public int compareTo(Object o) {
        Score score = (Score) o;
        if (this.value < score.value)
            return -1;
        else if (this.value == score.value)
            return 0;

        return 1;

    }

}
