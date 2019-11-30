package ch.buhls.billmanager.persistance;

import ch.buhls.billmanager.persistance.csvHandling.CsvManager;
import ch.buhls.billmanager.persistance.csvHandling.CSVPerson;
import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.container.BillContainer;
import ch.buhls.billmanager.persistance.database.container.EntityNotFoundException;
import ch.buhls.billmanager.persistance.database.container.PersonBaseDataContainer;
import ch.buhls.billmanager.persistance.database.container.PersonContainer;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import ch.buhls.billmanager.persistance.database.entities.ATrackedEntity;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import ch.buhls.billmanager.persistance.database.services.ArticleService;
import ch.buhls.billmanager.persistance.database.services.BillService;
import ch.buhls.billmanager.persistance.database.services.BillTemplateService;
import ch.buhls.billmanager.persistance.database.services.FinancialYearService;
import ch.buhls.billmanager.persistance.database.services.PersonBaseDataService;
import ch.buhls.billmanager.persistance.database.services.PersonService;
import ch.buhls.billmanager.persistance.database.services.PositionService;
import ch.buhls.billmanager.persistance.database.services.RoleService;
import ch.buhls.billmanager.persistance.database.services.ServiceException;
import ch.buhls.billmanager.utils.PropertiesSetBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author simon
 */
public class PersistanceFascade
{
    private ContainerFactory factory;

    private ArticleService articleService;
    private PositionService positionService;
    private PersonService personService;
    private PersonBaseDataService personBaseDataService;
    private RoleService roleService;
    private BillTemplateService billTemplateService;
    private FinancialYearService financialYearService;
    private BillService billService;

    public PersistanceFascade(File project) {
        factory = new ContainerFactory(project);

        articleService = new ArticleService(factory);
        positionService = new PositionService(factory);
        personService = new PersonService(factory);
        personBaseDataService = new PersonBaseDataService(factory);
        roleService = new RoleService(factory);
        billTemplateService = new BillTemplateService(factory);
        financialYearService = new FinancialYearService(factory);
        billService = new BillService(factory);
    }

    public <T extends ATrackedEntity<T>> List<T> getAllVersions(T art) {
        List<T> versions = new ArrayList<>();
        
        while(true){
            versions.add(art);
            
            if(art.getPreviousVersion() == null){
                break;
            }
            
            art = art.getPreviousVersion();
        }
        
        return versions;
    }
    
    // article
    public Article createArticle() {
        Article article = new Article();
        article.setDateAdded(new Date());
        article.setChangeTxt("neu erstellt");
        article.setVersionNr(0);
        
        return article;
    }

    public Article editArticle(Article article) {
        // trackable entity
        Article temp = article.duplicate();

        temp.setId(0);
        temp.setVersion(0);

        temp.setChangeTxt(null);
        temp.setFollowingVersion(null);
        temp.setPreviousVersion(article);
        temp.setDateAdded(new Date());
        temp.setVersionNr(article.getVersionNr()+1);

        return temp;
    }

