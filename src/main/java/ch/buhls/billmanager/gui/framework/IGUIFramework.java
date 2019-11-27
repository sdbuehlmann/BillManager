
package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.SplitScreen;
import java.io.File;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 *
 * @author simon
 */
public interface IGUIFramework
{
    public void setAppTitle(String title);
    
    // masks
    public void displayMainMask(Parent parent, SplitScreen splitScreen, HintBarContainer hintBarContainer);
    public ITabHandle displayMask(Node node, String title, Area area);
    public void closeAllMasks();
    
    // hints
    //public IHintHandle displayHint(HintContainer hintContainer);
    public void displayInfoHint(String text);
    public IHintHandle displayMarkedHint(String text, IHintListener listener);
    
    // dialoques
    public File openPathChooser(String title, File lastDirectory);
    public File openFileChooser(String title, File lastDirectory);
    
    public File openFileSaveDialoque(String title, File lastDirectory);
    
    public void showExceptionDialoque(Exception ex);
    public void showInfoDialoque(String header, String content);
    public void showInfo_canNotOpenProject();
    
    public boolean showConfirmDialoque(DialoquesStringsTO to);
    public boolean showConfirmDialoque(String header, String content);
    public boolean showConfirmToStoreDialoque(String content);
    
    public String showTextInputDialoque(String headerTxt, String labelTextField);
    public String showTextInputDialoque(DialoquesStringsTO to);
    
    public enum Area
    {
        LEFT,
        RIGHT
    }
    
    public StringCollections getStringCollections();
}
