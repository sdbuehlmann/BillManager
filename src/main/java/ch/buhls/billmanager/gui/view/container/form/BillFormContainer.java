
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.view.container.table.PersonTableContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class BillFormContainer
{
    private final VBox view;
   
    private final LabledSwitchableControlContainer<TextField> tfDate;
    private final LabledSwitchableControlContainer<TextField> tfLocation;
    private final LabledSwitchableControlContainer<NumberField> nfPaymentDeadlineInDays;
    private final LabledSwitchableControlContainer<ComboBox<GUITemplate>> cbTemplates;
    private final LabledSwitchableControlContainer<ComboBox<GUIFinancialYear>> cbFinancialYear;
    
    // table
    private final PersonTableContainer personTableContainer;
    private final ContextMenu contextMenu;
    private final MenuItem itemRemove;
    
    // buttons
    private final Button bCreate;
    
    public BillFormContainer(){        
        tfDate = new LabledSwitchableControlContainer<>(
                GUIStringCollection.BILL_DATE, 
                GUIStringCollection.EDIT, 
                new TextField());
        tfLocation = new LabledSwitchableControlContainer<>(
                GUIStringCollection.BILL_LOCATION, 
                GUIStringCollection.EDIT, 
                new TextField());
        nfPaymentDeadlineInDays = new LabledSwitchableControlContainer<>(
                GUIStringCollection.BILL_PAYMENT_DEADLINE, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        cbTemplates = new LabledSwitchableControlContainer<>(
                GUIStringCollection.BILL_TEMPLATE, 
                GUIStringCollection.EDIT, 
                new ComboBox());
        
        cbFinancialYear = new LabledSwitchableControlContainer<>(
                GUIStringCollection.BILL_FINANCIAL_YEAR, 
                GUIStringCollection.EDIT, 
                new ComboBox());
        
        // table
        itemRemove = new MenuItem(GUIStringCollection.BILL_REMOVE_PERSON);
        contextMenu = new ContextMenu(itemRemove);
        personTableContainer = new PersonTableContainer();
        personTableContainer.getTable().setContextMenu(contextMenu);
        
        // buttons
        bCreate = new Button(GUIStringCollection.BILL_CREATE);
        
        view = new VBox(
                tfDate.getView(),
                tfLocation.getView(),
                nfPaymentDeadlineInDays.getView(),
                cbTemplates.getView(),
                cbFinancialYear.getView(),
                new Separator(),
                new Label(GUIStringCollection.BILL_PERSONS),
                personTableContainer.getTable(),
                new Separator(),
                bCreate);
    }

    public VBox getView() {
        return view;
    }

    public LabledSwitchableControlContainer<TextField> getTfDate() {
        return tfDate;
    }

    public LabledSwitchableControlContainer<TextField> getTfLocation() {
        return tfLocation;
    }

    public LabledSwitchableControlContainer<NumberField> getNfPaymentDeadlineInDays() {
        return nfPaymentDeadlineInDays;
    }

    public LabledSwitchableControlContainer<ComboBox<GUITemplate>> getCbTemplates() {
        return cbTemplates;
    }

    public LabledSwitchableControlContainer<ComboBox<GUIFinancialYear>> getCbFinancialYear() {
        return cbFinancialYear;
    }
    
    public PersonTableContainer getPersonTableContainer() {
        return personTableContainer;
    }

    public Button getbCreate() {
        return bCreate;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getItemRemove() {
        return itemRemove;
    }
}
