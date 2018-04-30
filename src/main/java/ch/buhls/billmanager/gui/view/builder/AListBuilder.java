
package ch.buhls.billmanager.gui.view.builder;

import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public abstract class AListBuilder
{
    protected final VBox view;
    
    public AListBuilder() {
        view = new VBox();
    }
    
    protected abstract void connectListenerToContextMenu();
    public abstract void setMenuSelectionMode(MenuSelectionMode mode);
    
    public VBox getView() {
        return view;
    }
    
    public enum MenuSelectionMode{
        MULTIPLE,
        SINGLE
    }
}
