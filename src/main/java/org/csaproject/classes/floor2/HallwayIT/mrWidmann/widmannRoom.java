package org.csaproject.classes.floor2.HallwayIT.mrWidmann;

import org.csaproject.Room.Room;
import org.csaproject.Room.RoomMap;
import org.csaproject.Room.Interaction;
import org.csaproject.Room.Item;

import java.util.ArrayList;

public class widmannRoom {
    public Room run() {
        Item TeacherLaptop = new Item("Mr. Widmann's Laptop", "This is a Mr. Widmann's Laptop", 1);
        Item SolderingStation = new Item("Soldering Station", "This is a Soldering Station", 2);
        Item SMTCircuitBoard = new Item("SMT Circuit Board", "This is a SMT Circuit Board chaped like a LadyBug", 3);
        Item ArdunioProjectGlochan = new Item("Glochan's and Vedang's Ardunio Project", "It Looks Like the Game \"Snake\"", 4);
        Item CoffeePot = new Item("Coffee Pot", "This is Mr. Widmann's Coffee Pot", 5);
        Item ArduinoKit = new Item("Arduino Kit", "This is a Arduino Kit", 6);
        Item Tape = new Item("Tape", "This is Duct Tape with an American Flag Design", 7);
        Item RaspberryPi = new Item("Raspberry Pi", "Looks like it is hooked up", 8);
        Item HelloKittyPoster = new Item("Hello Kitty Poster", "This looks pretty odd to have in an IT Room", 9);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(TeacherLaptop);
        itemList.add(SolderingStation);
        itemList.add(SMTCircuitBoard);
        itemList.add(ArdunioProjectGlochan);
        itemList.add(CoffeePot);
        itemList.add(ArduinoKit);
        itemList.add(Tape);
        itemList.add(RaspberryPi);
        itemList.add(HelloKittyPoster);

        Interaction TeacherLaptopInteraction = new Interaction(TeacherLaptop, false);
        Interaction SolderingStationInteraction = new Interaction(SolderingStation, false);
        Interaction SMTCircuitBoardInteraction = new Interaction(SMTCircuitBoard, true);
        Interaction ArdunioProjectGlochanInteraction = new Interaction(ArdunioProjectGlochan, true);
        Interaction CoffeePotInteraction = new Interaction(CoffeePot, true);
        Interaction ArduinoKitInteraction = new Interaction(ArduinoKit, true);
        Interaction TapeInteraction = new Interaction(Tape, true);
        Interaction RaspberryPiInteraction = new Interaction(RaspberryPi, true);
        Interaction HelloKittyPosterInteraction = new Interaction(HelloKittyPoster, true);

        ArrayList<Interaction> interactionList = new ArrayList<>();
        interactionList.add(TeacherLaptopInteraction);
        interactionList.add(SolderingStationInteraction);
        interactionList.add(SMTCircuitBoardInteraction);
        interactionList.add(ArdunioProjectGlochanInteraction);
        interactionList.add(CoffeePotInteraction);
        interactionList.add(ArduinoKitInteraction);
        interactionList.add(TapeInteraction);
        interactionList.add(RaspberryPiInteraction);
        interactionList.add(HelloKittyPosterInteraction);


        Room room = getClassRoom(itemList, interactionList);

        return room;
    }

    private static Room getClassRoom(ArrayList<Item> itemList, ArrayList<Interaction> interactionList) {
        String mapFile = "C:\\Users\\2000152748\\IdeaProjects\\TUI\\src\\main\\java\\org\\csaproject\\classes\\floor2\\HallwayIT\\mrWidmann\\WidmannClassMap.txt";
        String interactionFile = "C:\\Users\\2000152748\\IdeaProjects\\TUI\\src\\main\\java\\org\\csaproject\\classes\\floor2\\HallwayIT\\mrWidmann\\WidmannInteractionMap.txt";

        RoomMap roomMap = new RoomMap(mapFile, interactionFile);
        Room room = new Room(1, "Mr. Widmann's Room", roomMap.getClassroomLayout(), roomMap.getInteractableObjectsMap(), itemList, interactionList);
        return room;
    }
}
