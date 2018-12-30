
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
public class EditRoleController extends AFormController<GUIRole> implements IDefaultMaskListener<GUIRole>
{
    private final RoleMaskBuilder builder;

    public EditRoleController(IGUIFramework framework, DataHandler dataHandler, String tabName, GUIRole role) {
        super(framework, dataHandler, framework.getStringCollections().getRoleStringCollection());
        
        role = dataHandler.editRole(role);
        
        this.builder = new RoleMaskBuilder(role, this);
        this.builder.changeToEditMode();
        
        displayEditMask(builder.getView(), role);
    }

    @Override
    public void save(GUIRole entity) {
        try {
            if (displayConfirmToStoreDialoque(entity)) {
                this.dataHandler.storeRole(entity);
                closeMask();
                displayEditedInfoHint(entity);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
