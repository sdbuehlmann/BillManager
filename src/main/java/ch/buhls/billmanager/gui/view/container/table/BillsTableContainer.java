
package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIBill.GUIBillStatus;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class BillsTableContainer
{
    private final TableView<GUIBill> table;

    public BillsTableContainer() {
        table = new TableView<>();

        {
            TableColumn<GUIBill, Number> column = new TableColumn(GUIStringCollection.DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, Number> column = new TableColumn(GUIStringCollection.DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(column);
        }
        
        {
            TableColumn<GUIBill, GUIBillStatus> column = new TableColumn(GUIStringCollection.BILL_STATUS);
            column.setCellValueFactory(cellData -> cellData.getValue().getState());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, String> column = new TableColumn(GUIStringCollection.YEAR_NAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getYear().getName());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, String> column = new TableColumn(GUIStringCollection.NAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getPerson().getName());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, String> column = new TableColumn(GUIStringCollection.PRENAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getPerson().getPrename());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, LocalDate> column = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<GUIBill, LocalDate> cellData) -> {
                        return cellData.getValue().getSendetDate();
                    }, GUIStringCollection.BILL_DATE_SENDET);
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, LocalDate> column = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<GUIBill, LocalDate> cellData) -> {
                        return cellData.getValue().getClosedDate();
                    }, GUIStringCollection.BILL_DATE_CLOSED);
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, Number> column = new TableColumn(GUIStringCollection.BILL_SUM_RP);
            column.setCellValueFactory(cellData -> cellData.getValue().getSumInRp());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIBill, Number> column = new TableColumn(GUIStringCollection.BILL_NR_POSITIONS);
            column.setCellValueFactory(cellData -> cellData.getValue().getNrPositions());
            table.getColumns().add(column);
        }
    }

    public TableView<GUIBill> getTable() {
        return table;
    }
}
