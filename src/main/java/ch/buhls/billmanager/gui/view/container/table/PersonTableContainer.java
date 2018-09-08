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

    private final TableColumn<GUIPerson, Number> dbIDColumn;
    private final TableColumn<GUIPerson, Number> dbVersionColumn;
    private final TableColumn<GUIPerson, Number> baseDataDbIDColumn;
    private final TableColumn<GUIPerson, Number> baseDataDbVersionColumn;
    
    private final TableColumn<GUIPerson, Number> personalIDColumn;
    private final TableColumn<GUIPerson, String> nameColumn;
    private final TableColumn<GUIPerson, String> prenameColumn;
    private final TableColumn<GUIPerson, String> streetColumn;
    private final TableColumn<GUIPerson, Number> postalCodeColumn;
    private final TableColumn<GUIPerson, String> cityColumn;
    private final TableColumn<GUIPerson, LocalDate> birthdayColumn;
    private final TableColumn<GUIPerson, String> phonePColumn;
    private final TableColumn<GUIPerson, String> phoneMColumn;
    private final TableColumn<GUIPerson, String> salutationColumn;
    private final TableColumn<GUIPerson, String> titleColumn;
    
    private final TableColumn<GUIPerson, Number> nrArtInBusketColumn;
    private final TableColumn<GUIPerson, Number> nrRolesColumn;
    private final TableColumn<GUIPerson, Number> nrBillsColumn;
    
    public PersonTableContainer()
    {
        table = new TableView<>();

            dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
            dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(dbIDColumn);

            dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
            dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(dbVersionColumn);

            baseDataDbIDColumn = new TableColumn(GUIStringCollection.PERSON_BASE_DATA_DB_ID);
            baseDataDbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getDb_id());
            table.getColumns().add(baseDataDbIDColumn);

            baseDataDbVersionColumn = new TableColumn(GUIStringCollection.PERSON_BASE_DATA_DB_VERSION);
            baseDataDbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getDb_version());
            table.getColumns().add(baseDataDbVersionColumn);

            personalIDColumn = new TableColumn(GUIStringCollection.PERSONAL_ID);
            personalIDColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPersonalId());
            table.getColumns().add(personalIDColumn);
            
            nameColumn = new TableColumn(GUIStringCollection.NAME);
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getName());
            table.getColumns().add(nameColumn);
        
            prenameColumn = new TableColumn(GUIStringCollection.PRENAME);
            prenameColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPrename());
            table.getColumns().add(prenameColumn);
        
            streetColumn = new TableColumn(GUIStringCollection.STREET);
            streetColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getStreet());
            table.getColumns().add(streetColumn);
        
            postalCodeColumn = new TableColumn(GUIStringCollection.POSTAL_CODE);
            postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPostalcode());
            table.getColumns().add(postalCodeColumn);
            
            cityColumn = new TableColumn(GUIStringCollection.CITY);
            cityColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getCity());
            table.getColumns().add(cityColumn);

            birthdayColumn = TablesUtils.createDateColumn(
                    (TableColumn.CellDataFeatures<GUIPerson, LocalDate> cellData) -> {
                        return cellData.getValue().getBaseData().getDateAdded();
                    }, GUIStringCollection.BIRTHDAY);
            table.getColumns().add(birthdayColumn);

            phonePColumn = new TableColumn(GUIStringCollection.PHONE_P);
            phonePColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPhoneP());
            table.getColumns().add(phonePColumn);
        
            phoneMColumn = new TableColumn(GUIStringCollection.PHONE_M);
            phoneMColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPhoneM());
            table.getColumns().add(phoneMColumn);

            salutationColumn = new TableColumn(GUIStringCollection.SALUTATION);
            salutationColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getSalutation());
            table.getColumns().add(salutationColumn);

            titleColumn = new TableColumn(GUIStringCollection.TITLE);
            titleColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getTitle());
            table.getColumns().add(titleColumn);

            
            nrArtInBusketColumn = new TableColumn(GUIStringCollection.PERSON_NR_OF_ART);
            nrArtInBusketColumn.setCellValueFactory(cellData -> cellData.getValue().getNrOfArtInBusket());
            table.getColumns().add(nrArtInBusketColumn);

            nrBillsColumn = new TableColumn(GUIStringCollection.PERSON_NR_OF_BILLS);
            nrBillsColumn.setCellValueFactory(cellData -> cellData.getValue().getNrOfBills());
            table.getColumns().add(nrBillsColumn);

            nrRolesColumn = new TableColumn(GUIStringCollection.PERSON_NR_OF_ROLES);
            nrRolesColumn.setCellValueFactory(cellData -> cellData.getValue().getNrOfRoles());
            table.getColumns().add(nrRolesColumn);
    }

    public TableView<GUIPerson> getTable()
    {
        return table;
    }

    public TableColumn<GUIPerson, Number> getDbIDColumn() {
        return dbIDColumn;
    }

    public TableColumn<GUIPerson, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<GUIPerson, Number> getBaseDataDbIDColumn() {
        return baseDataDbIDColumn;
    }

    public TableColumn<GUIPerson, Number> getBaseDataDbVersionColumn() {
        return baseDataDbVersionColumn;
    }

    public TableColumn<GUIPerson, Number> getPersonalIDColumn() {
        return personalIDColumn;
    }

    public TableColumn<GUIPerson, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<GUIPerson, String> getPrenameColumn() {
        return prenameColumn;
    }

    public TableColumn<GUIPerson, String> getStreetColumn() {
        return streetColumn;
    }

    public TableColumn<GUIPerson, Number> getPostalCodeColumn() {
        return postalCodeColumn;
    }

    public TableColumn<GUIPerson, String> getCityColumn() {
        return cityColumn;
    }

    public TableColumn<GUIPerson, LocalDate> getBirthdayColumn() {
        return birthdayColumn;
    }

    public TableColumn<GUIPerson, String> getPhonePColumn() {
        return phonePColumn;
    }

    public TableColumn<GUIPerson, String> getPhoneMColumn() {
        return phoneMColumn;
    }

    public TableColumn<GUIPerson, String> getSalutationColumn() {
        return salutationColumn;
    }

    public TableColumn<GUIPerson, String> getTitleColumn() {
        return titleColumn;
    }

    public TableColumn<GUIPerson, Number> getNrArtInBusketColumn() {
        return nrArtInBusketColumn;
    }

    public TableColumn<GUIPerson, Number> getNrRolesColumn() {
        return nrRolesColumn;
    }

    public TableColumn<GUIPerson, Number> getNrBillsColumn() {
        return nrBillsColumn;
    }
    
    
}
