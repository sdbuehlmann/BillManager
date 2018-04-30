
package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIRole;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class RoleTableContainer
{
    private final TableView<GUIRole> table;

    public RoleTableContainer() {
        table = new TableView<>();

        {
            TableColumn<GUIRole, Number> column = new TableColumn(GUIStringCollection.DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIRole, Number> column = new TableColumn(GUIStringCollection.DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIRole, String> column = new TableColumn(GUIStringCollection.ROLE_NAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getName());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIRole, Boolean> column = TablesUtils.createMarkedColumn((TableColumn.CellDataFeatures<GUIRole, Boolean> cellData) -> {
                return cellData.getValue().getMarked();
            });
            table.getColumns().add(column);
        }
    }

    public TableView<GUIRole> getTable() {
        return table;
    }
}
