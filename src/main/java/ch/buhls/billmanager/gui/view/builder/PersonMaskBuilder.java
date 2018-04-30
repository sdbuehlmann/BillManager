
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.view.container.form.PersonFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class PersonMaskBuilder extends TrackedEntityMaskBuilder<GUIPersonBaseData>
{
    // data
    private final GUIPersonBaseData person;
    
    // view
    private final PersonFormContainer maskContainer;
    
    // listener
    private final IDefaultMaskListener<GUIPersonBaseData> listener;
    
    public PersonMaskBuilder(GUIPersonBaseData person, IDefaultMaskListener<GUIPersonBaseData> listener){
        super(person, new PersonFormContainer());
        this.listener = listener;
        this.maskContainer = (PersonFormContainer)getFormContainer();
        
        this.person = person;
        
        bindProperties();
        bindListeners();
    }
    
    @Override
    protected void bindProperties()
    {
        super.bindProperties();
        
        LabledSwitchableControlContainer.bindNumberfield(maskContainer.getNfPersonIDContainer(), person.getPersonalId(), false);
        
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfPrenameContainer(), person.getPrename(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfNameContainer(), person.getName(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfStreetContainer(), person.getStreet(), false);
        LabledSwitchableControlContainer.bindNumberfield(maskContainer.getNfPostalCodeContainer(), person.getPostalcode(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfCityContainer(), person.getCity(), false);
        
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfPhoneMContainer(), person.getPhoneM(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfPhonePContainer(), person.getPhoneP(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfEmailContainer(), person.getMail(), false);
        
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfIBANContainer(), person.getIban(), false);
        
        LabledSwitchableControlContainer.bindDatePicker(maskContainer.getDpBirthdayContainer(), person.getBirthday(), false);
        
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfSalutationContainer(), person.getSalutation(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfTitleContainer(), person.getTitle(), false);
    }
    
    private void bindListeners()
    {
        maskContainer.getbSave().setOnAction((event) ->
        {
            this.listener.save(person);
        });
    }
    
    // methods to enable/disable functions from the view
    @Override
    public void changeToEditMode(){
        super.changeToEditMode();
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getNfPersonIDContainer());
        
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfPrenameContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfStreetContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getNfPostalCodeContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfCityContainer());
        
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfPhoneMContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfPhonePContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfEmailContainer());

        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfIBANContainer());
        
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getDpBirthdayContainer());

        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfSalutationContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfTitleContainer());
        
        maskContainer.getbSave().setDisable(false);
    }

    @Override
    public void changeToCreateMode() {
        super.changeToCreateMode();
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getNfPersonIDContainer());
        
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfPrenameContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfStreetContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getNfPostalCodeContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfCityContainer());
        
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfPhoneMContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfPhonePContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfEmailContainer());

        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfIBANContainer());
        
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getDpBirthdayContainer());

        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfSalutationContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfTitleContainer());
        
        maskContainer.getbSave().setDisable(false);
    }

    @Override
    public void changeToReadOnlyMode() {
        super.changeToReadOnlyMode();
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getNfPersonIDContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfPrenameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfStreetContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getNfPostalCodeContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfCityContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfPhoneMContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfPhonePContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfEmailContainer());

        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfIBANContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getDpBirthdayContainer());

        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfSalutationContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfTitleContainer());
        
        maskContainer.getbSave().setDisable(true);
    }
    
    
}
