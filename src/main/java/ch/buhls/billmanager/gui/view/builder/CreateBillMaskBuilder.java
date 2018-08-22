
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUICreateBillData;
import ch.buhls.billmanager.gui.view.container.form.BillFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import javafx.event.ActionEvent;
import ch.buhls.billmanager.gui.view.builder.listener.ICreateBillMaskListener;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class CreateBillMaskBuilder
{
    // data
    private final GUICreateBillData data;

    // view
    private final BillFormContainer formContainer;

    // listener
    private final ICreateBillMaskListener listener;
    
    public CreateBillMaskBuilder(GUICreateBillData data, ICreateBillMaskListener listener) {
        this.listener = listener;
        this.data = data;
        
        formContainer = new BillFormContainer();
        
        bindData();
        bindListener();
    }
    
    private void bindData(){
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfDate(), data.getDate(), false);
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfLocation(), data.getLocation(), false);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfPaymentDeadlineInDays(), data.getPaymentDeadlineInDays(), false);
        
        formContainer.getCbTemplates().getControl().setItems(data.getTemplates());
        formContainer.getCbTemplates().getControl().valueProperty().bindBidirectional(data.getSelectedTemplate());
        
        formContainer.getPersonTableContainer().getTable().setItems(data.getPersons());
    }
    
    private void bindListener(){
        formContainer.getbCreate().setOnAction((event)
                -> {
            listener.create(data);
        });
        formContainer.getItemRemove().setOnAction((ActionEvent event)
                -> {
            listener.removePerson(formContainer.getPersonTableContainer().getTable().getSelectionModel().getSelectedItem());
        });
    }
    
    public VBox getView(){
        return formContainer.getView();
    }
}
