
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.container.table.ImportedPersonsTableContainer;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ImportPersonsFormContainer
{
    private final VBox view;
        
    private final ToolBar tbManageImported;
    private final Button bReadFile;
    private final Button bImportData;
    private final Tooltip ttFileformat;
    
    private final ImportedPersonsTableContainer tableContainer;
    
    private final ContextMenu contextMenu;
    private final MenuItem miRemove;

    public ImportPersonsFormContainer() {
        bReadFile = new Button(GUIStringCollection.IMPORT_PERS_READ_FILE);
        bImportData = new Button(GUIStringCollection.IMPORT_PERS_STORE_DATA);
        ttFileformat = new Tooltip(GUIStringCollection.IMPORT_PERSONS_FILE_FORMAT);
        bReadFile.setTooltip(ttFileformat);
        tbManageImported = new ToolBar(bReadFile, bImportData);
        
        tableContainer = new ImportedPersonsTableContainer();
        VBox.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        
        miRemove = new MenuItem(GUIStringCollection.DELETE);
        contextMenu = new ContextMenu(miRemove);
        tableContainer.getTable().setContextMenu(contextMenu);
        
        view = new VBox(tbManageImported, tableContainer.getTable());
    }

    public VBox getView() {
        return view;
    }

    public ToolBar getTbManageImported() {
        return tbManageImported;
    }

    public Button getbReadFile() {
        return bReadFile;
    }

    public Button getbImportData() {
        return bImportData;
    }

    public ImportedPersonsTableContainer getTableContainer() {
        return tableContainer;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getMiRemove() {
        return miRemove;
    }
}
