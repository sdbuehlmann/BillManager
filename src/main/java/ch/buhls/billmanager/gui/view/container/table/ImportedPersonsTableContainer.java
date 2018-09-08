
package ch.buhls.billmanager.gui.view.container.table;


import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIImportedPerson;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class ImportedPersonsTableContainer
{
    private final TableView<GUIImportedPerson> table;
    
    private final TableColumn<GUIImportedPerson, String> colName;
    private final TableColumn<GUIImportedPerson, String> colPrename;
    private final TableColumn<GUIImportedPerson, String> colStreet;
    private final TableColumn<GUIImportedPerson, Number> colPlz;
    private final TableColumn<GUIImportedPerson, String> colCity;
    
    private final TableColumn<GUIImportedPerson, String> colPhoneP;
    private final TableColumn<GUIImportedPerson, String> colPhoneM;
    private final TableColumn<GUIImportedPerson, String> colEmail;
    
    private final TableColumn<GUIImportedPerson, Number> colBirthday;
    private final TableColumn<GUIImportedPerson, Number> colBirthmonth;
    private final TableColumn<GUIImportedPerson, Number> colBirthyear;
    
    private final TableColumn<GUIImportedPerson, String> colSalutation;
    private final TableColumn<GUIImportedPerson, String> colTitle;

    public ImportedPersonsTableContainer() {
        table = new TableView<>();
        
        colName = new TableColumn(GUIStringCollection.NAME);
        colName.setCellValueFactory(cellData -> cellData.getValue().getName());
        table.getColumns().add(colName);
        
        colPrename = new TableColumn(GUIStringCollection.PRENAME);
        colPrename.setCellValueFactory(cellData -> cellData.getValue().getPrename());
        table.getColumns().add(colPrename);
        
        colStreet = new TableColumn(GUIStringCollection.STREET);
        colStreet.setCellValueFactory(cellData -> cellData.getValue().getStreet());
        table.getColumns().add(colStreet);
        
        colPlz = new TableColumn(GUIStringCollection.POSTAL_CODE);
        colPlz.setCellValueFactory(cellData -> cellData.getValue().getPostalcode());
        table.getColumns().add(colPlz);
        
        colCity = new TableColumn(GUIStringCollection.CITY);
        colCity.setCellValueFactory(cellData -> cellData.getValue().getCity());
        table.getColumns().add(colCity);
        
        colPhoneP = new TableColumn(GUIStringCollection.PHONE_P);
        colPhoneP.setCellValueFactory(cellData -> cellData.getValue().getPhoneP());
        table.getColumns().add(colPhoneP);
        
        colPhoneM = new TableColumn(GUIStringCollection.PHONE_M);
        colPhoneM.setCellValueFactory(cellData -> cellData.getValue().getPhoneM());
        table.getColumns().add(colPhoneM);
        
        colEmail = new TableColumn(GUIStringCollection.E_MAIL);
        colEmail.setCellValueFactory(cellData -> cellData.getValue().getMail());
        table.getColumns().add(colEmail);
        
        colBirthday = new TableColumn(GUIStringCollection.BIRTHDAY);
        colBirthday.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
        table.getColumns().add(colBirthday);
        
        colBirthmonth = new TableColumn(GUIStringCollection.BIRTHMONTH);
        colBirthmonth.setCellValueFactory(cellData -> cellData.getValue().getBirthmonth());
        table.getColumns().add(colBirthmonth);
        
        colBirthyear = new TableColumn(GUIStringCollection.BIRTHYEAR);
        colBirthyear.setCellValueFactory(cellData -> cellData.getValue().getBirthyear());
        table.getColumns().add(colBirthyear);
        
        colSalutation = new TableColumn(GUIStringCollection.SALUTATION);
        colSalutation.setCellValueFactory(cellData -> cellData.getValue().getSalutation());
        table.getColumns().add(colSalutation);
        
        colTitle = new TableColumn(GUIStringCollection.TITLE);
        colTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        table.getColumns().add(colTitle);
    }

    public TableView<GUIImportedPerson> getTable() {
        return table;
    }

    public TableColumn<GUIImportedPerson, String> getColName() {
        return colName;
    }

    public TableColumn<GUIImportedPerson, String> getColPrename() {
        return colPrename;
    }

    public TableColumn<GUIImportedPerson, String> getColStreet() {
        return colStreet;
    }

    public TableColumn<GUIImportedPerson, Number> getColPlz() {
        return colPlz;
    }

    public TableColumn<GUIImportedPerson, String> getColCity() {
        return colCity;
    }

    public TableColumn<GUIImportedPerson, String> getColPhoneP() {
        return colPhoneP;
    }

    public TableColumn<GUIImportedPerson, String> getColPhoneM() {
        return colPhoneM;
    }

    public TableColumn<GUIImportedPerson, String> getColEmail() {
        return colEmail;
    }

    public TableColumn<GUIImportedPerson, Number> getColBirthday() {
        return colBirthday;
    }

    public TableColumn<GUIImportedPerson, Number> getColBirthmonth() {
        return colBirthmonth;
    }

    public TableColumn<GUIImportedPerson, Number> getColBirthyear() {
        return colBirthyear;
    }

    public TableColumn<GUIImportedPerson, String> getColSalutation() {
        return colSalutation;
    }

    public TableColumn<GUIImportedPerson, String> getColTitle() {
        return colTitle;
    }
    
    
    
}
