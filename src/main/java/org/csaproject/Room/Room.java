package org.csaproject.Room;

import org.csaproject.Person.Person;

import java.util.ArrayList;


public class Room {
    private int RoomNum;
    private String RoomName;
    private char[][] classLayout;
    private int[][] interactableObjectsMap;
    private ArrayList<Item> itemList;
    private ArrayList<Interaction> interactionList;

    public Room(int RoomNum, String RoomName, char[][] classLayout, int[][] interactableObjectsMap, ArrayList<Item> itemList, ArrayList<Interaction> interactionList) {
        this.RoomNum = RoomNum;
        this.RoomName = RoomName;
        this.classLayout = classLayout;
        this.interactableObjectsMap = interactableObjectsMap;
        this.itemList = itemList;
        this.interactionList = interactionList;
    }

    public int getRoomNum() {
        return RoomNum;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public void setRoomNum(int roomNum) {
        RoomNum = roomNum;
    }

    public char[][] getClassLayout() {
        return classLayout;
    }

    public int[][] getInteractableObjectsMap() {
        return interactableObjectsMap;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public Item getItem(int index) {
        return itemList.get(index);
    }

    public void setPlayerPosition(Person person) {
        for (int y = 0; y < interactableObjectsMap.length; y++) {
            for (int x = 0; x < interactableObjectsMap[y].length; x++) {
                if (interactableObjectsMap[y][x] == -4) { // -4 corresponds to 's'
                    person.setPosX(x);
                    person.setPosY(y);
                    return;
                }
            }
        }
    }

    public ArrayList<Interaction> getInteractionList() {
        return interactionList;
    }

    public Interaction getInteraction(int index) {
        return interactionList.get(index);
    }
}
