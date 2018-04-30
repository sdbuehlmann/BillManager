
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;
import javafx.beans.property.ObjectProperty;
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
    
    private PersistanceFascade persistanceFascade;
    
    private ObservableList<GUIRole> rolesBuffer;
    private ObservableList<GUIPerson> personsBuffer;
    private ObservableList<GUIArticle> articlesBuffer;
    private ObservableList<GUIBill> billsBuffer;

    private ObjectProperty<GUIArticle> markedArticle;
    private ObjectProperty<GUIRole> markedRole;

    public DataHandler(Project project) {
        persistanceFascade = project.getDb();
        
        rolesBuffer = FXCollections.observableArrayList();
        personsBuffer = FXCollections.observableArrayList();
        articlesBuffer = FXCollections.observableArrayList();
        billsBuffer = FXCollections.observableArrayList();

        markedArticle = new SimpleObjectProperty<>();
        markedRole = new SimpleObjectProperty<>();
    }

    
    // articles
    public ObservableList<GUIArticle> getArticlesBuffer() {
        return articlesBuffer;
    }

    public void reloadArticlesBuffer(){
        articlesBuffer.clear();
        for(Article art : persistanceFascade.getAllHighestVersionArticles()){
            articlesBuffer.add(new GUIArticle(art));
        }
    }
    
    public ObservableList<GUIArticle> getVersions(GUIArticle guiArticle) {
        ObservableList<GUIArticle> versionsBuffer = FXCollections.observableArrayList();
        
        for(Article article : persistanceFascade.getAllVersions(guiArticle.getData())){
            versionsBuffer.add(new GUIArticle(article));
        }
        
        return versionsBuffer;
    }
    
    public ObjectProperty<GUIArticle> getMarkedArticle() {
        return markedArticle;
    }
    
    public GUIArticle createArticle(){
        return new GUIArticle(persistanceFascade.createArticle());
    }
    
    public GUIArticle editArticle(GUIArticle art){
        return new GUIArticle(persistanceFascade.editArticle(art.getData()));
    }
    
    public void storeArticle(GUIArticle art) throws PersistanceException{
        persistanceFascade.storeArticle(art.getData());
        reloadArticlesBuffer();
    }
    
    
    
    // roles
    public ObservableList<GUIRole> getRolesBuffer() {
        return rolesBuffer;
    }

    public void reloadRolesBuffer(){
        rolesBuffer.clear();
        for(Role role : persistanceFascade.getAllRoles()){
            rolesBuffer.add(new GUIRole(role));
        }
    }
    
    public ObjectProperty<GUIRole> getMarkedRole() {
        return markedRole;
    }
    
    public GUIRole createRole(){
        return new GUIRole(persistanceFascade.createRole());
    }
    
    public GUIRole editRole(GUIRole role){
        return new GUIRole(persistanceFascade.editRole(role.getData()));
    }
    
    public void deleteRole(GUIRole role){
        
    }
    
    public void storeRole(GUIRole role) throws PersistanceException{
        persistanceFascade.storeRole(role.getData());
        reloadRolesBuffer();
    }
    
    
    
    
    // persons
    public ObservableList<GUIPerson> getPersonsBuffer(){
        return personsBuffer;
    }
    
    public void reloadPersonsBuffer(){
        personsBuffer.clear();
        for(Person pers : persistanceFascade.getAllPersons()){
            personsBuffer.add(new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData())));
        }
    }
    
    public ObservableList<GUIPersonBaseData> getPersonBaseDataVersions(GUIPerson guiPerson) {
        ObservableList<GUIPersonBaseData> versionsBuffer = FXCollections.observableArrayList();
        
        for(PersonBaseData person : persistanceFascade.getAllPersonBaseDataVersions(guiPerson.getData().getPersonBaseData())){
            versionsBuffer.add(new GUIPersonBaseData(person));
        }
        
        return versionsBuffer;
    }
    
    public GUIPerson createPerson(){
        Person pers = persistanceFascade.createPerson();
        return new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData()));
    }
    
    public GUIPerson editPerson(GUIPerson guiPers){
        Person pers = persistanceFascade.editPerson(guiPers.getData());
        return new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData()));
    }
    
    public void storePerson(GUIPerson pers) throws PersistanceException{
        persistanceFascade.storePerson(pers.getData());
        reloadPersonsBuffer();
    }
    
    public void storePersonBaseDataAndPerson(GUIPerson pers) throws PersistanceException{
        persistanceFascade.storePersonBaseDataAndPerson(pers.getData());
        reloadPersonsBuffer();
    }
    
    public ObservableList<GUIPersonBaseData> getPersonVersions(GUIPerson guiPerson) {
        ObservableList<GUIPersonBaseData> versionsBuffer = FXCollections.observableArrayList();
        
        for(PersonBaseData data : persistanceFascade.getAllVersions(guiPerson.getBaseData().getData())){
            versionsBuffer.add(new GUIPersonBaseData(data));
        }
        
        return versionsBuffer;
    }
    
    public ObservableList<GUIRole> getPersonRoles(GUIPerson guiPerson){
        ObservableList<GUIRole> roles = FXCollections.observableArrayList();
        
        for(Role role : guiPerson.getData().getRoles()){
            roles.add(new GUIRole(role));
        }
        
        return roles;
    }
    
    public void addRoleToPerson(GUIPerson guiPerson, GUIRole guiRole) throws PersistanceException{
        guiPerson.getData().getRoles().add(guiRole.getData());
        storePerson(guiPerson);
    }
    
    public ObservableList<GUIPosition> getCopyOfPersonBusket(GUIPerson guiPerson){
        ObservableList<GUIPosition> busket = FXCollections.observableArrayList();
        
        for(Position pos : guiPerson.getData().getBusket()){
            busket.add(new GUIPosition(new Position(pos), new GUIArticle(pos.getArticle())));
        }
        
        return busket;
    }
    
    public void addPositionToPersonsBusket(GUIPerson guiPerson, GUIPosition guiPosition) throws PersistanceException{
        guiPerson.getData().getBusket().add(guiPosition.getData());
        storePerson(guiPerson);
    }
    
    
    
    // positions
    public GUIPosition createPosition(GUIArticle guiArticle){
        Position pos = new Position();
        pos.setArticle(guiArticle.getData());
        
        return new GUIPosition(pos, guiArticle);
    }
    
    public GUIPosition createPositionForPerson(GUIArticle guiArticle, GUIPerson guiPerson, int amount){
        Position pos = new Position();
        pos.setArticle(guiArticle.getData());
        pos.setNumber(amount);
        pos.setPosition(getHighestPosNumber(guiPerson.getData().getBusket())+POS_NR_STEP);
        
        return new GUIPosition(pos, guiArticle);
    }
    
    public GUIPosition duplicatePosition(GUIPosition modelPos){
        return new GUIPosition(new Position(modelPos.getData()), modelPos.getGuiArticle());
    }
    
    private int getHighestPosNumber(List<Position> positions){
        int highestPosNr = 0;
        for(Position pos : positions){
            if(pos.getNumber() > highestPosNr){
                highestPosNr = pos.getNumber();
            }
        }
        
        return highestPosNr;
    }
}
