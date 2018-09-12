
package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIRole;
import javafx.scene.control.TableColumn;

/**
 *
 * @author simon
 */
public class RoleTableContainer extends ATableContainer<GUIRole>
{
    private final TableColumn<GUIRole, Number> dbIDColumn;
    private final TableColumn<GUIRole, Number> dbVersionColumn;
    
    private final TableColumn<GUIRole, String> nameColumn;

    public RoleTableContainer() {

            dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
            dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            addColumn(dbIDColumn);
            getTechnicalColumns().add(dbIDColumn);
        
            dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
            dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            addColumn(dbVersionColumn);
            getTechnicalColumns().add(dbVersionColumn);
        
            nameColumn = new TableColumn(GUIStringCollection.ROLE_NAME);
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
            addColumn(nameColumn);
    }

    public TableColumn<GUIRole, Number> getDbIDColumn() {
        return dbIDColumn;
    }

    public TableColumn<GUIRole, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<GUIRole, String> getNameColumn() {
        return nameColumn;
    }

}
