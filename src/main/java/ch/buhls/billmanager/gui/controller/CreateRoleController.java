
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.builder.RoleMaskBuilder;
import ch.buhls.billmanager.gui.view.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class CreateRoleController extends AController implements IDefaultMaskListener<GUIRole>
{
    private final RoleMaskBuilder builder;
    
    public CreateRoleController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, "Rolle erfassen");
        
        builder = new RoleMaskBuilder(dataHandler.createRole(), this);
        builder.changeToCreateMode();
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIRole entity) {
        try {
            if (framework.confirmToStore()) {
                this.dataHandler.storeRole(entity);
                tabHandle.close();
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
