package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.AGUITrackedData;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class VersionsTableContainer<T extends AGUITrackedData>
{
    private final TableView<T> table;

    public VersionsTableContainer() {
        table = new TableView<>();

        {
            TableColumn<T, Number> column = new TableColumn(GUIStringCollection.TRACKED_ENRITY_VERSION_NR);
            column.setCellValueFactory(cellData -> cellData.getValue().getVersionNr());
            table.getColumns().add(column);
        }
        {
            TableColumn<T, String> column = new TableColumn(GUIStringCollection.TRACKED_ENRITY_CHANGE_TXT);
            column.setCellValueFactory(cellData -> cellData.getValue().getChangeTxt());
            table.getColumns().add(column);
        }
        {
            TableColumn<T, LocalDate> column = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<T, LocalDate> cellData) -> {
                        return cellData.getValue().getDateAdded();
                    }, GUIStringCollection.TRACKED_ENRITY_DATE);
            table.getColumns().add(column);
        }
    }

    public TableView<T> getTable() {
        return table;
    }
}
