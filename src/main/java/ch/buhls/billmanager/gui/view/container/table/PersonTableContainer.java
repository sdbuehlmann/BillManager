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
public class PersonTableContainer extends ATableContainer<GUIPerson>
{

    private final TableColumn<GUIPerson, Number> dbIDColumn;
    private final TableColumn<GUIPerson, Number> dbVersionColumn;
    private final TableColumn<GUIPerson, Number> baseDataDbIDColumn;
    private final TableColumn<GUIPerson, Number> baseDataDbVersionColumn;

    private final TableColumn<GUIPerson, Number> idColumn;
    private final TableColumn<GUIPerson, Number> versionColumn;

    private final TableColumn<GUIPerson, String> nameColumn;
    private final TableColumn<GUIPerson, String> prenameColumn;
    private final TableColumn<GUIPerson, String> companyColumn;
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

    public PersonTableContainer() {
        super();

        dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
        dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        this.getTechnicalColumns().add(dbIDColumn);

        dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
        dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
        this.getTechnicalColumns().add(dbVersionColumn);

        baseDataDbIDColumn = new TableColumn(GUIStringCollection.PERSON_BASE_DATA_DB_ID);
        baseDataDbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getDb_id());
        this.getTechnicalColumns().add(baseDataDbIDColumn);

        baseDataDbVersionColumn = new TableColumn(GUIStringCollection.PERSON_BASE_DATA_DB_VERSION);
        baseDataDbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getDb_version());
        this.getTechnicalColumns().add(baseDataDbVersionColumn);

        idColumn = new TableColumn(GUIStringCollection.ID);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());

        versionColumn = new TableColumn(GUIStringCollection.TRACKED_ENRITY_VERSION_NR);
        versionColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getVersionNr());

        nameColumn = new TableColumn(GUIStringCollection.NAME);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getName());

        companyColumn = new TableColumn(GUIStringCollection.COMPANY);
        companyColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getCompany());
        
        prenameColumn = new TableColumn(GUIStringCollection.PRENAME);
        prenameColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPrename());

        streetColumn = new TableColumn(GUIStringCollection.STREET);
        streetColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getStreet());

        postalCodeColumn = new TableColumn(GUIStringCollection.POSTAL_CODE);
        postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPostalcode());

        cityColumn = new TableColumn(GUIStringCollection.CITY);
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getCity());

        birthdayColumn = TablesUtils.createDateColumn(
                (TableColumn.CellDataFeatures<GUIPerson, LocalDate> cellData) -> {
                    return cellData.getValue().getBaseData().getBirthday();
                }, GUIStringCollection.BIRTHDAY);

        phonePColumn = new TableColumn(GUIStringCollection.PHONE_P);
        phonePColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPhoneP());

        phoneMColumn = new TableColumn(GUIStringCollection.PHONE_M);
        phoneMColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getPhoneM());

        salutationColumn = new TableColumn(GUIStringCollection.SALUTATION);
        salutationColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getSalutation());

        titleColumn = new TableColumn(GUIStringCollection.TITLE);
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getBaseData().getTitle());

        nrArtInBusketColumn = new TableColumn(GUIStringCollection.PERSON_NR_OF_ART);
        nrArtInBusketColumn.setCellValueFactory(cellData -> cellData.getValue().getNrOfArtInBusket());

        nrBillsColumn = new TableColumn(GUIStringCollection.PERSON_NR_OF_BILLS);
        nrBillsColumn.setCellValueFactory(cellData -> cellData.getValue().getNrOfBills());

        nrRolesColumn = new TableColumn(GUIStringCollection.PERSON_NR_OF_ROLES);
        nrRolesColumn.setCellValueFactory(cellData -> cellData.getValue().getNrOfRoles());
        
        addAllColumns();
    }

    public final void addAllColumns() {
        this.addColumn(dbIDColumn);
        this.addColumn(dbVersionColumn);
        this.addColumn(baseDataDbIDColumn);
        this.addColumn(baseDataDbVersionColumn);

        this.addColumn(idColumn);
        this.addColumn(versionColumn);
        
        this.addColumn(nrArtInBusketColumn);
        this.addColumn(nrRolesColumn);
        this.addColumn(nrBillsColumn);

        this.addColumn(nameColumn);
        this.addColumn(prenameColumn);
        this.addColumn(companyColumn);
        this.addColumn(streetColumn);
        this.addColumn(postalCodeColumn);
        this.addColumn(cityColumn);
        this.addColumn(birthdayColumn);
        this.addColumn(phonePColumn);
        this.addColumn(phoneMColumn);
        this.addColumn(salutationColumn);
        this.addColumn(titleColumn);
    }

    // getter
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
