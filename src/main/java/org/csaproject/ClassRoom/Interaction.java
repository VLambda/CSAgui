package org.csaproject.ClassRoom;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;

public class Interaction {
    private boolean isTextBasedInteraction;
    private Item item;

    public Interaction(Item item, boolean isTextBasedInteraction) {
        this.item = item;
        this.isTextBasedInteraction = isTextBasedInteraction;
    }

    public boolean dialogInteract(Screen screen) {
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

        if (isTextBasedInteraction) {
            MessageDialog.showMessageDialog(textGUI, item.getItemName(), item.getItemDescription());
            return true;
        }

        return false;
    }

    public boolean isTextBasedInteraction() {
        return isTextBasedInteraction;
    }

    public void interact(Screen screen) {
        if (isTextBasedInteraction) {
            dialogInteract(screen);
        }
    }
}
