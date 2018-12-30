package ch.buhls.billmanager.gui.viewModel;

import ch.buhls.billmanager.gui.data.CopiedDataObjectContainer;
import ch.buhls.billmanager.gui.data.GUIAppSettings;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.model.App;
import ch.buhls.billmanager.model.AppException;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Does only convert the gui data to the model data. No further functionality.
 * @author simon
 */
public class DataHandler
{

    private final static int POS_NR_STEP = 10;

    private final Project project;
    private PersistanceFascade persistanceFascade;
    private ModelFascade modelFascade;
    
    // buffers
    private ObservableList<GUIRole> rolesBuffer;
    private ObservableList<GUIArticle> articlesBuffer;
    private final ObservableList<GUIBill> billsBuffer;
    private ObservableList<GUITemplate> templatesBuffer;
    private ObservableList<GUIFinancialYear> financialYearsBuffer;

    private ObjectProperty<GUIArticle> markedArticleProperty;
    private ObjectProperty<GUIRole> markedRoleProperty;
    private ObjectProperty<GUIFinancialYear> markedYearProperty;

    private final BooleanProperty showDBInfosProperty;

    private final PersonViewModel personsDataHandler;
    private final BillViewModel billViewModel;
 
    public DataHandler(Project project) {
        this.project = project;
        persistanceFascade = project.getDb();
        modelFascade = new ModelFascade();
        
        rolesBuffer = FXCollections.observableArrayList();
        articlesBuffer = FXCollections.observableArrayList();
        billsBuffer = FXCollections.observableArrayList();
        templatesBuffer = FXCollections.observableArrayList();
        financialYearsBuffer = FXCollections.observableArrayList();

        markedArticleProperty = new SimpleObjectProperty<>();
        markedRoleProperty = new SimpleObjectProperty<>();
        markedYearProperty = new SimpleObjectProperty<>();

        showDBInfosProperty = new SimpleBooleanProperty(App.INSTANCE.isShowDBInfos());

        personsDataHandler = new PersonViewModel(project);
        billViewModel = new BillViewModel(project);
    }
   
    // articles
    public ObservableList<GUIArticle> getArticlesBuffer() {
        return articlesBuffer;
    }

    public void reloadArticlesBuffer() {
        articlesBuffer.clear();
        for (Article art : persistanceFascade.getAllHighestVersionArticles()) {
            articlesBuffer.add(new GUIArticle(art));
        }
    }

    public ObservableList<GUIArticle> getVersions(GUIArticle guiArticle) {
        ObservableList<GUIArticle> versionsBuffer = FXCollections.observableArrayList();

        for (Article article : persistanceFascade.getAllVersions(guiArticle.getData())) {
            versionsBuffer.add(new GUIArticle(article));
        }

        return versionsBuffer;
    }

    public ObjectProperty<GUIArticle> getMarkedArticleProperty() {
        return markedArticleProperty;
    }

    public GUIArticle createArticle() {
        return new GUIArticle(persistanceFascade.createArticle());
    }

    public GUIArticle editArticle(GUIArticle art) {
        return new GUIArticle(persistanceFascade.editArticle(art.getData()));
    }

    public void storeArticle(GUIArticle art) throws PersistanceException {
        persistanceFascade.storeArticle(art.getData());
        reloadArticlesBuffer();
    }

    // roles
    public ObservableList<GUIRole> getRolesBuffer() {
        return rolesBuffer;
    }

    public void reloadRolesBuffer() {
        rolesBuffer.clear();
        for (Role role : persistanceFascade.getAllRoles()) {
            rolesBuffer.add(new GUIRole(role));
        }
    }

    public ObjectProperty<GUIRole> getMarkedRole() {
        return markedRoleProperty;
    }

    public GUIRole createRole() {
        return new GUIRole(persistanceFascade.createRole());
    }

    public GUIRole editRole(GUIRole role) {
        return new GUIRole(persistanceFascade.editRole(role.getData()));
    }

    public void deleteRole(GUIRole role) {

    }

    public void storeRole(GUIRole role) throws PersistanceException {
        persistanceFascade.storeRole(role.getData());
        reloadRolesBuffer();
    }

    // positions
    public GUIPosition createPosition(GUIArticle guiArticle) {
        Position pos = persistanceFascade.createPosition(guiArticle.getData());

        return new GUIPosition(pos, guiArticle);
    }

    public GUIPosition createPosition(GUIArticle guiArticle, int position, int amount) {
        Position pos = persistanceFascade.createPosition(guiArticle.getData());

        pos.setNumber(amount);
        pos.setPosition(position);

        return new GUIPosition(pos, guiArticle);
    }

    public GUIPosition duplicatePosition(GUIPosition modelPos) {
        return new GUIPosition(new Position(modelPos.getData()), modelPos.getGuiArticle());
    }

    public void deletePosition(GUIPosition guiPosition) throws PersistanceException {
        persistanceFascade.deletePosition(guiPosition.getData());
    }

    public void storePosition(GUIPosition guiPosition) throws PersistanceException {
        persistanceFascade.storePosition(guiPosition.getData());
    }

