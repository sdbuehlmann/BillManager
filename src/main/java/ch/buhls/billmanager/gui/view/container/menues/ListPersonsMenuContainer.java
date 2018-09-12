package ch.buhls.billmanager.gui.view.container.menues;

import ch.buhls.billmanager.gui.GUIStringCollection;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author simon
 */
public class ListPersonsMenuContainer
{

    private final ContextMenu contextMenu;

    private final MenuItem itemEdit, itemNew, itemShow;
    private final MenuItem itemNewBill;

    private final Menu menuAddArticle;
    private final MenuItem itemAdd1, itemAdd2, itemAdd3, itemAdd4, itemAdd5, itemAddCustom;
    private final MenuItem itemShowBusket;
    private final MenuItem itemAddRole;
    private final MenuItem itemShowRoles;

    private final MenuItem itemShowVersions;
    
    private final Menu menuFilter;
    private final Menu menuRoleFilter;
    private final MenuItem itemShowRoleMember;
    private final MenuItem itemHideRoleMember;
    private final Menu menuAgeFilter;
    private final MenuItem itemOlderOrEqual;
    private final MenuItem itemYoungerOrEqual;
    private final MenuItem itemOlder;
    private final MenuItem itemYounger;
    private final MenuItem itemEqual;
    
    public ListPersonsMenuContainer() {
        itemNew = new MenuItem(GUIStringCollection.NEW);
        itemEdit = new MenuItem(GUIStringCollection.EDIT);
        itemShow = new MenuItem(GUIStringCollection.SHOW);

        itemNewBill = new MenuItem(GUIStringCollection.PERSON_NEW_BILL);

        itemAdd1 = new MenuItem(GUIStringCollection.PERSON_ADD_1);
        itemAdd2 = new MenuItem(GUIStringCollection.PERSON_ADD_2);
        itemAdd3 = new MenuItem(GUIStringCollection.PERSON_ADD_3);
        itemAdd4 = new MenuItem(GUIStringCollection.PERSON_ADD_4);
        itemAdd5 = new MenuItem(GUIStringCollection.PERSON_ADD_5);
        itemAddCustom = new MenuItem(GUIStringCollection.PERSON_ADD_CUSTOM);
        menuAddArticle = new Menu(
                GUIStringCollection.PERSON_ADD_ART_TO_BILL,
                null,
                itemAdd1,
                itemAdd2,
                itemAdd3,
                itemAdd4,
                itemAdd5,
                itemAddCustom);
        itemShowBusket = new MenuItem(GUIStringCollection.PERSON_SHOW_BUSKET);

        itemAddRole = new MenuItem(GUIStringCollection.PERSON_ADD_ROLE);
        itemShowRoles = new MenuItem(GUIStringCollection.PERSON_SHOW_ROLES);

        itemShowVersions = new MenuItem(GUIStringCollection.SHOW_VERSIONS);
        
        // filter
        itemShowRoleMember = new MenuItem(GUIStringCollection.PERSON_ADD_FILTER_SHOW_ROLE_MEMBER);
        itemHideRoleMember = new MenuItem(GUIStringCollection.PERSON_ADD_FILTER_HIDE_ROLE_MEMBER);
        menuRoleFilter = new Menu(GUIStringCollection.PERSON_ROLE_FILTER, null, itemShowRoleMember, itemHideRoleMember);
        
        itemOlderOrEqual = new MenuItem(GUIStringCollection.PERSON_AGE_FILTER_OLDER_EQUAL);
        itemYoungerOrEqual = new MenuItem(GUIStringCollection.PERSON_AGE_FILTER_YOUNGER_EQUAL);
        itemOlder   = new MenuItem(GUIStringCollection.PERSON_AGE_FILTER_OLDER);
        itemYounger = new MenuItem(GUIStringCollection.PERSON_AGE_FILTER_YOUNGER);
        itemEqual = new MenuItem(GUIStringCollection.PERSON_AGE_FILTER_EQUAL);
        menuAgeFilter = new Menu(GUIStringCollection.PERSON_AGE_FILTER, null, itemOlderOrEqual, itemYoungerOrEqual, itemOlder, itemYounger, itemEqual);
                
        menuFilter = new Menu(GUIStringCollection.PERSON_FILTER, null, menuRoleFilter, menuAgeFilter);
        
        contextMenu = new ContextMenu(
                itemNew,
                itemEdit,
                itemShow,
                new SeparatorMenuItem(),
                itemNewBill,
                new SeparatorMenuItem(),
                menuAddArticle,
                itemShowBusket,
                itemAddRole,
                itemShowRoles,
                new SeparatorMenuItem(),
                itemShowVersions,
                new SeparatorMenuItem(),
                menuFilter);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getItemEdit() {
        return itemEdit;
    }

    public MenuItem getItemNew() {
        return itemNew;
    }

    public MenuItem getItemAdd1() {
        return itemAdd1;
    }

    public MenuItem getItemAdd2() {
        return itemAdd2;
    }

    public MenuItem getItemAdd3() {
        return itemAdd3;
    }

    public MenuItem getItemAdd4() {
        return itemAdd4;
    }

    public MenuItem getItemAdd5() {
        return itemAdd5;
    }

    public MenuItem getItemAddCustom() {
        return itemAddCustom;
    }

    public MenuItem getItemNewBill() {
        return itemNewBill;
    }

    public MenuItem getItemShowVersions() {
        return itemShowVersions;
    }

    public MenuItem getItemShow() {
        return itemShow;
    }

    public Menu getMenuAddArticle() {
        return menuAddArticle;
    }

    public MenuItem getItemShowBusket() {
        return itemShowBusket;
    }

    public MenuItem getItemAddRole() {
        return itemAddRole;
    }

    public MenuItem getItemShowRoles() {
        return itemShowRoles;
    }

    public Menu getMenuFilter() {
        return menuFilter;
    }

    public MenuItem getItemShowRoleMember() {
        return itemShowRoleMember;
    }

    public MenuItem getItemHideRoleMember() {
        return itemHideRoleMember;
    }

    public Menu getMenuRoleFilter() {
        return menuRoleFilter;
    }

    public Menu getMenuAgeFilter() {
        return menuAgeFilter;
    }

    public MenuItem getItemOlderOrEqual() {
        return itemOlderOrEqual;
    }

    public MenuItem getItemYoungerOrEqual() {
        return itemYoungerOrEqual;
    }

    public MenuItem getItemOlder() {
        return itemOlder;
    }

    public MenuItem getItemYounger() {
        return itemYounger;
    }

    public MenuItem getItemEqual() {
        return itemEqual;
    }

    
}
