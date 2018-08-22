
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.builder.RoleMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class ShowRolesController extends AController implements IDefaultMaskListener<GUIRole>
{
    private final RoleMaskBuilder builder;
    
    public ShowRolesController(IGUIFramework framework, DataHandler dataHandler, GUIRole role) {
        super(framework, dataHandler, "Rolle anzeigen");
        
        builder = new RoleMaskBuilder(role, this);
        builder.changeToShowMode();
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIRole entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