    public void updatePosition(GUIPosition guiPosition) throws PersistanceException {
        persistanceFascade.updatePosition(guiPosition.getData());
    }

    public void addPositionAndStoreBusket(GUIPerson guiPerson, GUIPosition guiPosition) throws PersistanceException {
        CopiedDataObjectContainer<GUIPerson> copiedDataContainer = personsDataHandler.copyPerson(guiPerson);
        
        copiedDataContainer.getCopiedDataObject().getData().getBusket().add(guiPosition.getData());

        storePosition(guiPosition);
        personsDataHandler.updatePerson(copiedDataContainer);
    }

    public void mergeAndStoreBusket(GUIPerson guiPerson, ObservableList<GUIPosition> guiBusket) throws PersistanceException {

        List<Position> busket = new ArrayList<>(guiBusket.size());
        for (GUIPosition guiPos : guiBusket) {
            busket.add(guiPos.getData());
        }

        persistanceFascade.mergeAndStoreBusket(guiPerson.getData(), busket);
        
        guiPerson.getNrOfArtInBusket().set(0);
    }

    public int getNextPositionNr(List<GUIPosition> guiPositions) {
        int highestPosNr = 0;
        for (GUIPosition pos : guiPositions) {
            if (pos.getPosition().get() > highestPosNr) {
                highestPosNr = pos.getPosition().get();
            }
        }

        return highestPosNr + POS_NR_STEP;
    }

    public int getNextPositionNr(GUIPerson guiPerson) {
        int highestPosNr = 0;
        for (Position pos : guiPerson.getData().getBusket()) {
            if (pos.getPosition() > highestPosNr) {
                highestPosNr = pos.getPosition();
            }
        }

        return highestPosNr + POS_NR_STEP;
    }

    // templates
    public ObservableList<GUITemplate> getTemplatesBuffer() {
        return templatesBuffer;
    }

    public void reloadTemplatesBuffer() {
        templatesBuffer.clear();
        for (BillTemplate template : persistanceFascade.getAllBillTemplates()) {
            templatesBuffer.add(new GUITemplate(template));
        }
    }

    public GUITemplate createTemplate() {
        return new GUITemplate(new BillTemplate());
    }

    public GUITemplate editTemplate(GUITemplate template) {
        return new GUITemplate(persistanceFascade.editBillTemplate(template.getData()));
    }

    public void storeTemplate(GUITemplate template, File svg) throws PersistanceException {
        persistanceFascade.storeBillTemplate(template.getData());
        reloadTemplatesBuffer();

        // copy file
        try {
            File dest = project.getTemplateFile(template.getData());
            Files.copy(svg.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ex) {
            throw new PersistanceException(ex);
        }
    }

    public void updateTemplate(GUITemplate template) throws PersistanceException {
        persistanceFascade.storeBillTemplate(template.getData());
        reloadTemplatesBuffer();
    }

    

    // financial years
    public ObservableList<GUIFinancialYear> getFinancialYearsBuffer() {
        return financialYearsBuffer;
    }

    public void reloadFinancialYearsBuffer() {
        financialYearsBuffer.clear();
        for (FinancialYear year : persistanceFascade.getAllFinancialYears()) {
            financialYearsBuffer.add(new GUIFinancialYear(year));
        }
    }

    public GUIFinancialYear createFinancialYear() {
        return new GUIFinancialYear(persistanceFascade.createFinancialYear());
    }

    public GUIFinancialYear editFinancialYear(GUIFinancialYear year) {
        return new GUIFinancialYear(persistanceFascade.editFinancialYear(year.getData()));
    }

    public void storeFinancialYear(GUIFinancialYear year) throws PersistanceException {
        persistanceFascade.storeFinancialYear(year.getData());
        reloadFinancialYearsBuffer();
    }

    public ObjectProperty<GUIFinancialYear> getMarkedYear() {
        return markedYearProperty;
    }

    // bills
    
    // app settings
    public GUIAppSettings getAppSettingsData() {
        GUIAppSettings data = new GUIAppSettings();
        try {
            data.getInkscapePathProperty().set(App.INSTANCE.getInkscapePath().getPath());
        }
        catch (AppException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.getShowDBInfosProperty().set(App.INSTANCE.isShowDBInfos());

        return data;
    }

    public void storeAppSettingsData(GUIAppSettings data) {
        App.INSTANCE.setInkscapePath(data.getInkscapePathProperty().get());
        App.INSTANCE.setShowDBInfos(data.getShowDBInfosProperty().get());

        if (data.getShowDBInfosProperty().get() != showDBInfosProperty.get()) {
            // update
            showDBInfosProperty.set(data.getShowDBInfosProperty().get());
        }
    }

    public BooleanProperty getShowDBInfosProperty() {
        return showDBInfosProperty;
    }

    // getter
    public PersonViewModel getPersonsDataHandler() {
        return personsDataHandler;
    }

    public BillViewModel getBillViewModel() {
        return billViewModel;
    }
}
