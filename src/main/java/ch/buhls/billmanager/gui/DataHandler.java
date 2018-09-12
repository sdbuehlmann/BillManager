package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.data.AppSettingsData;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUICreateBillsData;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.model.App;
import ch.buhls.billmanager.model.AppException;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.model.data.TemplateData;
import ch.buhls.billmanager.model.data.TemplatePosition;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
 *
 * @author simon
 */
public class DataHandler
{

    private final static int POS_NR_STEP = 10;

    private final Project project;
    private PersistanceFascade persistanceFascade;
    private ModelFascade modelFascade;

    private ObservableList<GUIRole> rolesBuffer;
    private ObservableList<GUIArticle> articlesBuffer;
    private ObservableList<GUIBill> billsBuffer;
    private ObservableList<GUITemplate> templatesBuffer;
    private ObservableList<GUIFinancialYear> financialYearsBuffer;

    private ObjectProperty<GUIArticle> markedArticleProperty;
    private ObjectProperty<GUIRole> markedRoleProperty;
    private ObjectProperty<GUIFinancialYear> markedYearProperty;
    
    private final BooleanProperty showDBInfosProperty;
    
    private final PersonsDataHandler personsDataHandler;

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
        
        personsDataHandler = new PersonsDataHandler(project);
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
        guiPerson.getData().getBusket().add(guiPosition.getData());

