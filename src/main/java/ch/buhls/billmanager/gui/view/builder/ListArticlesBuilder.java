
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.container.table.ArticleTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListArticlesListener;
import ch.buhls.billmanager.gui.view.container.menues.ListArticlesMenuContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;



/**
 *
 * @author simon
 */
public class ListArticlesBuilder
{
    private final IListArticlesListener listener;
    
    // container
    private final ArticleTableContainer tableContainer;
    private final ListArticlesMenuContainer contextMenuContainer;

    public ListArticlesBuilder(IListArticlesListener listener, ObservableList<GUIArticle> articlesToDisplay) {
        this.listener = listener;
        
        // init view containers
        contextMenuContainer = new ListArticlesMenuContainer();
        
        tableContainer = new ArticleTableContainer();
        tableContainer.getTable().setItems(articlesToDisplay);
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableContainer.getTable().setContextMenu(contextMenuContainer.getContextMenu());
        
        this.connectListenerToContextMenu();
    }
    
    private void connectListenerToContextMenu()
    {
        contextMenuContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
        
        contextMenuContainer.getItemNew().setOnAction((ActionEvent event) -> {
            listener.create();
        });
        
        contextMenuContainer.getItemMark().setOnAction((ActionEvent event) -> {
            listener.mark(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
        
        contextMenuContainer.getContextMenu().setOnShowing((event) -> {
            listener.contextMenuOpened(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
        
        contextMenuContainer.getItemShowVersions().setOnAction((ActionEvent event) -> {
            listener.showVersions(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
    }
    
    public Node getView() {
        return tableContainer.getTable();
    }
}
