
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class PersonsDataHandler
{
    private final Project project;
    private final PersistanceFascade persistanceFascade;
    private final ModelFascade modelFascade;

    private ObservableList<GUIPerson> personsBuffer;
    
    private GUIRole roleToShow;
    private GUIRole roleToHide;

    public PersonsDataHandler(Project project) {
        this.project = project;
        persistanceFascade = project.getDb();
        modelFascade = new ModelFascade();

        personsBuffer = FXCollections.observableArrayList();
    }
    
    public ObservableList<GUIPerson> getPersonsBuffer() {
        return personsBuffer;
    }

    public void reloadPersonsBuffer() {
        personsBuffer.clear();
        for (Person pers : persistanceFascade.getAllPersons()) {
            personsBuffer.add(new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData())));
        }
    }

    public ObservableList<GUIPersonBaseData> getPersonBaseDataVersions(GUIPerson guiPerson) {
        ObservableList<GUIPersonBaseData> versionsBuffer = FXCollections.observableArrayList();

        for (PersonBaseData person : persistanceFascade.getAllPersonBaseDataVersions(guiPerson.getData().getPersonBaseData())) {
            versionsBuffer.add(new GUIPersonBaseData(person));
        }

        return versionsBuffer;
    }

    public GUIPerson createPerson() {
        Person pers = persistanceFascade.createPerson();
        return new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData()));
    }

    public GUIPerson editPerson(GUIPerson guiPers) {
        Person pers = persistanceFascade.editPerson(guiPers.getData());
        return new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData()));
    }

    public void storePerson(GUIPerson pers) throws PersistanceException {
        persistanceFascade.storePerson(pers.getData());
        reloadPersonsBuffer();
    }

    public void storePersonBaseDataAndPerson(GUIPerson pers) throws PersistanceException {
        persistanceFascade.storePersonBaseDataAndPerson(pers.getData());
        reloadPersonsBuffer();
    }

    public ObservableList<GUIPersonBaseData> getPersonVersions(GUIPerson guiPerson) {
        ObservableList<GUIPersonBaseData> versionsBuffer = FXCollections.observableArrayList();

        for (PersonBaseData data : persistanceFascade.getAllVersions(guiPerson.getBaseData().getData())) {
            versionsBuffer.add(new GUIPersonBaseData(data));
        }

        return versionsBuffer;
    }

    public ObservableList<GUIRole> getPersonRoles(GUIPerson guiPerson) {
        ObservableList<GUIRole> roles = FXCollections.observableArrayList();

        for (Role role : guiPerson.getData().getRoles()) {
            roles.add(new GUIRole(role));
        }

        return roles;
    }

    public void addRoleToPerson(GUIPerson guiPerson, GUIRole guiRole) throws PersistanceException {
        guiPerson.getData().getRoles().add(guiRole.getData());
        storePerson(guiPerson);
    }

    public ObservableList<GUIPosition> getCopyOfPersonBusket(GUIPerson guiPerson) {
        ObservableList<GUIPosition> busket = FXCollections.observableArrayList();

        for (Position pos : guiPerson.getData().getBusket()) {
            busket.add(new GUIPosition(new Position(pos), new GUIArticle(pos.getArticle())));
        }

        return busket;
    }


}
