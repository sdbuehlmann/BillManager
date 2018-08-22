
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
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
public class EditPersonController extends AController implements IDefaultMaskListener<GUIPersonBaseData>
{
    private final PersonMaskBuilder builder;
    
    private final GUIPerson person;
    
    public EditPersonController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, GUIStringCollection.getTitleForEditPerson(person.getBaseData()));
        
        this.person = dataHandler.editPerson(person);
        
        this.builder = new PersonMaskBuilder(this.person.getBaseData(), this);
        this.builder.changeToEditMode();
        
        this.display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIPersonBaseData entity) {
        try {
            if (framework.confirmToStore()) {
                this.dataHandler.storePersonBaseDataAndPerson(person);
                //this.dataHandler.storePerson(entity);
                
                tabHandle.close();
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
