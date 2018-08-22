
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import ch.buhls.billmanager.gui.view.elements.SelectFileField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class TemplatesFormContainer
{
    private final VBox view;

    private final LabledSwitchableControlContainer<NumberField> nfIdDBContainer;
    private final LabledSwitchableControlContainer<NumberField> nfVersionDBContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfNameContainer;
    private final LabledSwitchableControlContainer<NumberField> nfMaxPositionsContainer;
    
    //private final LabledSwitchableControlContainer<SelectFileField> sffFile;

    private final Button bSave;

    public TemplatesFormContainer() {
        nfIdDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_ID, 
                GUIStringCollection.EDIT, 
                new NumberField());
        nfVersionDBContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.DB_VERSION, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        tfNameContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.TEMPLATE_NAME, 
                GUIStringCollection.EDIT, 
                new TextField());
        nfMaxPositionsContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.TEMPLATE_MAX_POS, 
                GUIStringCollection.EDIT, 
                new NumberField());
        
        bSave = new Button(GUIStringCollection.SAVE);
        
        view = new VBox(
                nfIdDBContainer.getView(),
                nfVersionDBContainer.getView(),
                tfNameContainer.getView(),
                nfMaxPositionsContainer.getView(),
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

    public LabledSwitchableControlContainer<NumberField> getNfMaxPositionsContainer() {
        return nfMaxPositionsContainer;
    }

    public Button getbSave() {
        return bSave;
    }
}
