
package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.HintContainer;
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
    
    public boolean showConfirmationDialoque(String title, String header, String content);
    public boolean confirmToStore();
    public boolean confirmToAddRole();
    public boolean confirmToRemoveRole();
    public boolean confirmToAddArticle();
    
    public String showTextInputDialoque(String title, String headerTxt, String labelTextField);
    
    public enum Area
    {
        LEFT,
        RIGHT
    }
}
