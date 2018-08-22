
package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class FinancialYearTableContainer
{
    private final TableView<GUIFinancialYear> table;

    public FinancialYearTableContainer() {
        table = new TableView<>();

        {
            TableColumn<GUIFinancialYear, Number> column = new TableColumn(GUIStringCollection.DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIFinancialYear, Number> column = new TableColumn(GUIStringCollection.DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIFinancialYear, String> column = new TableColumn(GUIStringCollection.YEAR_NAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getName());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIFinancialYear, String> column = new TableColumn(GUIStringCollection.YEAR_PREFIX);
            column.setCellValueFactory(cellData -> cellData.getValue().getPrefix());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIFinancialYear, LocalDate> column = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<GUIFinancialYear, LocalDate> cellData) -> {
                        return cellData.getValue().getFirstDay();
                    }, GUIStringCollection.YEAR_FIRST_DAY);
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIFinancialYear, LocalDate> column = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<GUIFinancialYear, LocalDate> cellData) -> {
                        return cellData.getValue().getLastDay();
                    }, GUIStringCollection.YEAR_LAST_DAY);
            table.getColumns().add(column);
        }
    }

    public TableView<GUIFinancialYear> getTable() {
        return table;
    }
}
