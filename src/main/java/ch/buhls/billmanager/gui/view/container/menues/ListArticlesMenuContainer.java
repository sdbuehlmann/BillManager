
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author simon
 */
public class ListArticlesMenuContainer
{
    private ContextMenu contextMenu;
    private MenuItem itemEdit, itemNew, itemMark, itemShowVersions;

    public ListArticlesMenuContainer() {
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        itemMark = new MenuItem(GUIStringCollection.MARK);
        itemShowVersions = new MenuItem(GUIStringCollection.SHOW_VERSIONS);
        
        contextMenu = new ContextMenu(itemNew, itemEdit, new SeparatorMenuItem(), itemMark, new SeparatorMenuItem(), itemShowVersions);
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

    public MenuItem getItemMark() {
        return itemMark;
    }

    public MenuItem getItemShowVersions() {
        return itemShowVersions;
    }
    
    
}
