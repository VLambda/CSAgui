package org.csaproject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.csaproject.ClassRoom.ClassRoom;
import org.csaproject.ClassRoom.ClassRoomMap;
import org.csaproject.ClassRoom.Interaction;
import org.csaproject.ClassRoom.Item;

import org.csaproject.Person.Person;
import org.csaproject.UIMethods.UI;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Person player = new Person();


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


        String mapFile = "C:\\Users\\2000152748\\IdeaProjects\\TUI\\src\\main\\java\\org\\csaproject\\WidmannClassMap.txt";
        String interactionFile = "C:\\Users\\2000152748\\IdeaProjects\\TUI\\src\\main\\java\\org\\csaproject\\WidmannInterationMap.txt";

        ClassRoomMap classRoomMap = new ClassRoomMap(mapFile, interactionFile);
        ClassRoom classRoom = new ClassRoom(1, "Mr. Widmann's Room", classRoomMap.getClassroomLayout(), classRoomMap.getInteractableObjectsMap(), itemList, interactionList);

        player.addItem(itemList.get(6));
        classRoom.setPlayerPosition(player);

        UI ui = new UI(classRoom, player);
        Screen screen = ui.getScreen();
        if (!ui.buildUI(screen)) {
            SpecialInteraction();
        }



        player.addItem(itemList.get(7));
    }

    public static void SpecialInteraction() {

        System.out.println("Special Interaction");
    }
}
