package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIPerson;
import java.time.LocalDate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class PersonTableContainer
{

    private final TableView<GUIPerson> table;

    public PersonTableContainer()
    {
        table = new TableView<>();

        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.PERSON_BASE_DATA_DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.PERSON_BASE_DATA_DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getDb_version());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.PERSONAL_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPersonalId());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.NAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getName());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.PRENAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPrename());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.STREET);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getStreet());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.POSTAL_CODE);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPostalcode());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.CITY);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getCity());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, LocalDate> column = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<GUIPerson, LocalDate> cellData) -> {
                        return cellData.getValue().getBaseData().getDateAdded();
                    }, GUIStringCollection.BIRTHDAY);
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.PHONE_P);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPhoneP());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.PHONE_M);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPhoneM());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.SALUTATION);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getSalutation());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, String> column = new TableColumn(GUIStringCollection.TITLE);
            column.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getTitle());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.PERSON_NR_OF_ART);
            column.setCellValueFactory(cellData -> cellData.getValue().getNrOfArtInBusket());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.PERSON_NR_OF_BILLS);
            column.setCellValueFactory(cellData -> cellData.getValue().getNrOfBills());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPerson, Number> column = new TableColumn(GUIStringCollection.PERSON_NR_OF_ROLES);
            column.setCellValueFactory(cellData -> cellData.getValue().getNrOfRoles());
            table.getColumns().add(column);
        }
    }

    public TableView<GUIPerson> getTable()
    {
        return table;
    }
    
}
