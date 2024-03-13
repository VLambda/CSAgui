package org.csaproject.Layout;

import org.csaproject.Person.Person;
import org.csaproject.Room.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FloorSpace {
    private Room[] rooms;
    private char[][] floorSpaceLayout;
    private int[][] interactableObjectsMap;
    private Person player;

    public FloorSpace(String LayoutFile, String InteractionFile, Room[] rooms) {
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
    }
}