package org.csaproject.classes.floor2.HallwayIT;

import org.csaproject.Layout.Floor;
import org.csaproject.Room.Room;
import org.csaproject.classes.floor2.HallwayIT.*;
import org.csaproject.classes.floor2.HallwayIT.mrWidmann.widmannRoom;

public class Floor3 {
    public static Floor run() {
        Room[] rooms = new Room[1];
        rooms[0] = new widmannRoom().run();

        String InteractionFile = "C:\\Users\\2000152748\\IdeaProjects\\TUI\\src\\main\\java\\org\\csaproject\\classes\\floor2\\HallwayIT\\Floor.txt";
        String LayoutFile = "C:\\Users\\2000152748\\IdeaProjects\\TUI\\src\\main\\java\\org\\csaproject\\classes\\floor2\\HallwayIT\\FloorLayout.txt";


        return new Floor(LayoutFile, InteractionFile, rooms);
    }
}
