
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author simon
 */
public class ListBillsMenuContainer
{
    private final ContextMenu contextMenu;
    private final MenuItem itemEdit, itemShowPDF, itemPrint;

    public ListBillsMenuContainer() {        
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        itemShowPDF = new MenuItem(GUIStringCollection.SHOW_PDF);
        itemPrint = new MenuItem(GUIStringCollection.PRINT_PDF);
        contextMenu = new ContextMenu(itemEdit, new SeparatorMenuItem(), itemShowPDF, itemPrint);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getItemEdit() {
        return itemEdit;
    }

    public MenuItem getItemShowPDF() {
        return itemShowPDF;
    }

    public MenuItem getItemPrint() {
        return itemPrint;
    }
    
    
}
