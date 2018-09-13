
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.gui.view.container.form.FinancialYearFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class FinancialYearMaskBuilder implements IMaskBuilder
{
    
    // data
    private final GUIFinancialYear data;

    // view
    private final FinancialYearFormContainer formContainer;

    // listener
    private final IDefaultMaskListener<GUIFinancialYear> listener;

    public FinancialYearMaskBuilder(GUIFinancialYear data, IDefaultMaskListener<GUIFinancialYear> listener) {
        this.data = data;
        this.listener = listener;

        this.formContainer = new FinancialYearFormContainer();
        
        bindListeners();
        bindProperties();
    }


    @Override
    public void bindProperties() {
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfIdDBContainer(), data.getDb_id(), true);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionDBContainer(), data.getDb_version(), true);

        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfNameContainer(), data.getName(), true);
        
        LabledSwitchableControlContainer.bindDatePicker(formContainer.getDpFirstDayContainer(), data.getFirstDay(), true);
        LabledSwitchableControlContainer.bindDatePicker(formContainer.getDpLastDayContainer(), data.getLastDay(), true);
    }

    @Override
    public void bindListeners() {
        formContainer.getbSave().setOnAction((event)
                -> {
            this.listener.save(data);
        });
    }

    @Override
    public void changeToEditMode() {
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfBillNrPrefixContainer());
        
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getDpFirstDayContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getDpLastDayContainer());
        
        formContainer.getbSave().setDisable(false);
    }

    @Override
    public void changeToShowMode() {
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfBillNrPrefixContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getDpFirstDayContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getDpLastDayContainer());
        
        formContainer.getbSave().setDisable(false);
    }

    @Override
    public void changeToCreateMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfBillNrPrefixContainer());
        
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getDpFirstDayContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getDpLastDayContainer());
        
        formContainer.getbSave().setDisable(false);
    }

    @Override
    public VBox getView() {
        return formContainer.getView();
    }
    
}
