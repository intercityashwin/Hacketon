package CucumberConfigs.DomainObjects.Entities;

public class Player {
    private String playerName;
    private String matchesPlayed;
    private String runsScored;
    private String average;
    private String strikeRate;
    private String hundredsScored;
    private String fiftiesScored;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(String matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public String getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(String runsScored) {
        this.runsScored = runsScored;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public String getHundredsScored() {
        return hundredsScored;
    }

    public void setHundredsScored(String hundredsScored) {
        this.hundredsScored = hundredsScored;
    }

    public String getFiftiesScored() {
        return fiftiesScored;
    }

    public void setFiftiesScored(String fiftiesScored) {
        this.fiftiesScored = fiftiesScored;
    }
}
