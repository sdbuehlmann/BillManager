
package ch.buhls.billmanager.gui.view.elements;

import java.time.LocalDate;
import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class LabeledControlContainer<T extends Control>
{
    private final VBox view;

    private final T control;
    private final Label label;
    
    public LabeledControlContainer(String labeltext, T control){
        this.control = control;
        this.label = new Label(labeltext);

        view = new VBox(label, control);
        view.setPadding(new Insets(5));
    }

    public VBox getView() {
        return view;
    }

    public T getControl() {
        return control;
    }

    public Label getLabel() {
        return label;
    }

    static public void bindTextfield(LabeledControlContainer<TextField> cont, Property<String> prop){
        cont.getControl().textProperty().bindBidirectional(prop);
    }
    
    static public void bindTextarea(LabeledControlContainer<TextArea> cont, Property<String> prop){
        cont.getControl().textProperty().bindBidirectional(prop);
    }

    static public void bindNumberfield(LabeledControlContainer<NumberField> cont, Property<Number> prop){
        cont.getControl().getValueProperty().bindBidirectional(prop);
    }

    static public void bindDatePicker(LabeledControlContainer<DatePicker> cont, Property<LocalDate> prop){
        cont.getControl().valueProperty().bindBidirectional(prop);
    }
}
