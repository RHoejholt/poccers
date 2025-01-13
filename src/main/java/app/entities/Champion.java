package app.entities;

public class Champion {

    int championID;
    String championName;
    String description;

    public Champion(int championID, String championName, String description) {
        this.championID = championID;
        this.championName = championName;
        this.description = description;
    }

    public int getChampionID() {
        return championID;
    }

    public String getChampionName() {
        return championName;
    }

    public String getDescription() {
        return description;
    }
}
