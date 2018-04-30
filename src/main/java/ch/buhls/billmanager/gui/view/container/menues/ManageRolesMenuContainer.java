
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

/**
 *
 * @author simon
 */
public class ManageRolesMenuContainer
{
    private final ButtonBar buttonBar;
    
    private final Button buttonAdd;
    private final Button buttonRemove;

    public ManageRolesMenuContainer() {
        buttonAdd = new Button(GUIStringCollection.PERSON_ADD_ROLE);
        buttonRemove = new Button(GUIStringCollection.PERSON_REMOVE_ROLE);
        
        buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(buttonAdd, buttonRemove);
        buttonBar.setPadding(new Insets(10));// TEMP!
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public Button getButtonRemove() {
        return buttonRemove;
    }
}
