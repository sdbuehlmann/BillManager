
package ch.buhls.billmanager.gui.view.elements;

import java.time.LocalDate;
import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 * @param <T>
 */
public class LabeledNodeContainer<T extends Node>
{
    private final VBox view;

    private final T node;
    private final Label label;
    
    public LabeledNodeContainer(String labeltext, T node){
        this.node = node;
        this.label = new Label(labeltext);

        view = new VBox(label, node);
        view.setPadding(new Insets(5));
    }

    public VBox getView() {
        return view;
    }

    public T getControl() {
        return node;
    }

    public Label getLabel() {
        return label;
    }

    static public void bindTextfield(LabeledNodeContainer<TextField> cont, Property<String> prop){
        cont.getControl().textProperty().bindBidirectional(prop);
    }
    
    static public void bindTextarea(LabeledNodeContainer<TextArea> cont, Property<String> prop){
        cont.getControl().textProperty().bindBidirectional(prop);
    }

    static public void bindNumberfield(LabeledNodeContainer<NumberField> cont, Property<Number> prop){
        cont.getControl().getValueProperty().bindBidirectional(prop);
    }

    static public void bindDatePicker(LabeledNodeContainer<DatePicker> cont, Property<LocalDate> prop){
        cont.getControl().valueProperty().bindBidirectional(prop);
    }
}
