package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.AGUITrackedData;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;

/**
 *
 * @author simon
 * @param <T>
 */
public class VersionsTableContainer<T extends AGUITrackedData> extends ATableContainer<T>
{
    private final TableColumn<T, Number> dbIDColumn;
    private final TableColumn<T, Number> dbVersionColumn;

    private final TableColumn<T, Number> versionColumn;
    private final TableColumn<T, String> changeTxtColumn;
    private final TableColumn<T, LocalDate> dateColumn;

    public VersionsTableContainer() {
        dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
        dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        this.addColumn(dbIDColumn);
        this.getTechnicalColumns().add(dbIDColumn);

        dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
        dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
        this.addColumn(dbVersionColumn);
        this.getTechnicalColumns().add(dbVersionColumn);

        versionColumn = new TableColumn(GUIStringCollection.TRACKED_ENRITY_VERSION_NR);
        versionColumn.setCellValueFactory(cellData -> cellData.getValue().getVersionNr());
        addColumn(versionColumn);

        changeTxtColumn = new TableColumn(GUIStringCollection.TRACKED_ENRITY_CHANGE_TXT);
        changeTxtColumn.setCellValueFactory(cellData -> cellData.getValue().getChangeTxt());
        addColumn(changeTxtColumn);

        dateColumn = TablesUtils.createDateColumn(
                (TableColumn.CellDataFeatures<T, LocalDate> cellData) -> {
                    return cellData.getValue().getDateAdded();
                }, GUIStringCollection.TRACKED_ENRITY_DATE);
        addColumn(dateColumn);
    }

    public TableColumn<T, Number> getDbIDColumn() {
        return dbIDColumn;
    }

    public TableColumn<T, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<T, Number> getVersionColumn() {
        return versionColumn;
    }

    public TableColumn<T, String> getChangeTxtColumn() {
        return changeTxtColumn;
    }

    public TableColumn<T, LocalDate> getDateColumn() {
        return dateColumn;
    }

    
}
