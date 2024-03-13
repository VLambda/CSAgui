package org.csaproject.UIMethods;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.csaproject.Room.Room;
import org.csaproject.Room.Item;
import org.csaproject.Person.Person;
import org.csaproject.Layout.Floor;

import java.io.PrintStream;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UI {
    private int prevX;
    private int prevY;
    private int[][] interactableObjectsMap;
    private Room room;
    private Person person;
    private Floor floor;
    private char[][] classLayout;
    private Screen screen;
    private boolean graphicUIActive;
    private Terminal terminal;


    public UI(Floor floor, Person person) {
        this.floor = floor;
        this.person = person;
        classLayout = floor.getFloorSpaceLayout();
        interactableObjectsMap = floor.getInteractableObjectsMap();
    }

    public void init() throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        this.screen = screen;
        this.terminal = terminal;
        initrender();
    }

    public Screen getScreen() {
        return screen;
    }

    public Screen startKeyListening() throws IOException {
        KeyStroke keyStroke = screen.pollInput();

        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case Escape:
                    break;
                case ArrowUp:
                    prevX = person.getPosX();
                    prevY = person.getPosY();
                    person.moveUp();
                    break;
                case ArrowDown:
                    prevX = person.getPosX();
                    prevY = person.getPosY();
                    person.moveDown();
                    break;
                case ArrowLeft:
                    prevX = person.getPosX();
                    prevY = person.getPosY();
                    person.moveLeft();
                    break;
                case ArrowRight:
                    prevX = person.getPosX();
                    prevY = person.getPosY();
                    person.moveRight();
                    break;
                case F5:
                    graphicUIActive = !graphicUIActive;
                    System.out.println(graphicUIActive);
                    if (!graphicUIActive) {
                        terminal.enterPrivateMode();
                        System.out.println("Graphic UI Active");
                        terminal.enterPrivateMode();
                    } else {
                        terminal.exitPrivateMode();
                    }
                    break;
                case Character:
                    if (keyStroke.getCharacter() == 'w') {
                        prevY = person.getPosY();
                        prevX = person.getPosX();
                        person.moveUp();
                    } else if (keyStroke.getCharacter() == 's') {
                        prevY = person.getPosY();
                        prevX = person.getPosX();
                        person.moveDown();
                    } else if (keyStroke.getCharacter() == 'a') {
                        prevX = person.getPosX();
                        prevY = person.getPosY();
                        person.moveLeft();
                    } else if (keyStroke.getCharacter() == 'd') {
                        prevX = person.getPosX();
                        prevY = person.getPosY();
                        person.moveRight();
                    } else if (keyStroke.getCharacter() == 'e') {
                        if (interactableObjectsMap[person.getPosY()][person.getPosX()] == -5) {
                            if (interactableObjectsMap[person.getPosY()][person.getPosX() + 1] == -1) {
                                int classIndex = interactableObjectsMap[person.getPosY()][person.getPosX() + 2];
                                floor.getRoom(classIndex - 1);
                            } else if (interactableObjectsMap[person.getPosY()][person.getPosX() - 1] == -1) {
                                int classIndex = interactableObjectsMap[person.getPosY()][person.getPosX() - 2];
                                floor.getRoom(classIndex - 1);
                            } else if (interactableObjectsMap[person.getPosY() + 1][person.getPosX()] == -1) {
                                int classIndex = interactableObjectsMap[person.getPosY() + 2][person.getPosX()];
                                floor.getRoom(classIndex - 1);
                            } else if (interactableObjectsMap[person.getPosY() - 1][person.getPosX()] == -1) {
                                int classIndex = interactableObjectsMap[person.getPosY() - 2][person.getPosX()];
                                floor.getRoom(classIndex - 1);
                            }
                        }
                        break;
                    }
                    return screen;
            }
        }
        return screen;
    }

    /*private char[][] initrender() {
        char[][] Map = new char[classLayout.length][];
        char[][] renderedMap = new char[26][13];

        int positionx = person.getPosX();
        int positiony = person.getPosY();

        for (int i = 0; i < classLayout.length; i++) {
            Map[i] = new char[classLayout[i].length];
            for (int j = 0; j < classLayout[i].length; j++) {
                if (i == person.getPosY() && j == person.getPosX()) {
                    Map[i][j] = '*';
                } else {
                    Map[i][j] = classLayout[i][j];
                }
            }
        }
        return renderedMap;
    }*/

    private char[][] initrender() {
        char[][] Map = new char[classLayout.length][];
        char[][] renderedMap = new char[13][26];

        int positionx = person.getPosX();
        int positiony = person.getPosY();
        int starx = 0;
        int stary = 0;

        for (int i = 0; i < classLayout.length; i++) {
            Map[i] = new char[classLayout[i].length];
            for (int j = 0; j < classLayout[i].length; j++) {
                if (i == person.getPosY() && j == person.getPosX()) {
                    Map[i][j] = '*';
                    starx = i;
                    stary = j;
                } else {
                    Map[i][j] = classLayout[i][j];
                }
            }
        }


        // Copy the Map to the renderedMap with the star in the 7th row
        for (int i = 0; i < renderedMap.length; i++) {
            for (int j = 0; j < renderedMap[i].length; j++) {
                renderedMap[i][j] = Map[i][stary - 13 + j];
            }
        }

        return renderedMap;
    }




    public char[][] render() throws IOException, InterruptedException {
        char[][] Map = new char[classLayout.length][];
        if (!validMove()) {
            person.setPosX(prevX);
            person.setPosY(prevY);
            render();
        }
        for (int i = 0; i < classLayout.length; i++) {
            Map[i] = new char[classLayout[i].length];
            for (int j = 0; j < classLayout[i].length; j++) {
                if (i == person.getPosY() && j == person.getPosX()) {
                    Map[i][j] = '*';
                } else if (Map[i][j] == 'h') {
                    Map[i][j] = ' ';
                } else {
                    Map[i][j] = classLayout[i][j];
                }
            }
        }
        return Map;
    }


    public boolean validMove() {
        return  (interactableObjectsMap[person.getPosY()][person.getPosX()] == 0 ||
                interactableObjectsMap[person.getPosY()][person.getPosX()] == -4 ||
                interactableObjectsMap[person.getPosY()][person.getPosX()] == -5);
    }
}

