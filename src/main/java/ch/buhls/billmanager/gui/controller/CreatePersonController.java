
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.view.builder.PersonMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class CreatePersonController extends AFormController<GUIPerson> implements IDefaultMaskListener<GUIPersonBaseData>
{
    private final PersonMaskBuilder builder;
    
    private final GUIPerson person;
    
    public CreatePersonController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, framework.getStringCollections().getPersonStringCollection());
        
        person = dataHandler.getPersonsDataHandler().createPerson();
        builder = new PersonMaskBuilder(person.getBaseData(), this);
        
        displayCreateMask(builder.getView());
    }

    @Override
    public void save(GUIPersonBaseData entity) {
        try {
            if (displayConfirmToStoreDialoque(person)) {
                this.dataHandler.getPersonsDataHandler().storePerson(person);
                closeMask();
                displayCreatedInfoHint(person);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
