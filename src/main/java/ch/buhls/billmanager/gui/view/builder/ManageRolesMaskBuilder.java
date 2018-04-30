package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.container.menues.ManageRolesMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.RoleTableContainer;
import ch.buhls.billmanager.gui.view.listener.IManageRolesListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ManageRolesMaskBuilder
{

    private final VBox view;

    private final ManageRolesMenuContainer menuContainer;
    private final RoleTableContainer tableContainer;
    
    private final ObjectProperty<GUIRole> markedRole;

    private final IManageRolesListener listener;

    public ManageRolesMaskBuilder(IManageRolesListener listener, ObservableList<GUIRole> entites, ObjectProperty<GUIRole> markedRole) {
        this.listener = listener;
        
        this.markedRole = markedRole;

        menuContainer = new ManageRolesMenuContainer();
        tableContainer = new RoleTableContainer();
        tableContainer.getTable().setItems(entites);

        this.view = new VBox(menuContainer.getButtonBar(), tableContainer.getTable());
        view.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        
        connectListener();
        
        enableRemoveRole(false);
        enableAddMarkedRole(false);
    }

    private void connectListener() {
        menuContainer.getButtonAdd().setOnAction((ActionEvent event) -> {
            listener.addMarkedRole();
        });

        menuContainer.getButtonRemove().setOnAction((ActionEvent event) -> {
            listener.removeRole(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });

        tableContainer.getTable().getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
            enableRemoveRole(tableContainer.getTable().getSelectionModel().getSelectedItem() != null);
        });
        
        markedRole.addListener((ObservableValue<? extends GUIRole> observable, GUIRole oldValue, GUIRole newValue) -> {
            listener.markedRoleChanged();
        });
    }

    public final void enableAddMarkedRole(boolean enable) {
        menuContainer.getButtonAdd().setDisable(!enable);
    }

    public final void enableRemoveRole(boolean enable) {
        menuContainer.getButtonRemove().setDisable(!enable);
    }

    public VBox getView() {
        return view;
    }
}
