
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
public class GUICreateBillsData
{
    private final ObjectProperty<LocalDate> date;
    private final StringProperty location;
    private final IntegerProperty paymentDeadlineInDays;
    
    private final ObservableList<GUITemplate> templates;
    private final ObjectProperty<GUITemplate> selectedTemplate;
    
    private final ObservableList<GUIFinancialYear> years;
    private final ObjectProperty<GUIFinancialYear> selectedYear;
    
    private final ObservableList<GUIPerson> persons;

    public GUICreateBillsData(List<GUITemplate> templates, List<GUIFinancialYear> years, List<GUIPerson> persons) {
        date = new SimpleObjectProperty();
        location = new SimpleStringProperty();
        paymentDeadlineInDays = new SimpleIntegerProperty();
        
        this.templates = FXCollections.observableArrayList(templates);
        this.selectedTemplate = new SimpleObjectProperty<>();
        
        this.years = FXCollections.observableArrayList(years);
        this.selectedYear = new SimpleObjectProperty<>();
        
        this.persons = FXCollections.observableArrayList(persons);
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

    public ObservableList<GUITemplate> getTemplates() {
        return templates;
    }

    public ObservableList<GUIPerson> getPersons() {
        return persons;
    }

    public ObjectProperty<GUITemplate> getSelectedTemplate() {
        return selectedTemplate;
    }

    public ObservableList<GUIFinancialYear> getYears() {
        return years;
    }

    public ObjectProperty<GUIFinancialYear> getSelectedYear() {
        return selectedYear;
    }
    
    
}