        storePosition(guiPosition);
        personsDataHandler.storePerson(guiPerson);
    }

    public void mergeAndStoreBusket(GUIPerson guiPerson,
            ObservableList<GUIPosition> guiBusket) throws PersistanceException {

        List<Position> busket = new ArrayList<>(guiBusket.size());
        for (GUIPosition guiPos : guiBusket) {
            busket.add(guiPos.getData());
        }

        persistanceFascade.mergeAndStoreBusket(guiPerson.getData(), busket);
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
            File dest = getTemplateFile(template);
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

    public File getTemplateFile(GUITemplate template) {
        return new File(project.getLocationTemplates(), project.createTemplateName(template.getData().getId()));
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
    public GUICreateBillsData createBills(List<GUIPerson> persons){
        GUICreateBillsData data = new GUICreateBillsData(templatesBuffer, financialYearsBuffer, persons);
        data.getPaymentDeadlineInDays().set(App.INSTANCE.getLastPaymentDeadlineInDays());
        data.getLocation().set(App.INSTANCE.getLastLocation());
        
        return data;
    }
    
    private File createBillPDF(GUIPerson person, String location, String date, String billNr, File template) throws ModelException, AppException {
        TemplateData templateData = new TemplateData()
                .setPrename(person.getBaseData().getPrename().get())
                .setName(person.getBaseData().getName().get())
                .setStreet(person.getBaseData().getStreet().get())
                .setPlz(Integer.toString(person.getBaseData().getPostalcode().get()))
                .setCity(person.getBaseData().getCity().get())
                .setSalutation(person.getBaseData().getSalutation().get())
                .setTitle(person.getBaseData().getTitle().get())
                .setNumber(billNr)
                .setLocation(location)
                .setDate(date)
                .setTemplate(template.getAbsolutePath());

        int total = 0;
        for (Position pos : person.getData().getBusket()) {
            TemplatePosition templatePos = new TemplatePosition()
                    .setPosition(pos.getPosition())
                    .setNumber(pos.getNumber())
                    .setPrice(pos.getArticle().getCosts())
                    .setSum(pos.getArticle().getCosts() * pos.getNumber())
                    .setFirstLine(pos.getArticle().getTitle())
                    .setSecondLine(pos.getArticle().getDescription());

            templateData.getPositions().add(templatePos);

            total = total + templatePos.getSum();
        }

        templateData.setTotal(total);

        // create temp file
        File tempPDF = new File(project.getLocationBills(), ModelFascade.createBillFilename(billNr, templateData.getName(), ".pdf"));
        modelFascade.createPDF(tempPDF, template, App.INSTANCE.getInkscapePath(), templateData);

        return tempPDF;
    }

    private String dateToString(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(date.getDay()));
        sb.append(". ");

        switch (date.getMonth()) {
            case 0:
                sb.append("Januar ");
                break;
            case 1:
                sb.append("Februar ");
                break;
            case 2:
                sb.append("März ");
                break;
            case 3:
                sb.append("April ");
                break;
            case 4:
                sb.append("Mai ");
                break;
            case 5:
                sb.append("Juni ");
                break;
            case 6:
                sb.append("Juli ");
                break;
            case 7:
                sb.append("August ");
                break;
            case 8:
                sb.append("September ");
                break;
            case 9:
                sb.append("Oktober ");
                break;
            case 10:
                sb.append("November ");
                break;
            case 11:
                sb.append("Dezember ");
                break;
        }
        
        sb.append(Integer.toString(date.getYear() + 1900));
        
        return sb.toString();
    }

    public void storeBills(GUICreateBillsData data) throws Exception {
        for (GUIPerson person : data.getPersons()) {
            // create db entry
            Bill bill = persistanceFascade.createBill();

            bill.setBillState(Bill.BillState.SENDET);
            bill.setPaymentPeriodInDays(data.getPaymentDeadlineInDays().get());
            bill.setDateSendet(Date.from(data.getDate().get().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            bill.setLocation(data.getLocation().get());
            bill.setTemplate(data.getSelectedTemplate().get().getData());
            bill.setFinancialYear(data.getSelectedYear().get().getData());
            bill.getPositions().addAll(person.getData().getBusket());
            bill.setPersonBaseData(person.getBaseData().getData());

            persistanceFascade.storeBill(bill);

            // manage bill number
            String fullBillNr = data.getSelectedYear().get().getData().getBillIdPrefix() + "-" + bill.getId();

            // manage template data
            File templateFile = getTemplateFile(data.getSelectedTemplate().get());

            // create file
            File pdfFile = createBillPDF(person, data.getLocation().get(), dateToString(bill.getDateSendet()), fullBillNr, templateFile);
            
            person.getData().getBills().add(bill);
            person.getData().getBusket().clear();
            
            persistanceFascade.storePerson(person.getData());
        }

        reloadBillsBuffer();
        personsDataHandler.reloadPersonsBuffer();
    }

    public GUIBill editBill(GUIBill bill){
        return new GUIBill(persistanceFascade.editBill(bill.getData()),bill.getTemplate(), bill.getYear(), bill.getPerson());
    }
    
    public void storeBill(GUIBill bill) throws PersistanceException{
        persistanceFascade.storeBill(bill.getData());
        reloadBillsBuffer();
    }
    
    public ObservableList<GUIBill> getBillsBuffer() {
        return billsBuffer;
    }

    public void reloadBillsBuffer() {
        billsBuffer.clear();
        for (Bill bill : persistanceFascade.getAllBills()) {
            billsBuffer.add(
                    new GUIBill(
                            bill,
                            new GUITemplate(bill.getTemplate()),
                            new GUIFinancialYear(bill.getFinancialYear()),
                            new GUIPersonBaseData(bill.getPersonBaseData())));
        }
    }
    
    // app settings
    public AppSettingsData getAppSettingsData(){
        AppSettingsData data = new AppSettingsData();
        try {
            data.getInkscapePathProperty().set(App.INSTANCE.getInkscapePath().getPath());
        }
        catch (AppException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.getShowDBInfosProperty().set(App.INSTANCE.isShowDBInfos());
        
        return data;
    }
    
    public void storeAppSettingsData(AppSettingsData data){
        App.INSTANCE.setInkscapePath(data.getInkscapePathProperty().get());
        App.INSTANCE.setShowDBInfos(data.getShowDBInfosProperty().get());
        
        if(data.getShowDBInfosProperty().get() != showDBInfosProperty.get()){
            // update
            showDBInfosProperty.set(data.getShowDBInfosProperty().get());
        }
    }

    public BooleanProperty getShowDBInfosProperty() {
        return showDBInfosProperty;
    }
    
    // getter
    public PersonsDataHandler getPersonsDataHandler(){
        return personsDataHandler;
    }
}
