package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIImportedPerson;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ImportPersonsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IImportPersonsBuilderListener;
import ch.buhls.billmanager.model.App;
import ch.buhls.billmanager.persistance.PersistanceException;
import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ImportPersonsController extends AController implements IImportPersonsBuilderListener
{

    private final ImportPersonsBuilder builder;

    private final ObservableList<GUIImportedPerson> persons;

    public ImportPersonsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.IMPORT_PERSONS_TAB_NAME);

        persons = FXCollections.observableArrayList();
        builder = new ImportPersonsBuilder(this, persons);

        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void removePerson(GUIImportedPerson pers) {
        persons.remove(pers);
    }

    @Override
    public void readFile() {
        File projectFile = framework.openFileChooser(GUIStringCollection.IMPORT_PERSONS_FILE_CHOOSER_TXT, App.INSTANCE.getLastPath());
        if (projectFile != null) {
            App.INSTANCE.setLastPath(projectFile);
            
            try {
                List<GUIImportedPerson> importedPersons = dataHandler.getPersonsDataHandler().importPersons(projectFile); 
                persons.addAll(importedPersons);
            }
            catch (PersistanceException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }
    
    @Override
    public void store() {
        for(GUIImportedPerson impPers : persons){
            try {
                dataHandler.getPersonsDataHandler().createPerson(impPers);
            }
            catch (PersistanceException ex) {
                framework.showExceptionDialoque(ex);
            }
        }
        
        dataHandler.reloadPersonsBuffer();
    }

}
