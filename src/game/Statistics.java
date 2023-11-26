package game;

import java.io.Serializable;

public class Statistics implements Serializable {

    private String userName;
    private int score;
    private int tries;


    public Statistics(String userName, int score, int tries) {
        this.userName = userName;
        this.score = score;
        this.tries = tries;
    }

    public Statistics() {
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public int getTries() {
        return tries;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }


}
