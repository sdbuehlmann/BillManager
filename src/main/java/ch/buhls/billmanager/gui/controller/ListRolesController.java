package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.view.builder.ListRolesBuilder;
import ch.buhls.billmanager.gui.view.listener.IListRolesListener;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ListRolesController extends AController implements IListRolesListener
{
    private final ListRolesBuilder builder;
    
    private IHintHandle roleMarkedHintHandle;

    public ListRolesController(IGUIFramework containerManager, DataHandler dataHandler) {
        super(containerManager, dataHandler, "Rollen");

        this.builder = new ListRolesBuilder(this, dataHandler.getRolesBuffer());
        this.bindBuilder(builder);

        this.display(builder.getView(), IGUIFramework.Area.LEFT);
    }

    @Override
    public void create() {
        new CreateRoleController(framework, dataHandler);
    }

    @Override
    public void edit(GUIRole selected) {
        new EditRoleController(framework, dataHandler, tabName, selected);
    }

    @Override
    public void show(GUIRole selected) {
        new ShowRolesController(framework, dataHandler, selected);
    }

    @Override
    public void delete(GUIRole selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mark(GUIRole selected) {
        if (selected == null) {
            return;
        }
        
        // remove old hint
        if(roleMarkedHintHandle != null){
            roleMarkedHintHandle.close();
            roleMarkedHintHandle = null;
        }

        // mark new
        dataHandler.getMarkedRole().set(selected);
        
        roleMarkedHintHandle = framework.displayMarkedHint(GUIStringCollection.getHintTxt_roleMarked(selected), () -> {
            // close hint
                dataHandler.getMarkedRole().set(null);
                roleMarkedHintHandle.close();
                roleMarkedHintHandle = null;
        });
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIRole> selected) {
        
    }

}
