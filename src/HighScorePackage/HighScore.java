package HighScorePackage;

import java.io.Serializable;
import java.util.Date;

public class HighScore implements Serializable {
    private String name;
    private int score;
    private Date date;

    public HighScore(String name, int score, Date date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%-" + name.length() + "s %-4s @ %s", name, score, date.toString());
    }
}
