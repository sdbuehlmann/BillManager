
package ch.buhls.billmanager.gui.view.container.menues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author simon
 */
public class MainMenuBarContainer
{
    private final MenuBar menuBar;
    
    private final Menu mEdit;
    private final Menu mTables;
    private final Menu mView;
    
    private final MenuItem miNew;
    private final MenuItem miOpen;
    private final MenuItem miSave;
    private final MenuItem miImportMembers;
    private final MenuItem miImportArticles;
    private final MenuItem miExportArticles;
    private final MenuItem miSaveAs;
    private final MenuItem miMembers;
    private final MenuItem miBills;
    private final MenuItem miComponents;
    private final MenuItem miSettings;
    private final MenuItem miGlobalSettings;
    
    public MainMenuBarContainer()
    {
        miNew = new MenuItem("Neu");
        miOpen = new MenuItem("Öffnen");
        miSave = new MenuItem("Speichern");
        miImportMembers = new MenuItem("Mitglieder importieren");
        miImportArticles = new MenuItem("Artikel importieren");
        miExportArticles = new MenuItem("Artikel exportieren");
        miSaveAs = new MenuItem("Speichern unter");
        miMembers = new MenuItem("Mitglieder");
        miBills = new MenuItem("Rechnungen");
        miComponents = new MenuItem("Artikel");
        miSettings = new MenuItem("Einstellungen");
        miGlobalSettings = new MenuItem("Globale Einstellungen");
        
        mTables = new Menu("Tabellen", null, miMembers, miBills, miComponents);
        mView = new Menu("Anzeigen", null, mTables, miSettings, miGlobalSettings);
        mEdit = new Menu("Datei", null, miNew, miOpen, miSave, miSaveAs, miImportMembers, miImportArticles, miExportArticles);
       
        menuBar = new MenuBar();
        menuBar.getMenus().add(mEdit);
        menuBar.getMenus().add(mView);
    }

    public Menu getmEdit()
    {
        return mEdit;
    }

    public Menu getmTables()
    {
        return mTables;
    }

    public Menu getmView()
    {
        return mView;
    }

    public MenuItem getMiNew()
    {
        return miNew;
    }

    public MenuItem getMiOpen()
    {
        return miOpen;
    }

    public MenuItem getMiSave()
    {
        return miSave;
    }

    public MenuItem getMiSaveAs()
    {
        return miSaveAs;
    }

    public MenuItem getMiMembers()
    {
        return miMembers;
    }

    public MenuItem getMiBills()
    {
        return miBills;
    }

    public MenuItem getMiComponents()
    {
        return miComponents;
    }

    public MenuItem getMiSettings()
    {
        return miSettings;
    }

    public MenuItem getMiGlobalSettings()
    {
        return miGlobalSettings;
    }

    public MenuItem getMiImportMembers()
    {
        return miImportMembers;
    }

    public MenuItem getMiImportArticles()
    {
        return miImportArticles;
    }

    public MenuItem getMiExportArticles()
    {
        return miExportArticles;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
