package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.SplitScreen;
import ch.buhls.billmanager.gui.view.container.menues.MainMenuBarContainer;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import ch.buhls.billmanager.gui.view.listener.IMainViewListener;
import java.util.List;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class MainViewBuilder
{
    private final HintBarContainer hintBarContainer;
    
    private final BorderPane view;
    private final SplitScreen splitScreen;
    private final MainMenuBarContainer menuBarContainer;

    private IMainViewListener listener;

    public MainViewBuilder(IMainViewListener listener) {
        this.listener = listener;

        view = new BorderPane();
        splitScreen = new SplitScreen();
        menuBarContainer = new MainMenuBarContainer();
        hintBarContainer = new HintBarContainer();

        view.setTop(menuBarContainer.getMenuBar());
        VBox.setVgrow(splitScreen, Priority.ALWAYS);
        view.setCenter(new VBox(hintBarContainer.getView(), splitScreen));
        
        setRecentOpenedProjects(null);

        bindListener(listener);
    }

    private void bindListener(IMainViewListener listener) { 
        menuBarContainer.getMiNew().setOnAction((ActionEvent event)
                -> {
            listener.create();
        });

        menuBarContainer.getMiOpen().setOnAction((ActionEvent event)
                -> {
            listener.open();
        });

        menuBarContainer.getMiImportMembers().setOnAction((ActionEvent event)
                -> {
            listener.importMembers();
        });

        menuBarContainer.getMiGlobalSettings().setOnAction((ActionEvent event)
                -> {
            listener.showGlobalSettings();
        });

        menuBarContainer.getMiOpenRecent1().setOnAction((event)
                -> {
            listener.openRecent(menuBarContainer.getMiOpenRecent1().getText());
        });
        menuBarContainer.getMiOpenRecent2().setOnAction((event)
                -> {
            listener.openRecent(menuBarContainer.getMiOpenRecent2().getText());
        });
        menuBarContainer.getMiOpenRecent3().setOnAction((event)
                -> {
            listener.openRecent(menuBarContainer.getMiOpenRecent3().getText());
        });
        menuBarContainer.getMiOpenRecent4().setOnAction((event)
                -> {
            listener.openRecent(menuBarContainer.getMiOpenRecent4().getText());
        });
        menuBarContainer.getMiOpenRecent5().setOnAction((event)
                -> {
            listener.openRecent(menuBarContainer.getMiOpenRecent5().getText());
        });
    }

    public final void setRecentOpenedProjects(List<String> projects){
        if(projects == null || projects.isEmpty()){
            menuBarContainer.getmOpenRecent().setDisable(true);
            return;
        }
        
        // reset
        for(int cnt = 0; cnt < 5; cnt++){
            getOpenRecentItem(cnt).setVisible(false);
        }
        
        // fill new
        int cnt = 0;
        for(String project : projects){
            if(cnt + 1 <= projects.size()){
                MenuItem item = getOpenRecentItem(cnt);
                if(item != null){ // ToDo: Remove this dirty and fast fix
                    item.setText(project);
                    item.setVisible(true);
                    menuBarContainer.getmOpenRecent().setDisable(false);
                }

                
                cnt++;
            }else{
                break;
            }
        }
    }
    
    private MenuItem getOpenRecentItem(int index) {
        switch (index) {
            case 0:
                return menuBarContainer.getMiOpenRecent1();
            case 1:
                return menuBarContainer.getMiOpenRecent2();
            case 2:
                return menuBarContainer.getMiOpenRecent3();
            case 3:
                return menuBarContainer.getMiOpenRecent4();
            case 4:
                return menuBarContainer.getMiOpenRecent5();
            default:
                return null;
        }
    }

    public void disableProjectDependentItems() {
        menuBarContainer.getMiNew().setDisable(false);
        menuBarContainer.getMiOpen().setDisable(false);
        menuBarContainer.getMiImportMembers().setDisable(true);
        menuBarContainer.getMiGlobalSettings().setDisable(false);
    }

    public void enableProjectDependentItems() {
        menuBarContainer.getMiNew().setDisable(false);
        menuBarContainer.getMiOpen().setDisable(false);
        menuBarContainer.getMiImportMembers().setDisable(false);
        menuBarContainer.getMiGlobalSettings().setDisable(false);
    }

    // getter
    public BorderPane getView() {
        return view;
    }

    public SplitScreen getSplitScreen() {
        return splitScreen;
    }

    public HintBarContainer getHintBarContainer() {
        return hintBarContainer;
    }
    
    public MainMenuBarContainer getMasterMenuBarContainer() {
        return menuBarContainer;
    }

    public void setListener(IMainViewListener listener) {
        this.listener = listener;
        bindListener(listener);
    }

}
