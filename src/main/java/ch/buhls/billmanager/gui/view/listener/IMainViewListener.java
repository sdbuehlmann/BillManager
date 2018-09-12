
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
    
    public void importMembers();

    public void showGlobalSettings();
}
