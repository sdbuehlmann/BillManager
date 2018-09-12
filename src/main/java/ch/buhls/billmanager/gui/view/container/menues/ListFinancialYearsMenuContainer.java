
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author simon
 */
public class ListFinancialYearsMenuContainer
{
    private final ContextMenu contextMenu;
    private final MenuItem itemNew, itemEdit;
    private final MenuItem itemMark;
    
    public ListFinancialYearsMenuContainer() {
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        
        itemMark = new MenuItem(GUIStringCollection.MARK);
        
        contextMenu = new ContextMenu(itemNew, itemEdit, new SeparatorMenuItem(), itemMark);
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

    public MenuItem getItemMark() {
        return itemMark;
    }
    
    
}
