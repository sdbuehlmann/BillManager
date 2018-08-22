package ch.buhls.billmanager.gui.view.elements;

import java.time.LocalDate;
import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 * @param <T>
 */
public class LabledSwitchableControlContainer<T extends Control>
{

    private final VBox view;

    private final T controll;
    private final Label l;
    private final Hyperlink hl;

    public LabledSwitchableControlContainer(String labeltext, String linktext, T controll)
    {
        this.controll = controll;
        this.l = new Label(labeltext);
        this.hl = new Hyperlink(linktext);

        this.view = new VBox(l, new HBox(controll, hl));
        view.setPadding(new Insets(5));
    }

    public VBox getView()
    {
        return view;
    }

    public T getControl()
    {
        return controll;
    }

    public Label getLabel()
    {
        return l;
    }

    public Hyperlink getHyperlink()
    {
        return hl;
    }
    
    static public void bindTf(LabledSwitchableControlContainer<TextField> cont, Property<String> prop){
        cont.getControl().textProperty().bindBidirectional(prop);
    }
    static public void bindNf(LabledSwitchableControlContainer<NumberField> cont, Property<Number> prop){
        cont.getControl().getValueProperty().bindBidirectional(prop);
    }
    static public void unbindTf(LabledSwitchableControlContainer<TextField> cont, Property<String> prop){
        cont.getControl().textProperty().unbindBidirectional(prop);
    }
    static public void unbindNf(LabledSwitchableControlContainer<NumberField> cont, Property<Number> prop){
        cont.getControl().getValueProperty().unbindBidirectional(prop);
    }

    static public void bindTextfield(LabledSwitchableControlContainer<TextField> cont, Property<String> prop, boolean disabled)
    {
        cont.getControl().textProperty().bindBidirectional(prop);
        cont.getControl().setDisable(disabled);
        cont.getHyperlink().setVisible(false);
    }

    static public void bindNumberfield(LabledSwitchableControlContainer<NumberField> cont, Property<Number> prop, boolean disabled)
    {
        cont.getControl().getValueProperty().bindBidirectional(prop);
        cont.getControl().setDisable(disabled);
        cont.getHyperlink().setVisible(false);
    }

    static public void bindDatePicker(LabledSwitchableControlContainer<DatePicker> cont, Property<LocalDate> prop, boolean disabled)
    {
        cont.getControl().setValue(prop.getValue());
        cont.getControl().setDisable(disabled);
        cont.getHyperlink().setVisible(false);
    }
    
    

    static public void changeToEditableState(LabledSwitchableControlContainer cont)
    {
        cont.getControl().setDisable(false);
        cont.getHyperlink().setVisible(false);
    }
    
    static public void changeToReadOnlyState(LabledSwitchableControlContainer cont)
    {
        cont.getControl().setDisable(true);
        cont.getHyperlink().setVisible(false);
    }
    
    static public void changeToSwitchableState(LabledSwitchableControlContainer cont)
    {
        cont.getControl().setDisable(true);
        cont.getHyperlink().setVisible(true);
        cont.getHyperlink().setDisable(false);

        cont.getHyperlink().setOnAction((event) ->
        {
            cont.getHyperlink().setDisable(true);
            cont.getControl().setDisable(false);
        });
    }
}
