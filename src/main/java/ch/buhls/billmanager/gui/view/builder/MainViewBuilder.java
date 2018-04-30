
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.view.container.SplitScreen;
import ch.buhls.billmanager.gui.view.container.menues.MainMenuBarContainer;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import ch.buhls.billmanager.gui.view.listener.IMainViewListener;

/**
 *
 * @author simon
 */
public class MainViewBuilder
{
    private final BorderPane view;
    private final SplitScreen splitScreen;
    private final MainMenuBarContainer menuBarContainer;
    
    private IMainViewListener listener;
    
    public MainViewBuilder(IMainViewListener listener) {
        this.listener = listener;
        
        view = new BorderPane();
        splitScreen = new SplitScreen();
        menuBarContainer = new MainMenuBarContainer();
        
        view.setTop(menuBarContainer.getMenuBar());
        view.setCenter(splitScreen);
        
        connectListener(listener);
    }

    private void connectListener(IMainViewListener listener)
    {
        menuBarContainer.getMiBills().setOnAction((ActionEvent event) ->
        {
            listener.showBillsTable();
        });
        
        menuBarContainer.getMiComponents().setOnAction((ActionEvent event) ->
        {
            listener.showComponentsTable();
        });
        
        menuBarContainer.getMiMembers().setOnAction((ActionEvent event) ->
        {
            listener.showMembersTable();
        });
        
        menuBarContainer.getMiNew().setOnAction((ActionEvent event) ->
        {
            listener.create();
        });
        
        menuBarContainer.getMiOpen().setOnAction((ActionEvent event) ->
        {
            listener.open();
        });
        
        menuBarContainer.getMiSave().setOnAction((ActionEvent event) ->
        {
            listener.save();
        });
        
        menuBarContainer.getMiSaveAs().setOnAction((ActionEvent event) ->
        {
            listener.saveAs();
        });
        
        menuBarContainer.getMiImportMembers().setOnAction((ActionEvent event) ->
        {
            listener.importMembers();
        });
        
        menuBarContainer.getMiImportArticles().setOnAction((event) ->
        {
            listener.importArticles();
        });
        
        menuBarContainer.getMiSettings().setOnAction((ActionEvent event) ->
        {
            listener.showSettings();
        });
        
        menuBarContainer.getMiGlobalSettings().setOnAction((ActionEvent event) ->
        {
            listener.showGlobalSettings();
        });
    }
    
    public void disableProjectDependentItems()
    {
        menuBarContainer.getMiNew().setDisable(false);
        menuBarContainer.getMiOpen().setDisable(false);
        menuBarContainer.getMiSave().setDisable(true);
        menuBarContainer.getMiSaveAs().setDisable(true);
        menuBarContainer.getMiImportMembers().setDisable(true);
        menuBarContainer.getMiImportArticles().setDisable(true);
        menuBarContainer.getMiExportArticles().setDisable(true);
        menuBarContainer.getMiMembers().setDisable(true);
        menuBarContainer.getMiBills().setDisable(true);
        menuBarContainer.getMiComponents().setDisable(true);
        menuBarContainer.getMiSettings().setDisable(true);
        menuBarContainer.getMiGlobalSettings().setDisable(false);
    }
    
    public void enableProjectDependentItems()
    {
        menuBarContainer.getMiNew().setDisable(false);
        menuBarContainer.getMiOpen().setDisable(false);
        menuBarContainer.getMiSave().setDisable(false);
        menuBarContainer.getMiSaveAs().setDisable(false);
        menuBarContainer.getMiImportMembers().setDisable(false);
        menuBarContainer.getMiImportArticles().setDisable(false);
        menuBarContainer.getMiExportArticles().setDisable(false);
        menuBarContainer.getMiMembers().setDisable(false);
        menuBarContainer.getMiBills().setDisable(false);
        menuBarContainer.getMiComponents().setDisable(false);
        menuBarContainer.getMiSettings().setDisable(false);
        menuBarContainer.getMiGlobalSettings().setDisable(false);
    }
    
    // getter
    public BorderPane getView() {
        return view;
    }

    public SplitScreen getSplitScreen() {
        return splitScreen;
    }

    public MainMenuBarContainer getMasterMenuBarContainer() {
        return menuBarContainer;
    }

    public IMainViewListener getListener() {
        return listener;
    }

    public void setListener(IMainViewListener listener) {
        this.listener = listener;
        connectListener(listener);
    }
    
    
}
