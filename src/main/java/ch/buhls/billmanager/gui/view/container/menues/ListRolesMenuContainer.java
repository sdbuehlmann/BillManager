
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author simon
 */
public class ListRolesMenuContainer extends DefaultMenuContainer
{
    private final MenuItem itemMark; 
    
    public ListRolesMenuContainer() {
        itemMark = new MenuItem(GUIStringCollection.MARK);
        contextMenu.getItems().add(new SeparatorMenuItem());
        contextMenu.getItems().add(itemMark);
    }

    public MenuItem getItemMark() {
        return itemMark;
    }
}
