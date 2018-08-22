
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
public class CreatePersonController extends AController implements IDefaultMaskListener<GUIPersonBaseData>
{
    private final PersonMaskBuilder builder;
    
    private final GUIPerson person;
    
    public CreatePersonController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.getTitleForCreatePerson());
        
        this.person = dataHandler.createPerson();
        
        this.builder = new PersonMaskBuilder(person.getBaseData(), this);
        
        this.display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIPersonBaseData entity) {
        try {
            if (framework.confirmToStore()) {
                this.dataHandler.storePersonBaseDataAndPerson(person);
                
                tabHandle.close();
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
