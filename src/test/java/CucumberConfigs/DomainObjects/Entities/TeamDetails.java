package CucumberConfigs.DomainObjects.Entities;

import java.util.List;

public class TeamDetails {
    private String teamName;
    private String category;

    private List<Player> players;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Player> getplayers() {
        return players;
    }

    public void setplayers(List<Player> players) {
        this.players = players;
    }
}
