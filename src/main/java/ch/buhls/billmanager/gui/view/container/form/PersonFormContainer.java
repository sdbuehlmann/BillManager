package ch.buhls.billmanager.gui.view.container.form;


import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;

/**
 *
 * @author simon
 */
public class PersonFormContainer extends TrackableEntityFormContainer
{
    private final LabledSwitchableControlContainer<NumberField> nfPersonIDContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfNameContainer;
    private final LabledSwitchableControlContainer<TextField> tfPrenameContainer;
    private final LabledSwitchableControlContainer<TextField> tfStreetContainer;
    private final LabledSwitchableControlContainer<NumberField> nfPostalCodeContainer;
    private final LabledSwitchableControlContainer<TextField> tfCityContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfPhonePContainer;
    private final LabledSwitchableControlContainer<TextField> tfPhoneMContainer;
    private final LabledSwitchableControlContainer<TextField> tfEmailContainer;
    
    private final LabledSwitchableControlContainer<TextField> tfIBANContainer;

    private final LabledSwitchableControlContainer<DatePicker> dpBirthdayContainer;

    private final LabledSwitchableControlContainer<TextField> tfSalutationContainer;
    private final LabledSwitchableControlContainer<TextField> tfTitleContainer;
    
    private final Button bSave;

    public PersonFormContainer()
    {
        nfPersonIDContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.PERSONAL_ID, GUIStringCollection.EDIT, new NumberField());
        
        tfNameContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.NAME, GUIStringCollection.EDIT, new TextField());
        tfPrenameContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.PRENAME, GUIStringCollection.EDIT, new TextField());
        tfStreetContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.STREET, GUIStringCollection.EDIT, new TextField());
        nfPostalCodeContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.POSTAL_CODE, GUIStringCollection.EDIT, new NumberField());
        tfCityContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.CITY, GUIStringCollection.EDIT, new TextField());
        
        tfPhonePContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.PHONE_P, GUIStringCollection.EDIT, new TextField());
        tfPhoneMContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.PHONE_M, GUIStringCollection.EDIT, new TextField());
        tfEmailContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.E_MAIL, GUIStringCollection.EDIT, new TextField());
        
        tfIBANContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.IBAN, GUIStringCollection.EDIT, new TextField());
        
        dpBirthdayContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.BIRTHDAY, GUIStringCollection.EDIT, new DatePicker());
        
        tfSalutationContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.SALUTATION, GUIStringCollection.EDIT, new TextField());
        tfTitleContainer = new LabledSwitchableControlContainer<>(GUIStringCollection.TITLE, GUIStringCollection.EDIT, new TextField());
        
        bSave = new Button(GUIStringCollection.SAVE);

        getView().getChildren().addAll(
                nfPersonIDContainer.getView(),
                tfNameContainer.getView(),
                tfPrenameContainer.getView(),
                tfStreetContainer.getView(),
                nfPostalCodeContainer.getView(),
                tfCityContainer.getView(),
                dpBirthdayContainer.getView(),
                new Separator(),
                tfPhonePContainer.getView(),
                tfPhoneMContainer.getView(),
                tfEmailContainer.getView(),
                new Separator(),
                tfIBANContainer.getView(),
                new Separator(),
                tfSalutationContainer.getView(),
                tfTitleContainer.getView(),
                bSave);
    }

    public LabledSwitchableControlContainer<NumberField> getNfPersonIDContainer() {
        return nfPersonIDContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfNameContainer() {
        return tfNameContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfPrenameContainer() {
        return tfPrenameContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfStreetContainer() {
        return tfStreetContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfPostalCodeContainer() {
        return nfPostalCodeContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfCityContainer() {
        return tfCityContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfPhonePContainer() {
        return tfPhonePContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfPhoneMContainer() {
        return tfPhoneMContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfEmailContainer() {
        return tfEmailContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfIBANContainer() {
        return tfIBANContainer;
    }

    public LabledSwitchableControlContainer<DatePicker> getDpBirthdayContainer() {
        return dpBirthdayContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfSalutationContainer() {
        return tfSalutationContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfTitleContainer() {
        return tfTitleContainer;
    }

    public Button getbSave() {
        return bSave;
    }
    
    
}
