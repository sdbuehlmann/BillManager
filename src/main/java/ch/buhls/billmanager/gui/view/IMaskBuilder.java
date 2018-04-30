
package ch.buhls.billmanager.gui.view;

import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public interface IMaskBuilder
{
    public void bindProperties();
    public void bindListeners();
    
    // methods to enable/disable functions from the view
    public void changeToEditMode();
    public void changeToShowMode();
    public void changeToCreateMode();
    
    // getter & setter
    public VBox getView();
}
