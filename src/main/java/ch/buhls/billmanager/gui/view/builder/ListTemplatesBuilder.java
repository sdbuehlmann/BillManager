
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.view.container.menues.ListTemplatesMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.TemplateTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListTemplatesListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;

/**
 *
 * @author simon
 */
public class ListTemplatesBuilder extends AListBuilder
{
    private final IListTemplatesListener listener;

    private final TemplateTableContainer tableContainer;
    private final ListTemplatesMenuContainer menuContainer;

    public ListTemplatesBuilder(IListTemplatesListener listener, ObservableList<GUITemplate> templates) {
        this.listener = listener;

        // init view containers
        menuContainer = new ListTemplatesMenuContainer();

        tableContainer = new TemplateTableContainer();
        tableContainer.getTable().setItems(templates);
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        view.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());

        //view.getChildren().add(filterFormContainer.getView());
        view.getChildren().add(tableContainer.getTable());

        this.connectListenerToContextMenu();
    }

    // methods to connect all listeners
    @Override
    protected final void connectListenerToContextMenu() {
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

    // methods to enable/disable functions from the view
    @Override
    public void setMenuSelectionMode(MenuSelectionMode mode) {
        
    }
    
    public void enableToAddArticle(boolean enable){
        
    }
    
    public void enableToAddRole(boolean enable){
        
    }
}
