
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
    private final Menu mTables;
    private final Menu mView;
    
    private final MenuItem miNew;
    private final MenuItem miOpen;
    private final Menu mOpenRecent;
    private final MenuItem miOpenRecent1;
    private final MenuItem miOpenRecent2;
    private final MenuItem miOpenRecent3;
    private final MenuItem miOpenRecent4;
    private final MenuItem miOpenRecent5;
    private final MenuItem miSave;
    private final MenuItem miSaveAs;
    private final MenuItem miImportMembers;
    private final MenuItem miImportArticles;
    private final MenuItem miExportArticles;
    
    private final MenuItem miMembers;
    private final MenuItem miBills;
    private final MenuItem miComponents;
    private final MenuItem miSettings;
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
        mEdit = new Menu("Datei", null, miNew, miOpen, mOpenRecent, miSave, miSaveAs, new SeparatorMenuItem(), miImportMembers, miImportArticles, miExportArticles);
       
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
    
    

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
