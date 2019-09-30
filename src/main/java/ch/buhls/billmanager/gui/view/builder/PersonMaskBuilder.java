
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.framework.jfxRenderer.FormRenderer;
import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.view.container.form.PersonFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author simon
 */
public class PersonMaskBuilder extends TrackedEntityMaskBuilder<GUIPersonBaseData>
{
    private static PropertiesView<PersonBaseData> propView;
    private final GridPane view;
    
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
        
        if(propView == null)
        {
            propView = getPropertiesView();
        }
        view = FormRenderer.render(propView, person.getData());
        
        bindProperties();
        bindListeners();
    }
    
    @Override
    protected void bindProperties()
    {
        super.bindProperties();
        
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfPrenameContainer(), person.getPrename(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfNameContainer(), person.getName(), false);
        LabledSwitchableControlContainer.bindTextfield(maskContainer.getTfCompanyContainer(), person.getCompany(), false);
        
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
        
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfPrenameContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(maskContainer.getTfCompanyContainer());
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
        
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfPrenameContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToEditableState(maskContainer.getTfCompanyContainer());
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
        
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfPrenameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(maskContainer.getTfCompanyContainer());
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
    
    private PropertiesView getPropertiesView(){
        PropertiesView<PersonBaseData> desc = new PropertiesView<>("person", Person.class);
        
        desc.addCategory("db");
        desc.addProperty("id", Integer.class, (owner) -> { return owner.getId(); });
        desc.addProperty("version", Integer.class, (owner) -> { return owner.getVersion(); });
        
        desc.addCategory("versioning");
        desc.addProperty("version", Integer.class, (owner) -> { return owner.getVersionNr(); });
        desc.addProperty("changeText", String.class, (owner) -> { return owner.getChangeTxt(); }, (owner,value) -> { owner.setChangeTxt(value); });
        
        desc.addCategory("identification");
        desc.addProperty("name", String.class, (owner) -> { return owner.getName(); }, (owner,value) -> { owner.setName(value); });
        desc.addProperty("prename", String.class, (owner) -> { return owner.getPrename(); }, (owner,value) -> { owner.setPrename(value); });
        desc.addProperty("birthday", Date.class, (owner) -> { return owner.getBirthday(); }, (owner,value) -> { owner.setBirthday(value); });
        
        desc.addCategory("contact");
        desc.addProperty("phoneP", String.class, (owner) -> { return owner.getPhoneP(); }, (owner,value) -> { owner.setPhoneP(value); });
        desc.addProperty("phoneM", String.class, (owner) -> { return owner.getPhoneM(); }, (owner,value) -> { owner.setPhoneM(value); });
        desc.addProperty("mail", String.class, (owner) -> { return owner.getEmail(); }, (owner,value) -> { owner.setEmail(value); });
        desc.addProperty("iban", String.class, (owner) -> { return owner.getIban(); }, (owner,value) -> { owner.setIban(value); });
        
        desc.addCategory("adress");
        desc.addProperty("company", String.class, (owner) -> { return owner.getCompany(); }, (owner,value) -> { owner.setCompany(value); });
        desc.addProperty("street", String.class, (owner) -> { return owner.getStreet(); }, (owner,value) -> { owner.setStreet(value); });
        desc.addProperty("postalcode", Integer.class, (owner) -> { return owner.getPostalcode(); }, (owner,value) -> { owner.setPostalcode(value); });
        desc.addProperty("city", String.class, (owner) -> { return owner.getCity(); }, (owner,value) -> { owner.setCity(value); });
        
        desc.addCategory("letter");
        desc.addProperty("salutation", String.class, (owner) -> { return owner.getSalutation(); }, (owner,value) -> { owner.setSalutation(value); });
        desc.addProperty("title", String.class, (owner) -> { return owner.getTitle(); }, (owner,value) -> { owner.setTitle(value); });
        
        return desc;
    }
    
    @Override
    public Node getView() {
        return view;
    }
}
