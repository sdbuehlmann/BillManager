
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author simon
 */
public class DefaultMenuContainer
{
    protected final ContextMenu contextMenu;
    protected final MenuItem itemNew, itemEdit, itemDelete, itemShow;

    public DefaultMenuContainer() {
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        itemDelete = new MenuItem(GUIStringCollection.DELETE);
        itemShow = new MenuItem(GUIStringCollection.SHOW);
        
        contextMenu = new ContextMenu(itemNew, itemEdit, itemDelete, itemShow);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getItemNew() {
        return itemNew;
    }

    public MenuItem getItemEdit() {
        return itemEdit;
    }

    public MenuItem getItemDelete() {
        return itemDelete;
    }

    public MenuItem getItemShow() {
        return itemShow;
    }
}
