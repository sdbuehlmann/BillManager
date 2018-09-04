package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.MainViewBuilder;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import java.io.File;
import ch.buhls.billmanager.gui.view.listener.IMainViewListener;
import ch.buhls.billmanager.model.App;

/**
 *
 * @author simon
 */
public class StartUpAppController implements IMainViewListener
{

    private final MainViewBuilder viewBuilder;
    private final IGUIFramework framework;

    public StartUpAppController(IGUIFramework framework) {
        this.framework = framework;
        this.viewBuilder = new MainViewBuilder(this);

        this.viewBuilder.disableProjectDependentItems();

        this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());

        framework.displayMainMask(viewBuilder.getView(), viewBuilder.getSplitScreen());
    }

    @Override
    public void create() {
        /*XMLHandler.INSTANCE.load();
        History history = XMLHandler.INSTANCE.getHistory();*/

        File lastPath = null;

        File file = framework.openFileSaveDialoque("Erstellen eines Projektes", lastPath);

        if (file != null) {
            //history.setLastOpenFilePath(file.getAbsolutePath());
            //XMLHandler.INSTANCE.store();

            try {
                Project project = new ModelFascade().createProject(file);

                DataHandler dataHandler = new DataHandler(project);

                new WorkingAppController(viewBuilder, framework, project, dataHandler);

                /*new ListBillController(framework, project);
                
                new ListPersonsController(framework, project);
                new EditSettingsController(framework, framework, project);*/
            }
            catch (ModelException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void open() {
        File lastPath = App.INSTANCE.getLastPath();

        File file = framework.openFileChooser("Öffnen eines Projekts", lastPath);
        if (file != null) {
            App.INSTANCE.setLastPath(file);
            App.INSTANCE.addRecentOpenedProject(file.getPath());
            this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());

            try {
                Project project = new ModelFascade().loadProject(file);
                DataHandler dataHandler = new DataHandler(project);

                new WorkingAppController(viewBuilder, framework, project, dataHandler);
            }
            catch (ModelException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void openRecent(String desc) {
        File recentProject = new File(desc);
        if(!recentProject.exists()){
            App.INSTANCE.removeFromRecentOpenedProjects(desc);
            framework.showInfo_canNotOpenProject();
            return;
        }
        
        App.INSTANCE.addRecentOpenedProject(desc);
        this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());

        try {
            Project project = new ModelFascade().loadProject(recentProject);
            DataHandler dataHandler = new DataHandler(project);

            new WorkingAppController(viewBuilder, framework, project, dataHandler);
        }
        catch (ModelException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void save() {
        throw new RuntimeException("Not possible to call \"save\" in start up mode");
    }

    @Override
    public void saveAs() {
        throw new RuntimeException("Not possible to call \"save as\" in start up mode");
    }

    @Override
    public void showSettings() {
        throw new RuntimeException("Not possible to call \"show settings\" in start up mode");
    }

    @Override
    public void showGlobalSettings() {
        //new EditGlobalSettingsController(framework, framework);
    }

    @Override
    public void showMembersTable() {
        throw new RuntimeException("Not possible to call \"show members\" in start up mode");
    }

    @Override
    public void showComponentsTable() {
        throw new RuntimeException("Not possible to call \"show components\" in start up mode");
    }

    @Override
    public void showBillsTable() {
        throw new RuntimeException("Not possible to call \"show bills\" in start up mode");
    }

    @Override
    public void importMembers() {
        throw new RuntimeException("Not possible to call \"import member\" in start up mode");
    }

}
