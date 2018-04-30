package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
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
        super(framework, dataHandler, GUIStringCollection.getTitleForManageRoles(person.getBaseData()));

        this.person = person;
        roles = dataHandler.getPersonRoles(person);
        builder = new ManageRolesMaskBuilder(this, roles, dataHandler.getMarkedRole());
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }
    
    @Override
    public void markedRoleChanged(){
        builder.enableAddMarkedRole(dataHandler.getMarkedRole().get()!=null);
    }

    @Override
    public void addMarkedRole() {
        if (framework.confirmToAddRole()) {
            GUIRole markedRole = dataHandler.getMarkedRole().get();

            try {
                person.getData().getRoles().add(markedRole.getData());
                dataHandler.storePerson(person);
                roles.add(markedRole);
            }
            catch (PersistanceException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void removeRole(GUIRole role) {
        if (framework.confirmToRemoveRole()) {
            try {
                person.getData().getRoles().remove(role.getData());
                dataHandler.storePerson(person);
                roles.remove(role);
            }
            catch (PersistanceException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

}
