package org.csaproject.UIMethods;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.csaproject.Room.Room;
import org.csaproject.Room.Item;
import org.csaproject.Person.Person;

import java.util.ArrayList;

import java.io.IOException;
import java.util.Arrays;

public class GraphicUI {
    private int prevX;
    private int prevY;
    private char[][] classLayout;
    private int[][] interactableObjectsMap;
    private Room room;
    private Person person;


    public GraphicUI (Room room, Person person) {
        this.classLayout = room.getClassLayout();
        this.interactableObjectsMap = room.getInteractableObjectsMap();
        this.room = room;
        this.person = person;

    }

    public Screen getScreen() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        return new TerminalScreen(terminal);
    }

    public boolean buildUI(Screen screen) throws IOException, InterruptedException {
        int stoppingItem = 0;
        screen.startScreen();

        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);


        screen.doResizeIfNecessary();

        // Get the terminal size
        TerminalSize terminalSize = screen.getTerminalSize();

        // Calculate 80% of the terminal width for the viewPanel
        int viewPanelWidth = (int) (terminalSize.getColumns() * 0.8);

        // Calculate 20% of the terminal width for the secondaryPanel
        int secondaryPanelWidth = terminalSize.getColumns() - viewPanelWidth;

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.EXPANDED));

        // Create panels to hold components
        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

        Panel viewPanel = new Panel();
        mainPanel.addComponent(viewPanel.withBorder(Borders.singleLine("View")));
        viewPanel.setPreferredSize(new TerminalSize(viewPanelWidth, terminalSize.getRows()));

        ArrayList<Label> Map = update();
        for (Label label : Map) {
            viewPanel.addComponent(label);
        }

        Panel secondaryPanel = new Panel();
        secondaryPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        Panel controlsPanel = new Panel();
        secondaryPanel.addComponent(controlsPanel.withBorder(Borders.singleLine("Controls")));
        controlsPanel.setPreferredSize(new TerminalSize(secondaryPanelWidth, (int) (terminalSize.getRows() * 0.7)));
        new Label("W - Forward").addTo(controlsPanel);
        new Label("A - Left").addTo(controlsPanel);
        new Label("S - Backward").addTo(controlsPanel);
        new Label("D - Right").addTo(controlsPanel);
        new Label("E - Interact").addTo(controlsPanel);
        new Label("Esc - Exit Menu").addTo(controlsPanel);


        Panel inventoryPanel = new Panel();
        secondaryPanel.addComponent(inventoryPanel.withBorder(Borders.singleLine("Inventory")));
        inventoryPanel.setPreferredSize(new TerminalSize(secondaryPanelWidth, (int) (terminalSize.getRows() * 0.3)));

        //Print Inventory into the Inventory Panel
        for (int i = 0; i < person.getItemList().size(); i++) {
            new Label(person.getItemList().get(i).getItemName()).addTo(inventoryPanel);
        }

        mainPanel.addComponent(secondaryPanel, LinearLayout.createLayoutData(LinearLayout.Alignment.Fill));


        mainPanel.addComponent(secondaryPanel);

        window.setComponent(mainPanel.withBorder(Borders.singleLine("Main Panel")));

        screen.refresh();

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));

        gui.addWindow(window);

        screen.refresh();

        boolean running = true;
        while (running) {
            gui.updateScreen();
            KeyStroke keyStroke = screen.pollInput();

            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case Escape:
                        running = false;
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
                            int[] interacted = interaction(textGUI);
                            if (interacted[0] == 0) {
                                running = false;
                            }
                        }
                        break;
                }
                viewPanel.removeAllComponents();
                Map = update();
                for (Label label : Map) {
                    viewPanel.addComponent(label);
                }
            }
        }

        screen.stopScreen();
        return running;
    }

    public ArrayList<Label> update() throws IOException, InterruptedException {
        ArrayList<Label> Map = new ArrayList<>();
        if (!validMove()) {
            person.setPosX(prevX);
            person.setPosY(prevY);
            update();
        }
        for (int i = 0; i < classLayout.length; i++) {
            StringBuilder rowString = new StringBuilder();
            for (int j = 0; j < classLayout[i].length; j++) {
                if (i == person.getPosY() && j == person.getPosX()) {
                    rowString.append('*').append(" ");
                } else {
                    rowString.append(classLayout[i][j]).append(" ");
                }
            }
            Map.add(new Label(rowString.toString()));
        }

        return Map;
    }

    public boolean validMove() {
        return  (interactableObjectsMap[person.getPosY()][person.getPosX()] == 0 ||
                interactableObjectsMap[person.getPosY()][person.getPosX()] == -4 ||
                interactableObjectsMap[person.getPosY()][person.getPosX()] == -5);
    }

    public int[] interaction(WindowBasedTextGUI textGUI) throws IOException, InterruptedException {
        int[] interacted = new int[2];

        if (interactableObjectsMap[person.getPosY() + 1][person.getPosX()] > 0) {
            Item interactedItem = room.getItem((interactableObjectsMap[person.getPosY() + 1][person.getPosX()]) - 1);
            room.getInteraction(interactedItem.getItemID() - 1).dialogInteract(textGUI.getScreen());
            if (room.getInteraction(interactedItem.getItemID() - 1).isTextBasedInteraction()) {
                interacted[0] = 1;
            }
            interacted[1] = interactedItem.getItemID();
        } else if (interactableObjectsMap[person.getPosY() - 1][person.getPosX()] > 0) {
            Item interactedItem = room.getItem((interactableObjectsMap[person.getPosY() - 1][person.getPosX()]) - 1);
            room.getInteraction(interactedItem.getItemID() - 1).dialogInteract(textGUI.getScreen());
            if (room.getInteraction(interactedItem.getItemID() - 1).isTextBasedInteraction()) {
                interacted[0] = 1;
            }
            interacted[1] = interactedItem.getItemID();
        } else if (interactableObjectsMap[person.getPosY()][person.getPosX() + 1] > 0) {
            Item interactedItem = room.getItem((interactableObjectsMap[person.getPosY()][person.getPosX() + 1]) - 1);
            room.getInteraction(interactedItem.getItemID() - 1).dialogInteract(textGUI.getScreen());
            if (room.getInteraction(interactedItem.getItemID() - 1).isTextBasedInteraction()) {
                interacted[0] = 1;
            }
            interacted[1] = interactedItem.getItemID();
        } else if (interactableObjectsMap[person.getPosY()][person.getPosX() - 1] > 0) {
            Item interactedItem = room.getItem((interactableObjectsMap[person.getPosY()][person.getPosX() - 1]) - 1);
            room.getInteraction(interactedItem.getItemID() - 1).dialogInteract(textGUI.getScreen());
            if (room.getInteraction(interactedItem.getItemID() - 1).isTextBasedInteraction()) {
                interacted[0] = 1;
            }
            interacted[1] = interactedItem.getItemID();
        } else {
            interacted[0] = 1;
        }

        return interacted;
    }

    public static void doNothing() {
    }
}
