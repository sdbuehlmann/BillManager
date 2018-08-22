
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.container.table.FinancialYearTableContainer;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ListFinancialYearsFormContainer
{
    private final VBox view;
    
    private final FinancialYearTableContainer tableContainer;
    
    // menu
    private final ContextMenu contextMenu;
    private final MenuItem itemNew, itemEdit;

    public ListFinancialYearsFormContainer() {
        tableContainer = new FinancialYearTableContainer();
        
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        contextMenu = new ContextMenu(itemNew, itemEdit);
        
        tableContainer.getTable().setContextMenu(contextMenu);
        
        view = new VBox(tableContainer.getTable());
        view.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
    }

    public FinancialYearTableContainer getTableContainer() {
        return tableContainer;
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

    public VBox getView() {
        return view;
    }

}
