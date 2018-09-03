
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill.GUIBillStatus;
import ch.buhls.billmanager.gui.view.elements.LabeledControlContainer;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class EditBillFormContainer
{
    private final VBox view;
    
    private final LabeledControlContainer<DatePicker> dpDateClosed;
    private final LabeledControlContainer<ComboBox<GUIBillStatus>> cbState;
    private final LabeledControlContainer<TextArea> taComment;

    public EditBillFormContainer() {
        dpDateClosed = new LabeledControlContainer<>(GUIStringCollection.BILL_DATE_CLOSED, new DatePicker());
        cbState = new LabeledControlContainer<>(GUIStringCollection.BILL_STATUS, new ComboBox(FXCollections.observableArrayList(GUIBillStatus.values())));
        taComment = new LabeledControlContainer<>(GUIStringCollection.BILL_COMMENT, new TextArea());
        
        view = new VBox(dpDateClosed.getView(), cbState.getView(), taComment.getView());
    }

    public VBox getView() {
        return view;
    }

    public LabeledControlContainer<DatePicker> getDpDateClosed() {
        return dpDateClosed;
    }

    public LabeledControlContainer<ComboBox<GUIBillStatus>> getCbState() {
        return cbState;
    }

    public LabeledControlContainer<TextArea> getTaComment() {
        return taComment;
    }    
}
