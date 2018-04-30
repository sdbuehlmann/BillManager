
package ch.buhls.billmanager.gui.view.listener;

/**
 *
 * @author simon
 */
public interface IMainViewListener
{
    public void create();
    public void open();
    public void save();
    public void saveAs();
    
    public void importMembers();
    public void importArticles();
    public void exportArticles();
    
    public void showSettings();
    public void showGlobalSettings();
    public void showMembersTable();
    public void showComponentsTable();
    public void showBillsTable();
}
