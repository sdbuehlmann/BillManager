package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;

/**
 *
 * @author simon
 */
public class FinancialYearTableContainer extends ATableContainer<GUIFinancialYear>
{
    private final TableColumn<GUIFinancialYear, Number> dbIDColumn;
    private final TableColumn<GUIFinancialYear, Number> dbVersionColumn;

    private final TableColumn<GUIFinancialYear, String> nameColumn;
    private final TableColumn<GUIFinancialYear, String> prefixColumn;
    private final TableColumn<GUIFinancialYear, LocalDate> firstDayColumn;
    private final TableColumn<GUIFinancialYear, LocalDate> lastDayColumn;

    public FinancialYearTableContainer() {
        dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
        dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        addColumn(dbIDColumn);
        getTechnicalColumns().add(dbIDColumn);

        dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
        dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
        addColumn(dbVersionColumn);
        getTechnicalColumns().add(dbVersionColumn);

        nameColumn = new TableColumn(GUIStringCollection.YEAR_NAME);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        addColumn(nameColumn);

        prefixColumn = new TableColumn(GUIStringCollection.YEAR_PREFIX);
        prefixColumn.setCellValueFactory(cellData -> cellData.getValue().getPrefix());
        addColumn(prefixColumn);

        firstDayColumn = TablesUtils.createDateColumn(
                (TableColumn.CellDataFeatures<GUIFinancialYear, LocalDate> cellData) -> {
                    return cellData.getValue().getFirstDay();
                }, GUIStringCollection.YEAR_FIRST_DAY);
        addColumn(firstDayColumn);

        lastDayColumn = TablesUtils.createDateColumn(
                (TableColumn.CellDataFeatures<GUIFinancialYear, LocalDate> cellData) -> {
                    return cellData.getValue().getLastDay();
                }, GUIStringCollection.YEAR_LAST_DAY);
        addColumn(lastDayColumn);
    }

    public TableColumn<GUIFinancialYear, Number> getDbIDColumn() {
        return dbIDColumn;
    }

    public TableColumn<GUIFinancialYear, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<GUIFinancialYear, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<GUIFinancialYear, String> getPrefixColumn() {
        return prefixColumn;
    }

    public TableColumn<GUIFinancialYear, LocalDate> getFirstDayColumn() {
        return firstDayColumn;
    }

    public TableColumn<GUIFinancialYear, LocalDate> getLastDayColumn() {
        return lastDayColumn;
    }
    
    
}
