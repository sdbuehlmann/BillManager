
package ch.buhls.billmanager.gui.view.listener;

import ch.buhls.billmanager.gui.AgePersonFilter.AgeFilterType;
import ch.buhls.billmanager.gui.data.GUIPerson;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public interface IListPersonsListener
{
    
    public void contextMenuOpened(ObservableList<GUIPerson> selected);
    
    public void create();
    public void edit(List<GUIPerson> persons);
    public void show(List<GUIPerson> persons);

    public void createNewBill(ObservableList<GUIPerson> persons);
    
    public void showBusket(ObservableList<GUIPerson> persons);
    public void addArticleToBusket(ObservableList<GUIPerson> persons, int nr);
    
    public void addRole(ObservableList<GUIPerson> persons);
    public void showRoles(ObservableList<GUIPerson> persons);
    
    public void showVersions(ObservableList<GUIPerson> persons);
    
    public void filterMembersByMarkedRole();
    public void hideMarkedRoleMembers();
    public void filterMembersByAge(AgeFilterType ageFilterType);
}
