package draganddrop.studybuddy.model.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * stores information about the user's score
 */
public class ScoreStats {
    private static final int noviceScore = 10;
    private static final int apprenticeScore = 50;
    private static final int dukeScore = 100;
    private static final int expertScore = 200;
    private static final int masterScore = 300;
    private static final int grandmasterScore = 400;
    private static final int legendScore = 500;
    private static final int sageScore = 1000;
    private static final int enlightenedScore = 2000;

    /**
     * List that contains the user's score for each day
     */
    private List<Integer> scoreList;

    private int currentScore;

    public ScoreStats() {
        this.initList();
    }

    public List<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Integer> scoreList) {
        this.scoreList = scoreList;
    }

    /**
     * initializes a scoreList with zero score for all days
     */
    public void initList() {
        scoreList = new ArrayList<>();
        for (int i = 0; i < 366; i++) {
            scoreList.add(0);
        }
    }

    /**
     * copies the currentScore and scoreList from the scoreStats
     * @param scoreStats the scoreStats to be copied from
     */
    public void copy(ScoreStats scoreStats) {
        this.currentScore = scoreStats.getCurrentScore();
        this.scoreList = scoreStats.getScoreList();
        if (scoreList.get(StatsUtil.getDayIndex()) == 0) {
            scoreList.set(StatsUtil.getDayIndex(), currentScore);
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * increments the score at current day index
     *
     * @param dayIndex the day in which the score is to be added
     */
    public void addScore(int dayIndex, int scoreToAdd) {
        currentScore += scoreToAdd;
        scoreList.set(dayIndex, currentScore);
    }

    /**
     * Returns user's score on the given day
     *
     * @param dayIndex the day of interest
     * @return user's score on the given day
     */
    public int getDailyScore(int dayIndex) {
        return scoreList.get(dayIndex);
    }

    /**
     * Returns a list containing the user's daily scores over the past 7 days
     * @param dayIndex the day of interest
     * @return a list containing the user's daily scores over the past 7 days
     */
    public List<Integer> getWeeklyScores(int dayIndex) {
        return scoreList.subList(dayIndex - 6, dayIndex + 1);
    }



    public String getRank() {
        if (currentScore < noviceScore) {
            return "Beginner";
        } else if (currentScore < apprenticeScore) {
            return "Novice";
        } else if (currentScore < dukeScore) {
            return "Apprentice";
        } else if (currentScore < expertScore) {
            return "Duke";
        } else if (currentScore < masterScore) {
            return "Expert";
        } else if (currentScore < grandmasterScore) {
            return "Master";
        } else if (currentScore < legendScore) {
            return "Grandmaster";
        } else if (currentScore < sageScore) {
            return "Legend";
        } else if (currentScore < enlightenedScore) {
            return "Sage";
        } else {
            return "Enlightened";
        }
    }

    public String getNextRank() {
        if (currentScore < noviceScore) {
            return (noviceScore - currentScore) + " PP more to become a Novice";
        } else if (currentScore < apprenticeScore) {
            return (apprenticeScore - currentScore) + " PP more to become an Apprentice";
        } else if (currentScore < dukeScore) {
            return (dukeScore - currentScore) + " PP more to become a Duke";
        } else if (currentScore < expertScore) {
            return (expertScore - currentScore) + " PP more to become a Master";
        } else if (currentScore < masterScore) {
            return (expertScore - currentScore) + " PP more to become a Grandmaster";
        } else if (currentScore < grandmasterScore) {
            return (grandmasterScore - currentScore) + " PP more to become a Grandmaster";
        } else if (currentScore < legendScore) {
            return (legendScore - currentScore) + " PP more to become a Legend";
        } else if (currentScore < sageScore) {
            return (sageScore - currentScore) + " PP more to become a Grandmaster";
        } else if (currentScore < enlightenedScore) {
            return (enlightenedScore - currentScore) + " PP more to become a Grandmaster";
        } else {
            return "You have mastered the productivity secrets of the universe.";
        }
    }

}
