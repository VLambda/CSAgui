package org.csaproject.Room;

public class Item {
    private String ItemName;
    private String ItemDescription;
    private int ItemID;
    private boolean ItemActive = false;

    public Item(String ItemName, String ItemDescription, int ItemID) {
        this.ItemName = ItemName;
        this.ItemDescription = ItemDescription;
        this.ItemID = ItemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public int getItemID() {
        return ItemID;
    }

    public boolean isItemActive() {
        return ItemActive;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public void setItemActive(boolean itemActive) {
        ItemActive = itemActive;
    }
}
