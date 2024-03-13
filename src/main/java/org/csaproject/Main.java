package org.csaproject;


import java.io.IOException;

import com.googlecode.lanterna.screen.Screen;
import org.csaproject.Layout.Floor;
import org.csaproject.Room.Room;

import org.csaproject.Person.Person;
import org.csaproject.UIMethods.UI;
import org.csaproject.classes.floor2.HallwayIT.Floor3;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Person player = new Person();


        Floor floor = Floor3.run();
        floor.setPlayerPosition(player);
        UI ui = new UI(floor, player);
        ui.init();
        char[][] classLayout = ui.render();
        System.out.println("Welcome to the game");

        for (char[] row : classLayout) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

        System.out.println(player.getPosX() + " " + player.getPosY());
        System.out.println(floor.getInteractableObjectsMap()[player.getPosY()][player.getPosX()]);

        while (true) {
            ui.startKeyListening();

        }
    }

    public static void SpecialInteraction() {
        System.out.println("No GUI");
    }
}
