
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIRegisterBillData;
import ch.buhls.billmanager.gui.view.builder.listener.IRegisterBillMaskListener;
import ch.buhls.billmanager.gui.view.container.form.RegisterBillFormContainer;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class RegisterBillMaskBuilder
{
    // data
    private final GUIRegisterBillData data;

    // view
    private final RegisterBillFormContainer formContainer;

    // listener
    private final IRegisterBillMaskListener listener;
    
    public RegisterBillMaskBuilder(GUIRegisterBillData data, IRegisterBillMaskListener listener) {
        this.listener = listener;
        this.data = data;
        
        formContainer = new RegisterBillFormContainer();
        
        bindData();
        bindListener();
    }
    
    private void bindData(){
        formContainer.getDpDate().valueProperty().bindBidirectional(data.getDate());
        formContainer.getTfLocation().textProperty().bindBidirectional(data.getLocation());
        formContainer.getNfPaymentDeadlineInDays().getValueProperty().bindBidirectional(data.getPaymentDeadlineInDays());
        
        formContainer.getCbFinancialYear().setItems(data.getYears());
        formContainer.getCbFinancialYear().valueProperty().bindBidirectional(data.getSelectedYear());
        
        formContainer.getSffBillPath().getTfPath().textProperty().bindBidirectional(data.getFile());
    }
    
    private void bindListener(){
        formContainer.getbRegister().setOnAction((event)
                -> {
            listener.register(data);
        });
        formContainer.getSffBillPath().getBtnSearch().setOnAction((ActionEvent event)
                -> {
            listener.selectBillFile();
        });
    }
    
    public VBox getView(){
        return formContainer.getView();
    }
}
