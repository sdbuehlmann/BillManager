package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.AgePersonFilter.AgeFilterType;
import ch.buhls.billmanager.gui.RolePersonFilter.RoleFilterType;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIImportedPerson;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.csvHandling.CSVPerson;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class PersonsDataHandler implements IDataBufferContainer
{

    private static final Logger LOG = Logger.getLogger(PersonsDataHandler.class.getName());
    

    private final Project project;
    private final PersistanceFascade persistanceFascade;
    private final ModelFascade modelFascade;

    private final ListFiltersContainer<Person> filteredListContainer;
    private final ObservableList<GUIPerson> buffer;

    public PersonsDataHandler(Project project) {
        this.project = project;
        persistanceFascade = project.getDb();
        modelFascade = new ModelFascade();

        filteredListContainer = new ListFiltersContainer<>();
        buffer = FXCollections.observableArrayList();
    }

    public FilterHandle addRoleFilter(RoleFilterType type, GUIRole role) {
        RolePersonFilter filter = new RolePersonFilter(type, role.getData());
        filteredListContainer.getFilters().add(filter);

        return new FilterHandle(filter, filteredListContainer.getFilters());
    }

    public FilterHandle addAgeFilter(AgeFilterType type, GUIFinancialYear year, int age) {
        AgePersonFilter filter = new AgePersonFilter(type, year.getData(), age);
        filteredListContainer.getFilters().add(filter);

        return new FilterHandle(filter, filteredListContainer.getFilters());
    }

    public ObservableList<GUIPerson> getPersonsBuffer() {
        return buffer;
    }

    @Deprecated
    public void reloadPersonsBuffer() {
        // get new data and filter
        List<Person> persons = persistanceFascade.getAllPersons();
        filteredListContainer.filterList(persons);

        // update buffer
        buffer.clear();
        for (Person pers : persons) {
            buffer.add(wrapPersonToGUIData(pers));
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

    public GUIPerson wrapPersonToGUIData(Person pers) {
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
        
        guiPerson.getNrOfRoles().set(0);
    }

    public ObservableList<GUIPosition> getCopyOfPersonBusket(GUIPerson guiPerson) {
        ObservableList<GUIPosition> busket = FXCollections.observableArrayList();

        for (Position pos : guiPerson.getData().getBusket()) {
            busket.add(new GUIPosition(new Position(pos), new GUIArticle(pos.getArticle())));
        }

        return busket;
    }

    // import
    public List<GUIImportedPerson> importPersons(File file) throws PersistanceException {
        List<CSVPerson> persons = persistanceFascade.importPersons(file);
        List<GUIImportedPerson> buffer = new ArrayList<>();

        for (CSVPerson pers : persons) {
            if (pers.getBirthday_year() < 100) {
                // format error, only last two digits of year (maybe)
                if (pers.getBirthday_year() < LocalDate.now().getYear() - 2000) {
                    // 2000er
                    pers.setBirthday_year(2000 + pers.getBirthday_year());
                }
                else {
                    // 1900er
                    pers.setBirthday_year(1900 + pers.getBirthday_year());
                }
            }

            buffer.add(new GUIImportedPerson(pers));
        }

        return buffer;
    }

    public void checkForDuplicates(GUIImportedPerson importedPers) {
        List<Person> existingPersons = persistanceFascade.getAllPersons();

        for (Person person : existingPersons) {
            // check: the same?
            float index = comparePersons(importedPers.getData(), person.getPersonBaseData());
            if (index == 1) {
                importedPers.getSameEntities().add(new GUIPerson(person, new GUIPersonBaseData(person.getPersonBaseData())));
            }
            else if (index > 0.6) {
                importedPers.getUpdatedEntities().add(new GUIPerson(person, new GUIPersonBaseData(person.getPersonBaseData())));
            }
        }
    }

    private float comparePersons(CSVPerson csvPers, PersonBaseData dbPers) {
        int testCnt = 0;
        int testResult = 0;

        testCnt++;
        if (csvPers.getName().equals(dbPers.getName())) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getPrename().equals(dbPers.getPrename())) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getStreet().equals(dbPers.getStreet())) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getPlz() == dbPers.getPostalcode()) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getCity().equals(dbPers.getCity())) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getPhone_mobile().equals(dbPers.getPhoneM())) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getPhone_landline().equals(dbPers.getPhoneP())) {
            testResult++;
        }

        testCnt++;
        if (csvPers.getMail().equals(dbPers.getEmail())) {
            testResult++;
        }

        return (float) testResult / (float) testCnt;
    }

    public GUIPerson createPerson(GUIImportedPerson importedPers) throws PersistanceException {
        GUIPerson pers = createPerson();

        pers.getBaseData().getName().set(importedPers.getName().get());
        pers.getBaseData().getPrename().set(importedPers.getPrename().get());
        pers.getBaseData().getStreet().set(importedPers.getStreet().get());
        pers.getBaseData().getPostalcode().set(importedPers.getPostalcode().get());
        pers.getBaseData().getCity().set(importedPers.getCity().get());
        pers.getBaseData().getPhoneM().set(importedPers.getPhoneM().get());
        pers.getBaseData().getPhoneP().set(importedPers.getPhoneP().get());
        pers.getBaseData().getMail().set(importedPers.getMail().get());

        pers.getBaseData().getSalutation().set(importedPers.getSalutation().get());
        pers.getBaseData().getTitle().set(importedPers.getTitle().get());

        try {
            LocalDate birthday = LocalDate.of(importedPers.getBirthyear().get(), importedPers.getBirthmonth().get(), importedPers.getBirthday().get());
            pers.getBaseData().getBirthday().set(birthday);
        }
        catch (Exception ex) {
            // store no birthday
            LOG.log(Level.INFO, ex.getMessage());
        }

        pers.getBaseData().getChangeTxt().set("imported");

        storePersonBaseDataAndPerson(pers);

        return pers;
    }

    @Override
    public void reloadBuffer() {
        this.reloadPersonsBuffer();
    }

}
