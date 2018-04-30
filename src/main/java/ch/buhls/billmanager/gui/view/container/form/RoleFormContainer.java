
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class RoleFormContainer
{
    private final VBox view;

    private final LabledSwitchableControlContainer<NumberField> nfIdDBContainer;
    private final LabledSwitchableControlContainer<NumberField> nfVersionDBContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfNameContainer;

    private final Button bSave;

    public RoleFormContainer() {
        nfIdDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_ID, 
                GUIStringCollection.EDIT, 
                new NumberField());
        nfVersionDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_VERSION, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        tfNameContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ROLE_NAME, 
                GUIStringCollection.EDIT, 
                new TextField());
        
        bSave = new Button(GUIStringCollection.SAVE);
        
        view = new VBox(
                nfIdDBContainer.getView(),
                nfVersionDBContainer.getView(),
                tfNameContainer.getView(),
                bSave);
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

    public LabledSwitchableControlContainer<TextField> getTfNameContainer() {
        return tfNameContainer;
    }

    public Button getbSave() {
        return bSave;
    }
}
