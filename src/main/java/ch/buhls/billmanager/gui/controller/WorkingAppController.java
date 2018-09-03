
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.MainViewBuilder;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.gui.view.listener.IMainViewListener;

/**
 *
 * @author simon
 */
public class WorkingAppController implements IMainViewListener
{
    private Project project;
    private final MainViewBuilder viewBuilder;
    private IGUIFramework framework;
    private DataHandler dataHandler;
    
    public WorkingAppController(MainViewBuilder viewBuilder, IGUIFramework framework, Project project, DataHandler dataHandler)
    {
        this.viewBuilder = viewBuilder;
        this.framework = framework;
        this.project = project;
        this.dataHandler = dataHandler;
        
        this.viewBuilder.enableProjectDependentItems();
        this.viewBuilder.setListener(this);
        
        this.setAppTitle(false);
        
        new ListArticlesController(framework, dataHandler);
        new ListRolesController(framework, dataHandler);
        new ListPersonsController(framework, dataHandler);
        new ListTemplatesController(framework, dataHandler);
        new ListFinancialYearsController(framework, dataHandler);
        new ListBillsController(framework, dataHandler);
        
        dataHandler.reloadArticlesBuffer();
        dataHandler.reloadRolesBuffer();
        dataHandler.reloadPersonsBuffer();
        dataHandler.reloadTemplatesBuffer();
        dataHandler.reloadFinancialYearsBuffer();
        dataHandler.reloadBillsBuffer();
    }

    @Override
    public void create()
    {
        new StartUpAppController(framework).create();
    }

    @Override
    public void open()
    {
        
    }

    @Override
    public void save()
    {
        /*if(this.project.getLocationData()==null)
        {
            this.saveAs();
        }
        else
        {
            try
            {
                //PersistanceFacade.INSTANCE.save(db);
                project.getTransactionService().writeTransactions();
                this.setAppTitle(false);
            }
            catch (CanNotWriteTransactionException ex)
            {
                guiManager.showExceptionDialoque(ex);
            }
        }*/
    }

    @Override
    public void saveAs()
    {
        /*File file = guiManager.openFileSaveDialoque("Speicherort wählen", null);
        
        if(file != null)
        {
            file.delete();
            
            this.project.setLocationData(file);
            
            try
            {
                //PersistanceFacade.INSTANCE.save(db);
                project.getTransactionService().writeTransactions();
                this.setAppTitle(false);
            }
            catch (CanNotWriteTransactionException ex)
            {
                guiManager.showExceptionDialoque(ex);
            }
        }*/
    }

    @Override
    public void showSettings()
    {
        //new EditSettingsController(guiManager, guiManager, project);
    }
    
    @Override
    public void showGlobalSettings()
    {
        //new EditGlobalSettingsController(guiManager, guiManager);
    }

    @Override
    public void showMembersTable()
    {
        //new ListPersonsController(guiManager, project);
    }

    @Override
    public void showComponentsTable()
    {
        //new ListArticlesController(guiManager, project);
    }

    @Override
    public void showBillsTable()
    {
       //new ListBillController(guiManager, project);
    }

    private void setAppTitle(boolean dataToSaveAvailable)
    {
        /*String title = "HBC Münsingen Manager - " + (project.getLocationData()!=null ? project.getLocationData().getName() : "neu");
        
        if(dataToSaveAvailable)
        {
            title = title + "*";
        }
        
        this.guiManager.setTitle(title);*/
    }

    @Override
    public void importMembers()
    {
        /*History history = HistoryService.INSTANCE.getHistory();
        
        File lastPath = HistoryService.loadFile(history.getLastImportFilePath());
        
        File file = guiManager.openFileChooser("Wählen eines members.csv", lastPath);
        if(file != null)
        {
            history.setLastImportFilePath(file.getAbsolutePath());
            HistoryService.INSTANCE.store();
            
            try
            {
                
                List<GUIPerson> persons = PersistanceFacade.INSTANCE.importPersons(file);
                
                // change id's of members
                int highestID = project.getHighestPersonsID();
                for(GUIPerson person : persons)
                {
                    highestID++;
                    person.getId().set(highestID);
                }
                
                new ImportMembersController(guiManager, FXCollections.observableArrayList(persons), project);
            }
            catch(Exception ex)
            {
                guiManager.showExceptionDialoque(ex);
            }
        }*/
    }

    @Override
    public void importArticles()
    {
        //new ImportArticlesController(guiManager, project);
    }

    @Override
    public void exportArticles()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
