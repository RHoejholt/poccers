package app.entities;

public class Item {

    int itemID;
    String itemName;
    String itemDescription;

    public Item(int itemID, String itemName, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return itemName;
    }

    public String getDescription() {
        return itemDescription;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
