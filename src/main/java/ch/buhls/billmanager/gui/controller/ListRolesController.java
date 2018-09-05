package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.view.builder.ListRolesBuilder;
import ch.buhls.billmanager.gui.view.container.HintContainer;
import ch.buhls.billmanager.gui.view.listener.IListRolesListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

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

        containerManager.displayMask(builder.getView(), tabName, IGUIFramework.Area.LEFT).focus();
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
        
        // remove old mark
        if (dataHandler.getMarkedRole().get() != null) {
            dataHandler.getMarkedRole().get().getMarked().set(false);
        }
        
        // remove old hint
        if(roleMarkedHintHandle != null){
            roleMarkedHintHandle.close();
            roleMarkedHintHandle = null;
        }

        // mark new
        dataHandler.getMarkedRole().set(selected);
        selected.getMarked().set(true);
        
        roleMarkedHintHandle = framework.displayHint(new HintContainer(new Label(GUIStringCollection.getHintTxt_roleMarked(selected)), () -> {
                // close hint
                dataHandler.getMarkedRole().get().getMarked().set(false);
                dataHandler.getMarkedRole().set(null);
                roleMarkedHintHandle.close();
                roleMarkedHintHandle = null;
            }));
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIRole> selected) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
