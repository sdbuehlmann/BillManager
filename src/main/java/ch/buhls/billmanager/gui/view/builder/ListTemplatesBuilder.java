
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.view.container.menues.ListTemplatesMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.TemplateTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListTemplatesListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;

/**
 *
 * @author simon
 */
public class ListTemplatesBuilder extends AListBuilder<GUITemplate>
{
    private final IListTemplatesListener listener;

    private final TemplateTableContainer tableContainer;
    private final ListTemplatesMenuContainer menuContainer;

    public ListTemplatesBuilder(IListTemplatesListener listener, ObservableList<GUITemplate> templates) {
        super(new TemplateTableContainer());
        tableContainer = (TemplateTableContainer)super.tableContainer;
        
        this.listener = listener;

        // init view containers
        menuContainer = new ListTemplatesMenuContainer();

        tableContainer.getTable().setItems(templates);
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());

        this.bindListener();
    }

    private void bindListener() {
        menuContainer.getContextMenu().setOnShowing((event) -> {
            listener.contextMenuOpened(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        menuContainer.getItemNew().setOnAction((ActionEvent event)
                -> {
            listener.create();
        });
        menuContainer.getItemEdit().setOnAction((ActionEvent event)
                -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        menuContainer.getItemShow().setOnAction((ActionEvent event)
                -> {
            listener.show(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
    }
    
    public Node getView(){
        return tableContainer.getTable();
    }
}
