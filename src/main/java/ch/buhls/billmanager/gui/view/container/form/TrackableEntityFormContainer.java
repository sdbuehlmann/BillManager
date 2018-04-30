
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class TrackableEntityFormContainer
{
    private final VBox view;

    private final LabledSwitchableControlContainer<NumberField> nfIdDBContainer;
    private final LabledSwitchableControlContainer<NumberField> nfVersionDBContainer;
    
    private final LabledSwitchableControlContainer<NumberField> nfVersionContainer;
    private final LabledSwitchableControlContainer<TextField> tfChangeTxtContainer;

    public TrackableEntityFormContainer() {
        nfIdDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_ID, 
                GUIStringCollection.EDIT, 
                new NumberField());
        nfVersionDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_VERSION, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        nfVersionContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.TRACKED_ENRITY_VERSION_NR, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        tfChangeTxtContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.TRACKED_ENRITY_CHANGE_TXT, 
                GUIStringCollection.EDIT, 
                new TextField());
        
        view = new VBox(
                nfIdDBContainer.getView(),
                nfVersionDBContainer.getView(),
                nfVersionContainer.getView(),
                tfChangeTxtContainer.getView());
    }

    public VBox getView() {
        return view;
    }

    public LabledSwitchableControlContainer<NumberField> getNfIdDBContainer() {
        return nfIdDBContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfVersionDBContainer() {
        return nfVersionDBContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfVersionContainer() {
        return nfVersionContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfChangeTxtContainer() {
        return tfChangeTxtContainer;
    }
}
