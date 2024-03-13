package org.csaproject.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomMap {
    private char[][] classroomLayout;
    private int[][] interactableObjectsMap;

    public RoomMap(String MapFile, String InteractionFile) {
        List<char[]> classroomLayout = new ArrayList<>();
        List<int[]> interactableLayout = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MapFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                classroomLayout.add(line.toCharArray());
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
                    } else if (c == '\\' || c == '/') {
                        interactableRow[i] = -3;
                    } else if (c == 's') {
                        interactableRow[i] = -4;
                    } else if (c == 'e') {
                        interactableRow[i] = -5;
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
        char[][] layoutArray = new char[classroomLayout.size()][];
        for (int i = 0; i < classroomLayout.size(); i++) {
            layoutArray[i] = classroomLayout.get(i);
        }

        // Convert the list to a 2D array
        int[][] interactableArray = new int[interactableLayout.size()][];
        for (int i = 0; i < interactableLayout.size(); i++) {
            interactableArray[i] = interactableLayout.get(i);
        }

        this.classroomLayout = layoutArray;
        this.interactableObjectsMap = interactableArray;
    }

    public char[][] getClassroomLayout() {
        return classroomLayout;
    }
    public int[][] getInteractableObjectsMap() {
        return interactableObjectsMap;
    }

    public int getInteractableObject(int x, int y) {
        return interactableObjectsMap[y][x];
    }
}
