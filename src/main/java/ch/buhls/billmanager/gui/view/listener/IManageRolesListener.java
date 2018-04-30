
package ch.buhls.billmanager.gui.view.listener;

import ch.buhls.billmanager.gui.data.GUIRole;

/**
 *
 * @author simon
 */
public interface IManageRolesListener
{
    public void addMarkedRole();
    public void markedRoleChanged();
    public void removeRole(GUIRole role);
}
