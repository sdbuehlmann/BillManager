package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIBill.GUIBillStatus;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;

/**
 *
 * @author simon
 */
public class BillsTableContainer extends ATableContainer<GUIBill>
{

    private final TableColumn<GUIBill, Number> dbIDColumn;
    private final TableColumn<GUIBill, Number> dbVersionColumn;

    private final TableColumn<GUIBill, Number> idColumn;
    
    private final TableColumn<GUIBill, GUIBillStatus> statusColumn;
    private final TableColumn<GUIBill, String> yearColumn;
    private final TableColumn<GUIBill, String> nameColumn;
    private final TableColumn<GUIBill, String> prenameColumn;
    private final TableColumn<GUIBill, LocalDate> sendetDateColumn;
    private final TableColumn<GUIBill, LocalDate> closedDateColumn;
    private final TableColumn<GUIBill, Number> sumColumn;
    private final TableColumn<GUIBill, Number> nrPositionsColumn;

    public BillsTableContainer() {
        dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
        dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        addColumn(dbIDColumn);
        getTechnicalColumns().add(dbIDColumn);

        dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
        dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
        addColumn(dbVersionColumn);
        getTechnicalColumns().add(dbVersionColumn);
        
        idColumn = new TableColumn(GUIStringCollection.ID);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        addColumn(idColumn);

        statusColumn = new TableColumn(GUIStringCollection.BILL_STATUS);
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getState());
        addColumn(statusColumn);

        yearColumn = new TableColumn(GUIStringCollection.YEAR_NAME);
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().getYear().getName());
        addColumn(yearColumn);

        nameColumn = new TableColumn(GUIStringCollection.NAME);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getPerson().getName());
        addColumn(nameColumn);

        prenameColumn = new TableColumn(GUIStringCollection.PRENAME);
        prenameColumn.setCellValueFactory(cellData -> cellData.getValue().getPerson().getPrename());
        addColumn(prenameColumn);

        sendetDateColumn = TablesUtils.createDateColumn(
                (TableColumn.CellDataFeatures<GUIBill, LocalDate> cellData) -> {
                    return cellData.getValue().getSendetDate();
                }, GUIStringCollection.BILL_DATE_SENDET);
        addColumn(sendetDateColumn);

        closedDateColumn = TablesUtils.createDateColumn(
                (TableColumn.CellDataFeatures<GUIBill, LocalDate> cellData) -> {
                    return cellData.getValue().getClosedDate();
                }, GUIStringCollection.BILL_DATE_CLOSED);
        addColumn(closedDateColumn);

        sumColumn = new TableColumn(GUIStringCollection.BILL_SUM_RP);
        sumColumn.setCellValueFactory(cellData -> cellData.getValue().getSumInRp());
        addColumn(sumColumn);

        nrPositionsColumn = new TableColumn(GUIStringCollection.BILL_NR_POSITIONS);
        nrPositionsColumn.setCellValueFactory(cellData -> cellData.getValue().getNrPositions());
        addColumn(nrPositionsColumn);
    }

    public TableColumn<GUIBill, Number> getDbIDColumn() {
        return dbIDColumn;
    }

    public TableColumn<GUIBill, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<GUIBill, GUIBillStatus> getStatusColumn() {
        return statusColumn;
    }

    public TableColumn<GUIBill, String> getYearColumn() {
        return yearColumn;
    }

    public TableColumn<GUIBill, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<GUIBill, String> getPrenameColumn() {
        return prenameColumn;
    }

    public TableColumn<GUIBill, LocalDate> getSendetDateColumn() {
        return sendetDateColumn;
    }

    public TableColumn<GUIBill, LocalDate> getClosedDateColumn() {
        return closedDateColumn;
    }

    public TableColumn<GUIBill, Number> getSumColumn() {
        return sumColumn;
    }

    public TableColumn<GUIBill, Number> getNrPositionsColumn() {
        return nrPositionsColumn;
    }
    
    
}
