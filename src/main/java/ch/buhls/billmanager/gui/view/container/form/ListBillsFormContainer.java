
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.container.table.BillsTableContainer;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ListBillsFormContainer
{
    private final VBox view;
    
    private final BillsTableContainer tableContainer;
    
    // menu
    private final ContextMenu contextMenu;
    private final MenuItem itemEdit, itemShowPDF, itemPrint;

    public ListBillsFormContainer() {
        tableContainer = new BillsTableContainer();
        
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        itemShowPDF = new MenuItem(GUIStringCollection.SHOW_PDF);
        itemPrint = new MenuItem(GUIStringCollection.PRINT_PDF);
        contextMenu = new ContextMenu(itemEdit, new SeparatorMenuItem(), itemShowPDF, itemPrint);
        
        tableContainer.getTable().setContextMenu(contextMenu);
        
        view = new VBox(tableContainer.getTable());
        view.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
    }

    public VBox getView() {
        return view;
    }

    public BillsTableContainer getTableContainer() {
        return tableContainer;
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
