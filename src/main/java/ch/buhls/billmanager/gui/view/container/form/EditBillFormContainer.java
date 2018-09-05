
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill.GUIBillStatus;
import ch.buhls.billmanager.gui.view.elements.LabeledNodeContainer;
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
    
    private final LabeledNodeContainer<DatePicker> dpDateClosed;
    private final LabeledNodeContainer<ComboBox<GUIBillStatus>> cbState;
    private final LabeledNodeContainer<TextArea> taComment;

    public EditBillFormContainer() {
        dpDateClosed = new LabeledNodeContainer<>(GUIStringCollection.BILL_DATE_CLOSED, new DatePicker());
        cbState = new LabeledNodeContainer<>(GUIStringCollection.BILL_STATUS, new ComboBox(FXCollections.observableArrayList(GUIBillStatus.values())));
        taComment = new LabeledNodeContainer<>(GUIStringCollection.BILL_COMMENT, new TextArea());
        
        view = new VBox(dpDateClosed.getView(), cbState.getView(), taComment.getView());
    }

    public VBox getView() {
        return view;
    }

    public LabeledNodeContainer<DatePicker> getDpDateClosed() {
        return dpDateClosed;
    }

    public LabeledNodeContainer<ComboBox<GUIBillStatus>> getCbState() {
        return cbState;
    }

    public LabeledNodeContainer<TextArea> getTaComment() {
        return taComment;
    }    
}
