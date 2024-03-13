package org.csaproject.Layout;

import org.csaproject.Person.Person;
import org.csaproject.Room.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Floor {
    private Room[] rooms;
    private char[][] floorSpaceLayout;
    private int[][] interactableObjectsMap;

    public Floor(String LayoutFile, String InteractionFile, Room[] rooms) {
        this.rooms = rooms;
        buildMapLayout(LayoutFile, InteractionFile);
    }

    private void buildMapLayout(String MapFile, String InteractionFile) {
        List<char[]> floorLayout = new ArrayList<>();
        List<int[]> interactableLayout = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MapFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                floorLayout.add(line.toCharArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(InteractionFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                int[] interactableRow = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        interactableRow[i] = Character.getNumericValue(c);
                    } else if (c == '+' || c == '|' || c == '-' || c == '=') {
                        interactableRow[i] = -2;
                    } else if (c == 'c') {
                        interactableRow[i] = -1;
                    } else if (c == '\\') {
                        interactableRow[i] = -3;
                    } else if (c == 's') {
                        interactableRow[i] = -4;
                    } else if (c == 'd') {
                        interactableRow[i] = -5;
                    } else if (c == '?') {
                        interactableRow[i] = -6;
                    } else {
                        interactableRow[i] = 0;
                    }
                }
                interactableLayout.add(interactableRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to a 2D array
        char[][] layoutArray = new char[floorLayout.size()][];
        for (int i = 0; i < floorLayout.size(); i++) {
            layoutArray[i] = floorLayout.get(i);
        }

        // Convert the list to a 2D array
        int[][] interactableArray = new int[interactableLayout.size()][];
        for (int i = 0; i < interactableLayout.size(); i++) {
            interactableArray[i] = interactableLayout.get(i);
        }

        this.floorSpaceLayout = layoutArray;
        this.interactableObjectsMap = interactableArray;
    }

    public void setPlayerPosition(Person person) {
        for (int y = 0; y < interactableObjectsMap.length; y++) {
            for (int x = 0; x < interactableObjectsMap[y].length; x++) {
                if (interactableObjectsMap[y][x] == -4) {
                    person.setPosX(x);
                    person.setPosY(y);
                    return;
                }
            }
        }
    }

    public char[][] getFloorSpaceLayout() {
        return floorSpaceLayout;
    }

    public int[][] getInteractableObjectsMap() {
        return interactableObjectsMap;
    }

    public Room getRoom(int index) {
        return rooms[index];
    }
}
