
package ch.buhls.billmanager.gui.framework;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 *
 * @author simon
 */
public class TabContainer implements ITabHandle
{
    private final TabPane parent;
    private final Tab tab;

    public TabContainer(TabPane parent, Tab tab)
    {
        this.parent = parent;
        this.tab = tab;
    }

    @Override
    public void focus()
    {
        parent.getSelectionModel().select(tab);
    }

    @Override
    public void close()
    {
        parent.getTabs().remove(tab);
    }

    public TabPane getParent()
    {
        return parent;
    }

    public Tab getTab()
    {
        return tab;
    }
    
    
    
}
