
package ch.buhls.billmanager.gui.view.listener;

/**
 *
 * @author simon
 */
public interface IMainViewListener
{
    public void create();
    public void open();
    public void openRecent(String desc);
    public void save();
    public void saveAs();
    
    public void importMembers();
    
    public void showSettings();
    public void showGlobalSettings();
    public void showMembersTable();
    public void showComponentsTable();
    public void showBillsTable();
}
