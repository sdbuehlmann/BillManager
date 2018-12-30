
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.data.CopiedDataObjectContainer;
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
public class EditPersonController extends AFormController<GUIPerson> implements IDefaultMaskListener<GUIPersonBaseData>
{
    private final PersonMaskBuilder builder;
    
    private final CopiedDataObjectContainer<GUIPerson> person;
    
    public EditPersonController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, framework.getStringCollections().getPersonStringCollection());
        
        this.person = dataHandler.getPersonsDataHandler().copyPerson(person);
        
        this.builder = new PersonMaskBuilder(this.person.getCopiedDataObject().getBaseData(), this);
        this.builder.changeToEditMode();
        
        displayEditMask(builder.getView(), person);
    }

    @Override
    public void save(GUIPersonBaseData entity) {
        try {
            if (displayConfirmToStoreDialoque(person.getCopiedDataObject())) {
                this.dataHandler.getPersonsDataHandler().updatePersonBaseDataAndPerson(person);
                closeMask();
                displayEditedInfoHint(person.getCopiedDataObject());
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
