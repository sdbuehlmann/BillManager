package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.AgePersonFilter.AgeFilterType;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;
import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.IHintBar;
import ch.buhls.billmanager.gui.view.container.menues.ListPersonsMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.PersonTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListPersonsListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ListPersonsBuilder extends AListBuilder<GUIPerson> implements IHintBar
{
    private final VBox view;
    
    private final IListPersonsListener listener;

    private final HintBarContainer hintBarContainer;
    private final PersonTableContainer tableContainer;
    private final ListPersonsMenuContainer menuContainer;
    
    private final ObservableList<GUIPerson> personsToDisplay;

    public ListPersonsBuilder(IListPersonsListener listener, ObservableList<GUIPerson> personsToDisplay) {
        super(new PersonTableContainer());
        tableContainer = (PersonTableContainer)super.tableContainer;
        
        this.listener = listener;
        this.personsToDisplay = personsToDisplay;

        view = new VBox();
        hintBarContainer = new HintBarContainer();
        menuContainer = new ListPersonsMenuContainer();
        
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());

        view.getChildren().add(hintBarContainer.getView());
        view.getChildren().add(tableContainer.getTable());

        this.bindProperties();
        this.bindListener();
    }
    
    private void bindProperties(){
        tableContainer.getTable().setItems(personsToDisplay);
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
        menuContainer.getItemRegisterBill().setOnAction((ActionEvent event)
                -> {
            listener.registerNewBill(tableContainer.getTable().getSelectionModel().getSelectedItems());
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
        menuContainer.getItemAddCustom().setOnAction((ActionEvent event)
                -> {
            listener.addArticleToBusket(tableContainer.getTable().getSelectionModel().getSelectedItems(), -1);
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
        
        // filter
        menuContainer.getItemShowRoleMember().setOnAction((ActionEvent event)
                -> {
            listener.filterMembersByMarkedRole();
        });
        menuContainer.getItemHideRoleMember().setOnAction((ActionEvent event)
                -> {
            listener.hideMarkedRoleMembers();
        });
        menuContainer.getItemOlder().setOnAction((ActionEvent event)
                -> {
            listener.filterMembersByAge(AgeFilterType.OLDER);
        });
        menuContainer.getItemOlderOrEqual().setOnAction((ActionEvent event)
                -> {
            listener.filterMembersByAge(AgeFilterType.OLDER_OR_EQUAL);
        });
        menuContainer.getItemYounger().setOnAction((ActionEvent event)
                -> {
            listener.filterMembersByAge(AgeFilterType.YOUNGER);
        });
        menuContainer.getItemYoungerOrEqual().setOnAction((ActionEvent event)
                -> {
            listener.filterMembersByAge(AgeFilterType.YOUNGER_OR_EQUAL);
        });
        menuContainer.getItemEqual().setOnAction((ActionEvent event)
                -> {
            listener.filterMembersByAge(AgeFilterType.EQUAL);
        });
    }
    
    public void enableToAddArticle(boolean enable){
        menuContainer.getMenuAddArticle().setDisable(!enable);
    }
    
    public void enableRoleInteractions(boolean enable){
        menuContainer.getItemAddRole().setDisable(!enable);
        menuContainer.getItemShowRoleMember().setDisable(!enable);
        menuContainer.getItemHideRoleMember().setDisable(!enable);
    }
    
    public void enableYearInteractions(boolean enable){
        menuContainer.getItemOlder().setDisable(!enable);
        menuContainer.getItemOlderOrEqual().setDisable(!enable);
        menuContainer.getItemYounger().setDisable(!enable);
        menuContainer.getItemYoungerOrEqual().setDisable(!enable);
        menuContainer.getItemEqual().setDisable(!enable);
    }
    
    public void enableMultiplePersonsSelectedInteractions(){
        menuContainer.getItemNew().setDisable(false);
        menuContainer.getItemNewBill().setDisable(false);
        menuContainer.getMenuAddArticle().setDisable(false);
        menuContainer.getItemAddRole().setDisable(false);
        
        menuContainer.getItemRegisterBill().setDisable(true);
        menuContainer.getItemEdit().setDisable(true);
        menuContainer.getItemShow().setDisable(true);
        menuContainer.getItemShowBusket().setDisable(true);
        menuContainer.getItemShowRoles().setDisable(true);
        menuContainer.getItemShowVersions().setDisable(true);
    }
    
    public void enableSinglePersonSelectedInteractions(){
        menuContainer.getItemNew().setDisable(false);
        menuContainer.getItemNewBill().setDisable(false);
        menuContainer.getMenuAddArticle().setDisable(false);
        menuContainer.getItemAddRole().setDisable(false);
        
        menuContainer.getItemRegisterBill().setDisable(false);
        menuContainer.getItemEdit().setDisable(false);
        menuContainer.getItemShow().setDisable(false);
        menuContainer.getItemShowBusket().setDisable(false);
        menuContainer.getItemShowRoles().setDisable(false);
        menuContainer.getItemShowVersions().setDisable(false);
    }
    
    @Override
    public IHintHandle addHint(String text, IHintListener listener) {
        return hintBarContainer.addFilterHint(text, listener);
    }
    
    // getter

    public VBox getView() {
        return view;
    }

    
    
}
