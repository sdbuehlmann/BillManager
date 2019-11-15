package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.framework.jfxRenderer.TableRenderer;
import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import ch.buhls.billmanager.gui.viewModel.criteria.AgePersonCriteria.AgeFilterType;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;
import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.menues.ListPersonsMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.PersonTableContainer;
import ch.buhls.billmanager.gui.view.listener.IListPersonsListener;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ch.buhls.billmanager.gui.view.container.IHintBarContainer;

import java.util.Date;

/**
 *
 * @author simon
 */
public class ListPersonsBuilder extends AListBuilder<GUIPerson> implements IHintBarContainer
{
    public final static PropertiesView<Person> propView = getPropertiesView();

    private final VBox view;
    
    private final IListPersonsListener listener;

    private final HintBarContainer hintBarContainer;
    private final PersonTableContainer tableContainer;
    private final ListPersonsMenuContainer menuContainer;

    private final TableView<Person> tableView;
    
    private final ObservableList<Person> personsToDisplay;

    public ListPersonsBuilder(IListPersonsListener listener, ObservableList<Person> personsToDisplay) {
        super(new PersonTableContainer());
        tableContainer = (PersonTableContainer)super.tableContainer;
        
        this.listener = listener;
        this.personsToDisplay = personsToDisplay;

        view = new VBox();
        hintBarContainer = new HintBarContainer();
        menuContainer = new ListPersonsMenuContainer();

        tableView = TableRenderer.render(propView, personsToDisplay);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setContextMenu(menuContainer.getContextMenu());

        //tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //VBox.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        //tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());

        view.getChildren().add(hintBarContainer.getView());
        //view.getChildren().add(tableContainer.getTable());
        view.getChildren().add(tableView);

        //this.bindProperties();
        this.bindListener();
    }
    
    private void bindProperties(){
        //tableContainer.getTable().setItems(personsToDisplay);
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
            ObservableList<Person> selectedItems = tableView.getSelectionModel().getSelectedItems();
            listener.edit(null);
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

    private static PropertiesView<Person>  getPropertiesView(){
        PropertiesView<Person> desc = new PropertiesView<>("person", Person.class);

        desc.addCategory("db");
        desc.addProperty("id", Integer.class, (owner) -> { return owner.getPersonBaseData().getId(); });
        desc.addProperty("version", Integer.class, (owner) -> { return owner.getPersonBaseData().getVersion(); });

        desc.addCategory("versioning");
        desc.addProperty("version", Integer.class, (owner) -> { return owner.getPersonBaseData().getVersionNr(); });
        desc.addProperty("changeText", String.class, (owner) -> { return owner.getPersonBaseData().getChangeTxt(); });

        desc.addCategory("identification");
        desc.addProperty("name", String.class, (owner) -> { return owner.getPersonBaseData().getName(); });
        desc.addProperty("prename", String.class, (owner) -> { return owner.getPersonBaseData().getPrename(); });
        desc.addProperty("birthday", Date.class, (owner) -> { return owner.getPersonBaseData().getBirthday(); });

        desc.addCategory("contact");
        desc.addProperty("phoneP", String.class, (owner) -> { return owner.getPersonBaseData().getPhoneP(); });
        desc.addProperty("phoneM", String.class, (owner) -> { return owner.getPersonBaseData().getPhoneM(); });
        desc.addProperty("mail", String.class, (owner) -> { return owner.getPersonBaseData().getEmail(); });
        desc.addProperty("iban", String.class, (owner) -> { return owner.getPersonBaseData().getIban(); });

        desc.addCategory("adress");
        desc.addProperty("company", String.class, (owner) -> { return owner.getPersonBaseData().getCompany(); });
        desc.addProperty("street", String.class, (owner) -> { return owner.getPersonBaseData().getStreet(); });
        desc.addProperty("postalcode", Integer.class, (owner) -> { return owner.getPersonBaseData().getPostalcode(); });
        desc.addProperty("city", String.class, (owner) -> { return owner.getPersonBaseData().getCity(); });

        desc.addCategory("letter");
        desc.addProperty("salutation", String.class, (owner) -> { return owner.getPersonBaseData().getSalutation(); });
        desc.addProperty("title", String.class, (owner) -> { return owner.getPersonBaseData().getTitle(); });

        return desc;
    }


    // getter

    public VBox getView() {
        return view;
    }

    
    
}
