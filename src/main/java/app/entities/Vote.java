package app.entities;

public class Vote {

    int rating;
    int voteID;
    int itemID;
    int championID;
    String ipaddress;

    public Vote(int rating, int voteID, int itemID, int championID, String ipaddress) {
        this.rating = rating;
        this.voteID = voteID;
        this.itemID = itemID;
        this.championID = championID;
        this.ipaddress = ipaddress;
    }

    public int getRating() {
        return rating;
    }

    public int getVoteID() {
        return voteID;
    }

    public int getItemID() {
        return itemID;
    }

    public int getChampionID() {
        return championID;
    }

    public String getIpaddress() {
        return ipaddress;
    }
}
