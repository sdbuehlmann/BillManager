package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.builder.ManageRolesMaskBuilder;
import ch.buhls.billmanager.gui.view.listener.IManageRolesListener;
import ch.buhls.billmanager.persistance.PersistanceException;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ManageRolesController extends AController implements IManageRolesListener
{

    private final ManageRolesMaskBuilder builder;

    private final ObservableList<GUIRole> roles;
    private final GUIPerson person;

    public ManageRolesController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, framework.getStringCollections().getPersonStringCollection().getTabTitle_ListRoles(person));

        this.person = person;
        roles = dataHandler.getPersonsDataHandler().getPersonRoles(person);
        builder = new ManageRolesMaskBuilder(this, roles, dataHandler.getMarkedRole());
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }
    
    @Override
    public void markedRoleChanged(){
        builder.enableAddMarkedRole(dataHandler.getMarkedRole().get()!=null);
    }

    @Override
    public void addMarkedRole() {
        GUIRole markedRole = dataHandler.getMarkedRole().get();
        
        if (framework.showConfirmDialoque(framework.getStringCollections().getPersonStringCollection().getConfirmTxt_AddRole(markedRole, 1))) {
            try {
                person.getData().getRoles().add(markedRole.getData());
                dataHandler.getPersonsDataHandler().updatePerson(person);
                roles.add(markedRole);
            }
            catch (PersistanceException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void removeRole(GUIRole role) {
        if (framework.showConfirmDialoque(framework.getStringCollections().getPersonStringCollection().getConfirmTxt_RemoveRole(role))) {
            try {
                person.getData().getRoles().remove(role.getData());
                dataHandler.getPersonsDataHandler().updatePerson(person);
                roles.remove(role);
            }
            catch (PersistanceException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

}
