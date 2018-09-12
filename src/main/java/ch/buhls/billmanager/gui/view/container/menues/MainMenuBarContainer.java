
package ch.buhls.billmanager.gui.view.container.menues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author simon
 */
public class MainMenuBarContainer
{
    private final MenuBar menuBar;
    
    private final Menu mEdit;
    
    private final MenuItem miNew;
    private final MenuItem miOpen;
    
    private final Menu mOpenRecent;
    private final MenuItem miOpenRecent1;
    private final MenuItem miOpenRecent2;
    private final MenuItem miOpenRecent3;
    private final MenuItem miOpenRecent4;
    private final MenuItem miOpenRecent5;
    
    private final MenuItem miImportMembers;
    
    
    private final Menu mView;
    private final MenuItem miGlobalSettings;
    
    public MainMenuBarContainer()
    {
        miNew = new MenuItem("Neu");
        miOpen = new MenuItem("Öffnen");
        miOpenRecent1 = new MenuItem();
        miOpenRecent2 = new MenuItem();
        miOpenRecent3 = new MenuItem();
        miOpenRecent4 = new MenuItem();
        miOpenRecent5 = new MenuItem();
        mOpenRecent = new Menu("Kürzlich verwendet", null, miOpenRecent1, miOpenRecent2, miOpenRecent3, miOpenRecent4, miOpenRecent5);
        miImportMembers = new MenuItem("Mitglieder importieren");
        miGlobalSettings = new MenuItem("Globale Einstellungen");
        
        mView = new Menu("Anzeigen", null, miGlobalSettings);
        mEdit = new Menu("Datei", null, miNew, miOpen, mOpenRecent, new SeparatorMenuItem(), miImportMembers);
       
        menuBar = new MenuBar();
        menuBar.getMenus().add(mEdit);
        menuBar.getMenus().add(mView);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Menu getmEdit() {
        return mEdit;
    }

    public MenuItem getMiNew() {
        return miNew;
    }

    public MenuItem getMiOpen() {
        return miOpen;
    }

    public Menu getmOpenRecent() {
        return mOpenRecent;
    }

    public MenuItem getMiOpenRecent1() {
        return miOpenRecent1;
    }

    public MenuItem getMiOpenRecent2() {
        return miOpenRecent2;
    }

    public MenuItem getMiOpenRecent3() {
        return miOpenRecent3;
    }

    public MenuItem getMiOpenRecent4() {
        return miOpenRecent4;
    }

    public MenuItem getMiOpenRecent5() {
        return miOpenRecent5;
    }

    public MenuItem getMiImportMembers() {
        return miImportMembers;
    }

    public Menu getmView() {
        return mView;
    }

    public MenuItem getMiGlobalSettings() {
        return miGlobalSettings;
    }
    
    
}
