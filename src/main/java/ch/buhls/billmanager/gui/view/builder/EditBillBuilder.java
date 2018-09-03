package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.gui.view.container.form.EditBillFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabeledControlContainer;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class EditBillBuilder
{

    private final VBox view;
    private final EditBillFormContainer form;
    private final Button bSave;

    private final GUIBill data;
    private final IDefaultMaskListener<GUIBill> listener;

    public EditBillBuilder(GUIBill data, IDefaultMaskListener<GUIBill> listener) {
        this.data = data;
        this.listener = listener;

        form = new EditBillFormContainer();
        bSave = new Button(GUIStringCollection.SAVE);
        view = new VBox(form.getView(), bSave);

        bindProperties();
        bindListener();
    }

    private final void bindProperties() {
        LabeledControlContainer.bindTextarea(form.getTaComment(), data.getComment());
        LabeledControlContainer.bindDatePicker(form.getDpDateClosed(), data.getClosedDate());
        form.getCbState().getControl().valueProperty().bindBidirectional(data.getState());
    }

    private final void bindListener() {
        bSave.setOnAction((event)
                -> {
            listener.save(data);
        });
    }

    public VBox getView() {
        return view;
    }
}
