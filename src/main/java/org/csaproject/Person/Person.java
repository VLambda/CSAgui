package org.csaproject.Person;

import org.csaproject.Room.Item;

import java.util.ArrayList;

public class Person {
    private ArrayList<Item> itemList = new ArrayList<>();
    private int posX;
    private int posY;

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void moveUp() {
            posY--;
    }

    public void moveDown() {
            posY++;
    }

    public void moveLeft() {
            posX--;
    }

    public void moveRight() {
            posX++;
    }
}
