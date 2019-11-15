package ch.buhls.billmanager.gui.viewModel;

import ch.buhls.billmanager.gui.data.CopiedDataObjectContainer;
import ch.buhls.billmanager.gui.viewModel.criteria.AgePersonCriteria.AgeFilterType;
import ch.buhls.billmanager.gui.viewModel.criteria.RolePersonCriteria.RoleFilterType;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIImportedPerson;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.viewModel.buffer.BufferDataService;
import ch.buhls.billmanager.gui.viewModel.dataLoader.PersonDataLoader;
import ch.buhls.billmanager.gui.viewModel.filter.PersonFilterService;
import ch.buhls.billmanager.gui.viewModel.wrappers.PersonWrapper;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.gui.viewModel.criteria.AgePersonCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.CriteriaHandle;
import ch.buhls.billmanager.gui.viewModel.criteria.RolePersonCriteria;
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
public class PersonViewModel
{
    private static final Logger LOG = Logger.getLogger(PersonViewModel.class.getName());

    private final PersistanceFascade persistanceFascade;
    
    private final BufferDataService<Person, GUIPerson> bufferService;

    public PersonViewModel(Project project) {
        persistanceFascade = project.getDb();

        this.bufferService = new BufferDataService<>(
                new PersonFilterService(), 
                new PersonDataLoader(persistanceFascade), 
                new PersonWrapper());
    }

    // entity management
    
    public GUIPerson createPerson() {
        Person pers = persistanceFascade.createPerson();
        return new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData()));
    }

    public CopiedDataObjectContainer<GUIPerson> copyPerson(GUIPerson guiPers) {
        Person pers = persistanceFascade.editPerson(guiPers.getData());
        GUIPerson copiedPers = new GUIPerson(pers, new GUIPersonBaseData(pers.getPersonBaseData()));
        
        return new CopiedDataObjectContainer<>(guiPers, copiedPers);
    }
    
    public void storePerson(GUIPerson pers) throws PersistanceException {
        persistanceFascade.storePerson(pers.getData());
        bufferService.add(pers);
    }
    
    public void updatePerson(CopiedDataObjectContainer<GUIPerson> copiedDataContainer) throws PersistanceException {
        persistanceFascade.updatePerson(copiedDataContainer.getCopiedDataObject().getData());
        bufferService.update(copiedDataContainer);
    }

    public void updatePersonBaseDataAndPerson(CopiedDataObjectContainer<GUIPerson> copiedDataContainer) throws PersistanceException {
        persistanceFascade.storePersonBaseData(copiedDataContainer.getCopiedDataObject().getData());
        bufferService.update(copiedDataContainer);
    }

    
    // criterias
    public CriteriaHandle addRoleFilter(RoleFilterType type, GUIRole role) {
        RolePersonCriteria filter = new RolePersonCriteria(type, role.getData());
        this.bufferService.addCriteria(filter);
        
        return new CriteriaHandle(filter, this.bufferService);
    }

    public CriteriaHandle addAgeFilter(AgeFilterType type, GUIFinancialYear year, int age) {
        AgePersonCriteria filter = new AgePersonCriteria(type, year.getData(), age);
        this.bufferService.addCriteria(filter);
        
        return new CriteriaHandle(filter, this.bufferService);
    }

    public ObservableList<GUIPerson> getPersonsBuffer() {
        return this.bufferService.getBuffer();
    }

    public ObservableList<Person> getPersonsBufferHack() { // ToDo: Remove this dirty hack
        ObservableList<Person> observableList = FXCollections.observableArrayList();
        observableList.addAll(new PersonDataLoader(persistanceFascade).loadData());
        return observableList;
    }

    public void reloadPersonsBuffer() {
        this.bufferService.reloadData();
    }

    // utils
    
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
        CopiedDataObjectContainer<GUIPerson> copiedDataContainer = this.copyPerson(guiPerson);
        copiedDataContainer.getCopiedDataObject().getData().getRoles().add(guiRole.getData());
        updatePerson(copiedDataContainer);
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

        storePerson(pers);

        return pers;
    }
}
