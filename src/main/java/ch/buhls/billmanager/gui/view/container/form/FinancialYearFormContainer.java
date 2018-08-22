
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class FinancialYearFormContainer
{
    private final VBox view;

    private final LabledSwitchableControlContainer<NumberField> nfIdDBContainer;
    private final LabledSwitchableControlContainer<NumberField> nfVersionDBContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfNameContainer;
    private final LabledSwitchableControlContainer<TextField> tfBillNrPrefixContainer;
    private final LabledSwitchableControlContainer<DatePicker> dpFirstDayContainer;
    private final LabledSwitchableControlContainer<DatePicker> dpLastDayContainer;
    
    private final Button bSave;
    
    public FinancialYearFormContainer(){
        nfIdDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_ID, 
                GUIStringCollection.EDIT, 
                new NumberField());
        nfVersionDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_VERSION, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        tfNameContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.YEAR_NAME, 
                GUIStringCollection.EDIT, 
                new TextField());
        tfBillNrPrefixContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.YEAR_PREFIX, 
                GUIStringCollection.EDIT, 
                new TextField());
        
        dpFirstDayContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.YEAR_PREFIX, 
                GUIStringCollection.EDIT, 
                new DatePicker());
        dpLastDayContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.YEAR_PREFIX, 
                GUIStringCollection.EDIT, 
                new DatePicker());
        
        bSave = new Button(GUIStringCollection.SAVE);
        
        view = new VBox(
                nfIdDBContainer.getView(),
                nfVersionDBContainer.getView(),
                new Separator(Orientation.HORIZONTAL),
                tfNameContainer.getView(),
                tfBillNrPrefixContainer.getView(),
                dpFirstDayContainer.getView(),
                dpLastDayContainer.getView(),
                bSave);
    }
    
    public VBox getView() {
        return view;
    }

    public LabledSwitchableControlContainer<NumberField> getNfIdDBContainer() {
        return nfIdDBContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfVersionDBContainer() {
        return nfVersionDBContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfNameContainer() {
        return tfNameContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfBillNrPrefixContainer() {
        return tfBillNrPrefixContainer;
    }

    public LabledSwitchableControlContainer<DatePicker> getDpFirstDayContainer() {
        return dpFirstDayContainer;
    }

    public LabledSwitchableControlContainer<DatePicker> getDpLastDayContainer() {
        return dpLastDayContainer;
    }

    public Button getbSave() {
        return bSave;
    }
    
    
}