    public void storeArticle(Article article) throws PersistanceException {
        try {
            articleService.add(article);

            if (article.getPreviousVersion() != null) {
                // change the entry for the following version
                article.getPreviousVersion().setFollowingVersion(article);
                articleService.update(article);
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }

    public List<Article> getAllArticles() {
        return articleService.getContainer().findAll();
    }
    
    public List<Article> getAllHighestVersionArticles() {
        List<Article> highestVersionArticles = new ArrayList<>();
        for(Article article : articleService.getContainer().findAll()){
            if(article.getFollowingVersion() == null){
                highestVersionArticles.add(article);
            }
        }
        
        return highestVersionArticles;
    }

    // role
    public Role createRole() {
        return new Role();
    }

    /**
     * returns a copy of the handed role
     *
     * @param role
     * @return
     */
    public Role editRole(Role role) {
        Role temp = new Role(role);
        return temp;
    }

    public void storeRole(Role role) throws PersistanceException {
        try {
            if (role.getId() == 0) {
                // new entity
                roleService.add(role);
            }
            else {
                // existing entity
                roleService.update(role);
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public List<Role> getAllRoles() {
        return roleService.getContainer().findAll();
    }
    
    public List<PersonBaseData> getAllPersonBaseDataVersions(PersonBaseData pers) {
        List<PersonBaseData> versions = new ArrayList<>();
        
        while(true){
            versions.add(pers);
            
            if(pers.getPreviousVersion() == null){
                break;
            }
            
            pers = pers.getPreviousVersion();
        }
        
        return versions;
    }
    
    // bill template
    public BillTemplate createBillTemplate() {
        return new BillTemplate();
    }

    public BillTemplate editBillTemplate(BillTemplate template) {
        BillTemplate temp = new BillTemplate(template);

        return temp;
    }

    public void storeBillTemplate(BillTemplate template) throws PersistanceException {
        try {
            if (template.getId() == 0) {
                // new entity
                billTemplateService.add(template);
            }
            else {
                // existing entity
                billTemplateService.update(template);
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }

    public List<BillTemplate> getAllBillTemplates() {
        return billTemplateService.getContainer().findAll();
    }
    
    public List<Bill> getBillsByRole(Role role){
        List<Person> roleMembers = this.factory.getPersonContainer().getPersonsByRole(role);
        
        List<Bill> bills = new ArrayList();
        
        for(Person roleMember : roleMembers){
            bills.addAll(roleMember.getBills());
        }
        
        return bills;
    }
    
    public List<Bill> getBillsByState(Bill.BillState state){
        return this.billService.getBillsByState(state);
    }
    
    public List<Bill> getBillsByRoleAndState(Role role, Bill.BillState state){
        List<Bill> bills = new ArrayList();
        
        for(Bill bill : this.getBillsByRole(role)){
            if(bill.getBillState() == state){
                bills.add(bill);
            }
        }
        
        return bills;
    }
    
    public BillContainer getBillContainer(){
        return this.factory.getBillContainer();
    }
    
    // financial year
    public FinancialYear createFinancialYear() {
        FinancialYear year = new FinancialYear();
        year.setFirstDay(new Date());
        year.setLastDay(new Date());
        
        return year;
    }

    public FinancialYear editFinancialYear(FinancialYear year) {
        FinancialYear temp = new FinancialYear(year);

        return temp;
    }

    public void storeFinancialYear(FinancialYear year) throws PersistanceException {
        try {
            if (year.getId() == 0) {
                // new entity
                financialYearService.add(year);
            }
            else {
                // existing entity
                financialYearService.update(year);
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public List<FinancialYear> getAllFinancialYears() {
        return financialYearService.getContainer().findAll();
    }
    
    // person
    public Person createPerson() {
        PersonBaseData persBaseData = new PersonBaseData();
        persBaseData.setDateAdded(new Date());
        persBaseData.setChangeTxt("neu erstellt");
        persBaseData.setVersionNr(0);
        
        Person pers = new Person();
        pers.setPersonBaseData(persBaseData);

        return pers;
    }

    public Person editPerson(Person person) {
        Person personCopy = new Person(person);
        
        PersonBaseData personDataCopy = personCopy.getPersonBaseData();
        personDataCopy.setId(0);
        personDataCopy.setVersion(0);

        personDataCopy.setChangeTxt("");
        personDataCopy.setFollowingVersion(null);
        personDataCopy.setPreviousVersion(person.getPersonBaseData());
        
        personDataCopy.setDateAdded(new Date());
        personDataCopy.setVersionNr(person.getPersonBaseData().getVersionNr()+1);

        return personCopy;
    }
    
    public void storePerson(Person person) throws PersistanceException {
        // new person
        if(person.getId() != 0){
            throw new PersistanceException("Can not store person cause the entity has allready an id.");
        }
        
        try {
            personBaseDataService.add(person.getPersonBaseData());
            personService.add(person);
            
            person.getPersonBaseData().setPersonId(person.getId());
            personService.update(person);
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public void updatePerson(Person person) throws PersistanceException {
        // existing person
        if(person.getId() == 0){
            throw new PersistanceException("Can not update person cause the entity has no id.");
        }
        
        try {
            Person origPerson = personService.getContainer().findByID(person.getId());
            person.setPersonBaseData(origPerson.getPersonBaseData());
            
            personService.update(person);
        }
        catch (EntityNotFoundException | ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public void storePersonBaseData(Person person) throws PersistanceException {
        if(person.getPersonBaseData().getId() != 0){
            throw new PersistanceException(String.format("Person base data has allready an id with value %d",person.getPersonBaseData().getId()));
        }
        
        try {
            PersonBaseData temp = person.getPersonBaseData();
            
            personBaseDataService.add(temp);

            // update previous version
            if (temp.getPreviousVersion() != null) {
                temp.getPreviousVersion().setFollowingVersion(temp);
                personBaseDataService.update(temp.getPreviousVersion());
            }

            // update person with new base data
            if (person.getId() == 0) {
                // new person
                personService.add(person);
                
                // update id in base data
                if(person.getId() == 0){
                    throw new PersistanceException("Can not update base data, cause wrong person id (is 0).");
                }
                
                person.getPersonBaseData().setPersonId(person.getId());
                personBaseDataService.update(person.getPersonBaseData());
            }
            else {
                // existing person
                personService.update(person);
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }

    public List<Person> getAllPersons() {
        return personService.getContainer().findAll();
    }
    
    public List<PersonBaseData> getPersonsByPrename(String prename) {
        PersonBaseDataContainer container = (PersonBaseDataContainer)personBaseDataService.getContainer(); // TEMP!!!!!!!!!!!!!!!!!!!!!!!!! Ugly cast
        return container.findByPrename(prename);
    }

    public PersonService getPersonService() {
        return personService;
    }
    public PersonContainer getPersonContainer(){
        return factory.getPersonContainer();
    }
    
    public void updatePersonIdInAllPersonBaseDatas() throws ServiceException
    {
        List<Person> allPersons = personService.getContainer().findAll();
        for(Person pers : allPersons){
            this.definePersonId(pers.getPersonBaseData(), pers.getId());
        }
    }
    
    private void definePersonId(PersonBaseData baseData, int personId) throws ServiceException{
        baseData.setPersonId(personId);
        personBaseDataService.update(baseData);
        
        if(baseData.getPreviousVersion() != null){
            this.definePersonId(baseData.getPreviousVersion(), personId);
        }
    }
    
    public List<CSVPerson> importPersons(File file) throws PersistanceException{
        CsvManager<CSVPerson> csvManager = new CsvManager();
        PropertiesSetBuilder<CSVPerson> propertiesSetBuilder = new PropertiesSetBuilder<>(CSVPerson.class);

        //Name;Vornamen;Firma;Geburtstag;Geburtsmonat;Geburtsjahr;Strasse;PLZ;Ort;TelP;TelM;E-Mail;Begrüssung;Titel
        propertiesSetBuilder
                .addProperty("name", String.class, CSVPerson::getName, CSVPerson::setName)
                .addProperty("prename", String.class, CSVPerson::getPrename, CSVPerson::setPrename)
                .addProperty("company", String.class, CSVPerson::getCompany, CSVPerson::setCompany)

                .addProperty("birthday_day", Integer.class, CSVPerson::getBirthday_day, CSVPerson::setBirthday_day)
                .addProperty("birthday_month", Integer.class, CSVPerson::getBirthday_month, CSVPerson::setBirthday_month)
                .addProperty("birthday_year", Integer.class, CSVPerson::getBirthday_year, CSVPerson::setBirthday_year)

                .addProperty("street", String.class, CSVPerson::getStreet, CSVPerson::setStreet)
                .addProperty("postalcode", Integer.class, CSVPerson::getPlz, CSVPerson::setPlz)
                .addProperty("city", String.class, CSVPerson::getCity, CSVPerson::setCity)

                .addProperty("phone_p", String.class, CSVPerson::getPhone_landline, CSVPerson::setPhone_landline)
                .addProperty("phone_m", String.class, CSVPerson::getPhone_mobile, CSVPerson::setPhone_mobile)
                .addProperty("e_mail", String.class, CSVPerson::getMail, CSVPerson::setMail)

                .addProperty("salutation", String.class, CSVPerson::getSalutation, CSVPerson::setSalutation)
                .addProperty("title", String.class, CSVPerson::getTitle, CSVPerson::setTitle);

        try {
            return csvManager.read(file, propertiesSetBuilder.getPropertiesSet());
        }
        catch (Exception ex) {
            throw new PersistanceException(ex);
        }
    }
    
    // bill
    public Bill createBill() {
        Bill temp = new Bill();
        temp.setBillState(Bill.BillState.SENDET);

        return temp;
    }

    /**
     * Create a copy of the handed bill-object (including a copy of the base
     * data object)
     *
     * @param origBill
     * @return
     */
    public Bill editBill(Bill origBill) {

        Bill tempBill = new Bill(origBill);

        
        // duplicate all positions
        for(Position pos : origBill.getPositions()){
            tempBill.getPositions().add(new Position(pos));
        }

        return tempBill;
    }
    
    public void storeBill(Bill bill) throws PersistanceException {
        try {
            if (bill.getId() == 0) {
                // new bill
                billService.add(bill);
            }
            else {
                // existing
                billService.update(bill);
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public void updateBill(Bill bill) throws PersistanceException {
        try {
            billService.update(bill);
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }

    public List<Bill> getAllBills() {
        return billService.getContainer().findAll();
    }
    
    public List<Bill> getBillsByFinancialYear(FinancialYear year) {
        BillContainer container = (BillContainer)billService.getContainer(); // TEMP! Dirty Cast
        return container.findByFinancialYearID(year.getId());
    }
    
    
    // position
    public Position createPosition(Article art) {
        Position pos = new Position();
        pos.setArticle(art);

        return pos;
    }
    
    public void storePosition(Position position) throws PersistanceException{
        try {
            positionService.add(position);
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public void updatePosition(Position position) throws PersistanceException{
        try {
            positionService.update(position);
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public void deletePosition(Position position) throws PersistanceException{
        try {
            positionService.remove(position);
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }
    
    public void mergeAndStoreBusket(Person person, List<Position> newBusket) throws PersistanceException{
        List<Position> oldPositions = findOldEntitiesComparedByID(person.getBusket(), newBusket);
        
        // store all used positions
        for(Position pos : newBusket){
            if(pos.getId() == 0){
                storePosition(pos);
            }else{
                updatePosition(pos);
            }
        }
        
        // update person entity
        person.getBusket().clear();
        person.getBusket().addAll(newBusket);
        updatePerson(person);
        
        // delete unused
        for(Position pos : oldPositions){
            deletePosition(pos);
        }
    }
    
    /**
     * Finds all old entities only compared by their id (without version). Each entry which is in the old but not in the new list is a old entry and will be returned in the return-list.
     * @param oldList
     * @param newList
     * @return a list with all old entries
     */
    private <X extends AEntity> List<X> findOldEntitiesComparedByID(List<X> oldList, List<X> newList){
        List<X> oldEntries = new ArrayList<>(oldList.size());
        oldEntries.addAll(oldList);

        for (X oldEntity : oldList)
        {
            for (X newEntity : newList)
            {
                if(oldEntity.getId() == newEntity.getId()){
                    oldEntries.remove(oldEntity);
                    break;
                }
            }
        }

        return oldEntries;
    }
}
