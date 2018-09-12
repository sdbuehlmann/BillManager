
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author simon
 */
public class ListFinancialYearsMenuContainer
{
    private final ContextMenu contextMenu;
    private final MenuItem itemNew, itemEdit;

    public ListFinancialYearsMenuContainer() {
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        contextMenu = new ContextMenu(itemNew, itemEdit);
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
}
