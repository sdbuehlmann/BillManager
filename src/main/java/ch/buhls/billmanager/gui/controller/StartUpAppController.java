
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.MainViewBuilder;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.persistance.files.History;
import ch.buhls.billmanager.persistance.files.XMLHandler;
import java.io.File;
import ch.buhls.billmanager.gui.view.listener.IMainViewListener;

/**
 *
 * @author simon
 */
public class StartUpAppController implements IMainViewListener
{
    private final MainViewBuilder viewBuilder;
    private final IGUIFramework framework;
    
    public StartUpAppController(IGUIFramework framework)
    {
        this.framework = framework;
        this.viewBuilder = new MainViewBuilder(this);
        
        this.viewBuilder.disableProjectDependentItems();
        
        framework.displayMainMask(viewBuilder.getView(), viewBuilder.getSplitScreen());
    }
    

    @Override
    public void create()
    {
        XMLHandler.INSTANCE.load();
        History history = XMLHandler.INSTANCE.getHistory();
        
        File lastPath = XMLHandler.loadFile(history.getLastOpenFilePath());
        
        File file  = framework.openFileSaveDialoque("Erstellen eines Projektes", lastPath);
        
        if(file != null)
        {
            history.setLastOpenFilePath(file.getAbsolutePath());
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
    public void open()
    {
        XMLHandler.INSTANCE.load();
        History history = XMLHandler.INSTANCE.getHistory();
        
        File lastPath = XMLHandler.loadFile(history.getLastOpenFilePath());
        
        File file = framework.openFileChooser("Öffnen eines Projekts", lastPath);
        if(file != null)
        {
            history.setLastOpenFilePath(file.getAbsolutePath());
            //XMLHandler.INSTANCE.store();
            
            try {
                Project project = new ModelFascade().loadProject(file);
                
                DataHandler dataHandler = new DataHandler(project);
                
                new WorkingAppController(viewBuilder, framework, project, dataHandler);
                
                
                /*new ListBillController(framework, project);
                
                new ListPersonsController(framework, project);
                new EditSettingsController(framework, framework, project);*/
            }
            catch (ModelException ex) {
                framework.showExceptionDialoque(ex);
            }
            
            /*Project db = PersistanceFacade.INSTANCE.open(file);
            new WorkingAppController(bar, framework, db);
            
            new ListBillController(framework, db);
            new ListArticlesController(framework, db);
            new ListPersonsController(framework, db);*/
        }
    }

    @Override
    public void save()
    {
        throw new RuntimeException("Not possible to call \"save\" in start up mode");
    }

    @Override
    public void saveAs()
    {
        throw new RuntimeException("Not possible to call \"save as\" in start up mode");
    }

    @Override
    public void showSettings()
    {
        throw new RuntimeException("Not possible to call \"show settings\" in start up mode");
    }
    
    @Override
    public void showGlobalSettings()
    {
        //new EditGlobalSettingsController(framework, framework);
    }

    @Override
    public void showMembersTable()
    {
        throw new RuntimeException("Not possible to call \"show members\" in start up mode");
    }

    @Override
    public void showComponentsTable()
    {
        throw new RuntimeException("Not possible to call \"show components\" in start up mode");
    }

    @Override
    public void showBillsTable()
    {
        throw new RuntimeException("Not possible to call \"show bills\" in start up mode");
    }

    @Override
    public void importMembers()
    {
        throw new RuntimeException("Not possible to call \"import member\" in start up mode");
    }

    @Override
    public void importArticles()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportArticles()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
