
package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
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
    
    private final Menu menuFilter, menuStatusFilter;
    private final MenuItem itemStatusSendet, itemStatusCanceled, itemStatusPaid;

    public ListBillsMenuContainer() {        
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        
        itemShowPDF = new MenuItem(GUIStringCollection.SHOW_PDF);
        itemPrint = new MenuItem(GUIStringCollection.PRINT_PDF);
        
        itemStatusSendet = new MenuItem(GUIStringCollection.BILL_STATUS_SENDET);
        itemStatusCanceled = new MenuItem(GUIStringCollection.BILL_STATUS_STORNO);
        itemStatusPaid = new MenuItem(GUIStringCollection.BILL_STATUS_PAID);
        menuStatusFilter = new Menu(GUIStringCollection.BILL_STATUS_FILTER, null, itemStatusSendet, itemStatusPaid, itemStatusCanceled);
        menuFilter = new Menu(GUIStringCollection.FILTER, null, menuStatusFilter);
        
        
        
        contextMenu = new ContextMenu(itemEdit, new SeparatorMenuItem(), itemShowPDF, itemPrint, new SeparatorMenuItem(), menuFilter);
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

    public Menu getMenuFilter() {
        return menuFilter;
    }

    public Menu getMenuStatusFilter() {
        return menuStatusFilter;
    }

    public MenuItem getItemStatusSendet() {
        return itemStatusSendet;
    }

    public MenuItem getItemStatusCanceled() {
        return itemStatusCanceled;
    }

    public MenuItem getItemStatusPaid() {
        return itemStatusPaid;
    }
    
    
}
