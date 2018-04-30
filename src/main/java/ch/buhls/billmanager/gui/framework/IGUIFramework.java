
package ch.buhls.billmanager.gui.framework;

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
    public void displayMainMask(Parent parent, SplitScreen splitScreen);
    
    public ITabHandle displayMask(Node node, String title, Area area);
    public File openPathChooser(String title, File lastDirectory);
    public File openFileChooser(String title, File lastDirectory);
    
    // dialoques
    public File openFileSaveDialoque(String title, File lastDirectory);
    public void showExceptionDialoque(Exception ex);
    public boolean showConfirmationDialoque(String title, String header, String content);
    public boolean confirmToStore();
    public boolean confirmToAddRole();
    public boolean confirmToRemoveRole();
    public boolean confirmToAddArticle();
    
    public enum Area
    {
        LEFT,
        RIGHT
    }
}
