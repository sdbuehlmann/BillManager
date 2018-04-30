
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.AGUITrackedData;
import ch.buhls.billmanager.gui.view.container.table.VersionsTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import ch.buhls.billmanager.gui.view.container.menues.ListVersionsMenuContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author simon
 * @param <T>
 */
public class ListVersionsBuilder<T extends AGUITrackedData>
{
    private final IListVersionsListener<T> listener;
    
    // container
    private final VersionsTableContainer<T> tableContainer;
    private final ListVersionsMenuContainer contextMenuContainer;

    public ListVersionsBuilder(IListVersionsListener<T> listener, ObservableList<T> entitiesToDisplay) {
        this.listener = listener;
        
        // init view containers
        contextMenuContainer = new ListVersionsMenuContainer();
        
        tableContainer = new VersionsTableContainer<>();
        tableContainer.getTable().setItems(entitiesToDisplay);
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableContainer.getTable().setContextMenu(contextMenuContainer.getContextMenu());
        
        this.connectListenerToContextMenu();
    }
    
    private void connectListenerToContextMenu()
    {
        contextMenuContainer.getItemShow().setOnAction((ActionEvent event) -> {
            listener.show(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
    }
    
    public Node getView() {
        return tableContainer.getTable();
    }
}
