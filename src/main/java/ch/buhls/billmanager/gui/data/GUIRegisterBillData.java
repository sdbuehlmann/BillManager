
package ch.buhls.billmanager.gui.data;

import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class GUIRegisterBillData
{
    private final ObjectProperty<LocalDate> date;
    private final StringProperty location;
    private final IntegerProperty paymentDeadlineInDays;
    
    private final StringProperty file;
    
    private final ObservableList<GUIFinancialYear> years;
    private final ObjectProperty<GUIFinancialYear> selectedYear;
    
    private final ObjectProperty<GUIPerson> person;

    public GUIRegisterBillData(List<GUIFinancialYear> years, GUIPerson person) {
        date = new SimpleObjectProperty();
        location = new SimpleStringProperty();
        paymentDeadlineInDays = new SimpleIntegerProperty();
        
        file = new SimpleStringProperty();
        
        this.years = FXCollections.observableArrayList(years);
        this.selectedYear = new SimpleObjectProperty<>();
        
        this.person = new SimpleObjectProperty<>(person);
    }

    public ObjectProperty<LocalDate> getDate() {
        return date;
    }

    public StringProperty getLocation() {
        return location;
    }

    public IntegerProperty getPaymentDeadlineInDays() {
        return paymentDeadlineInDays;
    }

    public StringProperty getFile() {
        return file;
    }

    public ObservableList<GUIFinancialYear> getYears() {
        return years;
    }

    public ObjectProperty<GUIFinancialYear> getSelectedYear() {
        return selectedYear;
    }

    public ObjectProperty<GUIPerson> getPerson() {
        return person;
    }
    
    

}
