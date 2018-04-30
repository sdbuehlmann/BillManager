
package ch.buhls.billmanager.gui.view.listener;

import ch.buhls.billmanager.gui.data.GUIPosition;

/**
 *
 * @author simon
 */
public interface IManagePositionsListener
{
    public void save();
    
    public void editPos(GUIPosition pos);
    public void savePos(GUIPosition pos);
    public void removePos(GUIPosition pos);
    public void addMarkedArticle();
    
    public void selectionChanged(GUIPosition pos);
}
