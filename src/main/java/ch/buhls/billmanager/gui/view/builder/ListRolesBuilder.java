
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.container.menues.ListRolesMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.RoleTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListRolesListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author simon
 */
public class ListRolesBuilder extends AListBuilder<GUIRole>
{
    private final IListRolesListener listener;
    
    // containers
    private final RoleTableContainer tableContainer;
    private final ListRolesMenuContainer menuContainer;

    public ListRolesBuilder(IListRolesListener listener, ObservableList<GUIRole> entites) {
        super(new RoleTableContainer());
        tableContainer = (RoleTableContainer)super.tableContainer;
        
        this.listener = listener;
        
        
        menuContainer = new ListRolesMenuContainer();
        
        tableContainer.getTable().setItems(entites);
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());
        
        bindListener();
    }
    
    private void bindListener()
    {
        menuContainer.getItemNew().setOnAction((ActionEvent event) -> {
            listener.create();
        });
        
        menuContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        menuContainer.getItemDelete().setOnAction((ActionEvent event) -> {
            listener.delete(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        menuContainer.getItemShow().setOnAction((ActionEvent event) -> {
            listener.show(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        menuContainer.getItemMark().setOnAction((ActionEvent event) -> {
            listener.mark(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        menuContainer.getContextMenu().setOnShowing((event) -> {
            listener.contextMenuOpened(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
    }
    
    @Override
    public Node getView() {
        return tableContainer.getTable();
    }
}
