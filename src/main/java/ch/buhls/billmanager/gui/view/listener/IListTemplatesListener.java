
package ch.buhls.billmanager.gui.view.listener;

import ch.buhls.billmanager.gui.data.GUITemplate;

/**
 *
 * @author simon
 */
public interface IListTemplatesListener extends IContextMenuListener<GUITemplate>
{
    public void create();
    public void edit(GUITemplate selected);
    public void show(GUITemplate selected);
}
