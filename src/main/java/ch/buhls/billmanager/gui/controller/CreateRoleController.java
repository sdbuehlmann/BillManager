
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.builder.RoleMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class CreateRoleController extends AFormController<GUIRole> implements IDefaultMaskListener<GUIRole>
{
    private final RoleMaskBuilder builder;
    
    public CreateRoleController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, framework.getStringCollections().getRoleStringCollection());
        
        builder = new RoleMaskBuilder(dataHandler.createRole(), this);
        builder.changeToCreateMode();
        
        displayCreateMask(builder.getView());
    }

    @Override
    public void save(GUIRole entity) {
        try {
            if (displayConfirmToStoreDialoque(entity)) {
                this.dataHandler.storeRole(entity);
                closeMask();
                displayCreatedInfoHint(entity);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
