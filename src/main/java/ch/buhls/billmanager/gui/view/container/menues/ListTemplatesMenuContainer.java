
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author simon
 */
public class ListTemplatesMenuContainer
{
    private final ContextMenu contextMenu;
    private final MenuItem itemEdit, itemNew, itemShow;

    public ListTemplatesMenuContainer() {
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        itemShow = new MenuItem(GUIStringCollection.SHOW);
        
        contextMenu = new ContextMenu(itemNew, itemEdit);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getItemEdit() {
        return itemEdit;
    }

    public MenuItem getItemNew() {
        return itemNew;
    }

    public MenuItem getItemShow() {
        return itemShow;
    }
}
