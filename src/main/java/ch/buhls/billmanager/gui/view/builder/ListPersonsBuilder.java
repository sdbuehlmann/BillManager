package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.view.container.FilterPersonsFormContainer;
import ch.buhls.billmanager.gui.view.container.menues.ListPersonsMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.PersonTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListPersonsListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;

/**
 *
 * @author simon
 */
public class ListPersonsBuilder extends AListBuilder
{

    private final IListPersonsListener listener;

    private final FilterPersonsFormContainer filterFormContainer;
    private final PersonTableContainer tableContainer;
    private final ListPersonsMenuContainer menuContainer;

    public ListPersonsBuilder(IListPersonsListener listener, ObservableList<GUIPerson> personsToDisplay) {
        this.listener = listener;

        // init view containers
        filterFormContainer = new FilterPersonsFormContainer();
        menuContainer = new ListPersonsMenuContainer();

        tableContainer = new PersonTableContainer();
        tableContainer.getTable().setItems(personsToDisplay);
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        view.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());

        //view.getChildren().add(filterFormContainer.getView());
        view.getChildren().add(tableContainer.getTable());

        this.connectListenerToContextMenu();
    }

    // methods to connect all listeners
    @Override
    protected void connectListenerToContextMenu() {
        menuContainer.getContextMenu().setOnShowing((event) -> {
            listener.contextMenuOpened(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        menuContainer.getItemNew().setOnAction((ActionEvent event)
                -> {
            listener.create();
        });
        menuContainer.getItemEdit().setOnAction((ActionEvent event)
                -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
        menuContainer.getItemShow().setOnAction((ActionEvent event)
                -> {
            listener.show(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        menuContainer.getItemNewBill().setOnAction((ActionEvent event)
                -> {
            listener.createNewBill(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        menuContainer.getItemAdd1().setOnAction((ActionEvent event)
                -> {
            listener.addArticleToBusket(tableContainer.getTable().getSelectionModel().getSelectedItems(), 1);
        });
        menuContainer.getItemAdd2().setOnAction((ActionEvent event)
                -> {
            listener.addArticleToBusket(tableContainer.getTable().getSelectionModel().getSelectedItems(), 2);
        });
        menuContainer.getItemAdd3().setOnAction((ActionEvent event)
                -> {
            listener.addArticleToBusket(tableContainer.getTable().getSelectionModel().getSelectedItems(), 3);
        });
        menuContainer.getItemAdd4().setOnAction((ActionEvent event)
                -> {
            listener.addArticleToBusket(tableContainer.getTable().getSelectionModel().getSelectedItems(), 4);
        });
        menuContainer.getItemAdd5().setOnAction((ActionEvent event)
                -> {
            listener.addArticleToBusket(tableContainer.getTable().getSelectionModel().getSelectedItems(), 5);
        });
        menuContainer.getItemShowBusket().setOnAction((ActionEvent event)
                -> {
            listener.showBusket(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        menuContainer.getItemAddRole().setOnAction((ActionEvent event)
                -> {
            listener.addRole(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
        menuContainer.getItemShowRoles().setOnAction((ActionEvent event)
                -> {
            listener.showRoles(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        menuContainer.getItemShowVersions().setOnAction((ActionEvent event)
                -> {
            listener.showVersions(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });
    }

    // methods to enable/disable functions from the view
    @Override
    public void setMenuSelectionMode(MenuSelectionMode mode) {
        menuContainer.getItemNew().setDisable(false);
        menuContainer.getItemNewBill().setDisable(false);
        menuContainer.getMenuAddArticle().setDisable(false);
        menuContainer.getItemAddRole().setDisable(false);
        
        switch (mode) {
            case MULTIPLE:
                menuContainer.getItemEdit().setDisable(true);
                menuContainer.getItemShow().setDisable(true);
                menuContainer.getItemShowBusket().setDisable(true);
                menuContainer.getItemShowRoles().setDisable(true);
                menuContainer.getItemShowVersions().setDisable(true);
                break;
            case SINGLE:
                menuContainer.getItemEdit().setDisable(false);
                menuContainer.getItemShow().setDisable(false);
                menuContainer.getItemShowBusket().setDisable(false);
                menuContainer.getItemShowRoles().setDisable(false);
                menuContainer.getItemShowVersions().setDisable(false);
                break;
        }
    }
    
    public void enableToAddArticle(boolean enable){
        menuContainer.getMenuAddArticle().setDisable(!enable);
    }
    
    public void enableRoleInteractions(boolean enable){
        menuContainer.getItemAddRole().setDisable(!enable);
        menuContainer.getItemShowRoleMember().setDisable(!enable);
        menuContainer.getItemHideRoleMember().setDisable(!enable);
    }
}
