package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.GUIStringCollection;
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
public class MainMaskController implements IMainViewListener
{

    private final MainViewBuilder viewBuilder;
    private final IGUIFramework framework;
    private final ModelFascade modelFascade;

    private Project project;

    public MainMaskController(IGUIFramework framework) {
        this.framework = framework;
        this.viewBuilder = new MainViewBuilder(this);
        this.modelFascade = new ModelFascade();

        this.viewBuilder.disableProjectDependentItems();
        this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());

        framework.displayMainMask(viewBuilder.getView(), viewBuilder.getSplitScreen(), viewBuilder.getHintBarContainer());
        framework.displayInfoHint(GUIStringCollection.getHintTxt_welcome());
    }

    @Override
    public void create() {
        File lastPath = App.INSTANCE.getLastPath();
        File projectFile = framework.openFileSaveDialoque("Erstellen eines Projektes", lastPath);
        if (projectFile != null) {
            App.INSTANCE.setLastPath(projectFile);

            try {
                project = modelFascade.createProject(projectFile);

                maskResetForNewProject(project);

                App.INSTANCE.addRecentOpenedProject(projectFile.getPath());
                this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());
                
                framework.displayInfoHint(GUIStringCollection.getHintTxt_projectOpened(projectFile));
            }
            catch (ModelException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void open() {
        File projectFile = framework.openFileChooser("Öffnen eines Projekts", App.INSTANCE.getLastPath());
        if (projectFile != null) {
            App.INSTANCE.setLastPath(projectFile);

            try {
                project = modelFascade.loadProject(projectFile);

                maskResetForNewProject(project);

                App.INSTANCE.addRecentOpenedProject(projectFile.getPath());
                viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());
                
                framework.displayInfoHint(GUIStringCollection.getHintTxt_projectOpened(projectFile));
            }
            catch (ModelException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void openRecent(String desc) {
        File projectFile = new File(desc);
        if (!projectFile.exists()) {
            App.INSTANCE.removeFromRecentOpenedProjects(desc);
            this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());

            framework.showInfo_canNotOpenProject();
            return;
        }

        try {
            project = modelFascade.loadProject(projectFile);

            maskResetForNewProject(project);

            App.INSTANCE.addRecentOpenedProject(projectFile.getPath());
            this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());
            
            framework.displayInfoHint(GUIStringCollection.getHintTxt_projectOpened(projectFile));
        }
        catch (ModelException ex) {
            App.INSTANCE.removeFromRecentOpenedProjects(desc);
            this.viewBuilder.setRecentOpenedProjects(App.INSTANCE.getRecentOpenedProjects());
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void showGlobalSettings() {
        new EditAppSettingsController(framework, project.getDataHandler());
    }

    @Override
    public void importMembers() {
        new ImportPersonsController(framework, project.getDataHandler());
    }

    // private methods
    private void maskResetForNewProject(Project project) {
        // clear old
        framework.closeAllMasks();

        framework.setAppTitle(project.getLocation().getName());

        new ListArticlesController(framework, project.getDataHandler());
        new ListRolesController(framework, project.getDataHandler());
        new ListPersonsController(framework, project.getDataHandler());
        new ListTemplatesController(framework, project.getDataHandler());
        new ListFinancialYearsController(framework, project.getDataHandler());
        new ListBillsController(framework, project.getDataHandler());

        project.getDataHandler().reloadArticlesBuffer();
        project.getDataHandler().reloadRolesBuffer();
        project.getDataHandler().getPersonsDataHandler().reloadPersonsBuffer();
        project.getDataHandler().reloadTemplatesBuffer();
        project.getDataHandler().reloadFinancialYearsBuffer();
        project.getDataHandler().reloadBillsBuffer();

        this.viewBuilder.enableProjectDependentItems();
    }
}
