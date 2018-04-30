
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author simon
 */
public class ListVersionsMenuContainer
{
    private ContextMenu contextMenu;
    private MenuItem itemShow;

    public ListVersionsMenuContainer() {
        itemShow = new MenuItem(GUIStringCollection.SHOW);
        
        contextMenu = new ContextMenu(itemShow);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getItemShow() {
        return itemShow;
    }
    
    
}
