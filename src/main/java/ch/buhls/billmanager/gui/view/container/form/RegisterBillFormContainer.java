
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.view.elements.LabeledNodeContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import ch.buhls.billmanager.gui.view.elements.SelectFileField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class RegisterBillFormContainer
{
    private final VBox view;
   
    private final LabeledNodeContainer<DatePicker> dpDate;
    private final LabeledNodeContainer<TextField> tfLocation;
    private final LabeledNodeContainer<NumberField> nfPaymentDeadlineInDays;
    private final LabeledNodeContainer<ComboBox<GUIFinancialYear>> cbFinancialYear;
    
    private final LabeledNodeContainer<SelectFileField> sffBillPath;
    
    // buttons
    private final Button bRegister;

    public RegisterBillFormContainer() {
        dpDate = new LabeledNodeContainer<>(
                GUIStringCollection.BILL_DATE, 
                new DatePicker());
        tfLocation = new LabeledNodeContainer<>(
                GUIStringCollection.BILL_LOCATION, 
                new TextField());
        nfPaymentDeadlineInDays = new LabeledNodeContainer<>(
                GUIStringCollection.BILL_PAYMENT_DEADLINE, 
                new NumberField());
        cbFinancialYear = new LabeledNodeContainer<>(
                GUIStringCollection.BILL_FINANCIAL_YEAR, 
                new ComboBox());
        
        sffBillPath = new LabeledNodeContainer<>(GUIStringCollection.APP_SETTINGS_INKSCAPE_PATH, new SelectFileField());
        
        // buttons
        bRegister = new Button(GUIStringCollection.BILL_CREATE);
        
        view = new VBox(
                dpDate.getView(),
                tfLocation.getView(),
                nfPaymentDeadlineInDays.getView(),
                cbFinancialYear.getView(),
                new Separator(),
                sffBillPath.getView(),
                new Separator(),
                bRegister);
    }

    public VBox getView() {
        return view;
    }

    public DatePicker getDpDate() {
        return dpDate.getControl();
    }

    public TextField getTfLocation() {
        return tfLocation.getControl();
    }

    public NumberField getNfPaymentDeadlineInDays() {
        return nfPaymentDeadlineInDays.getControl();
    }

    public ComboBox<GUIFinancialYear> getCbFinancialYear() {
        return cbFinancialYear.getControl();
    }

    public SelectFileField getSffBillPath() {
        return sffBillPath.getControl();
    }

    public Button getbRegister() {
        return bRegister;
    }
    
    
}
