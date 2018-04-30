
package ch.buhls.billmanager.gui.view.listener;

import ch.buhls.billmanager.gui.data.GUIRole;

/**
 *
 * @author simon
 */
public interface IListRolesListener extends IContextMenuListener<GUIRole>
{
    public void create();
    public void edit(GUIRole selected);
    public void show(GUIRole selected);
    public void delete(GUIRole selected);
    
    public void mark(GUIRole selected);
}
