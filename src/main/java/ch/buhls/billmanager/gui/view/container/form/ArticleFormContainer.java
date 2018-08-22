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
public class ArticleFormContainer
{
    private final VBox view;

    private final LabledSwitchableControlContainer<NumberField> nfIdDBContainer;
    private final LabledSwitchableControlContainer<NumberField> nfVersionDBContainer;
    
    private final LabledSwitchableControlContainer<NumberField> nfVersionContainer;
    private final LabledSwitchableControlContainer<TextField> tfChangeTxtContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfDescription1Container;
    private final LabledSwitchableControlContainer<TextField> tfDescription2Container;
    private final LabledSwitchableControlContainer<NumberField> nfPrizeContainer;
    private final LabledSwitchableControlContainer<TextField> tfCategorieContainer;

    private final Button bSave;

    public ArticleFormContainer() {
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
        
        tfDescription1Container = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_TITLE, 
                GUIStringCollection.EDIT, 
                new TextField());
        tfDescription2Container = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_DESCRIPTION, 
                GUIStringCollection.EDIT, 
                new TextField());
        nfPrizeContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_PRICE, 
                GUIStringCollection.EDIT, 
                new NumberField());
        tfCategorieContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_CATEGORIE, 
                GUIStringCollection.EDIT, 
                new TextField());
        
        bSave = new Button(GUIStringCollection.SAVE);
        
        view = new VBox(
                nfIdDBContainer.getView(),
                nfVersionDBContainer.getView(),
                nfVersionContainer.getView(),
                tfChangeTxtContainer.getView(),
                tfDescription1Container.getView(),
                tfDescription2Container.getView(),
                nfPrizeContainer.getView(),
                tfCategorieContainer.getView(),
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

    public LabledSwitchableControlContainer<NumberField> getNfVersionContainer() {
        return nfVersionContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfChangeTxtContainer() {
        return tfChangeTxtContainer;
    }
    
    public LabledSwitchableControlContainer<TextField> getTfDescription1Container() {
        return tfDescription1Container;
    }

    public LabledSwitchableControlContainer<TextField> getTfDescription2Container() {
        return tfDescription2Container;
    }

    public LabledSwitchableControlContainer<NumberField> getNfPrizeContainer() {
        return nfPrizeContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfCategorieContainer() {
        return tfCategorieContainer;
    }

    public Button getbSave() {
        return bSave;
    }
    
    
}
