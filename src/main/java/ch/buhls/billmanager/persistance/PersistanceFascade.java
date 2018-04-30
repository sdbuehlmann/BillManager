package ch.buhls.billmanager.persistance;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.container.EntityNotFoundException;
import ch.buhls.billmanager.persistance.database.entities.ATrackedEntity;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.BillBaseData;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import ch.buhls.billmanager.persistance.database.services.ArticleService;
import ch.buhls.billmanager.persistance.database.services.BillBaseDataService;
import ch.buhls.billmanager.persistance.database.services.BillService;
import ch.buhls.billmanager.persistance.database.services.BillTemplateService;
import ch.buhls.billmanager.persistance.database.services.FinancialYearService;
import ch.buhls.billmanager.persistance.database.services.PersonBaseDataService;
import ch.buhls.billmanager.persistance.database.services.PersonService;
import ch.buhls.billmanager.persistance.database.services.RoleService;
import ch.buhls.billmanager.persistance.database.services.ServiceException;
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

    private ArticleService articleService;
    private PersonService personService;
    private PersonBaseDataService personBaseDataService;
    private RoleService roleService;
    private BillTemplateService billTemplateService;
    private FinancialYearService financialYearService;
    private BillService billService;
    private BillBaseDataService billBaseDataService;

    public PersistanceFascade(File project) {
        ContainerFactory factory = new ContainerFactory(project);

        articleService = new ArticleService(factory);
        personService = new PersonService(factory);
        personBaseDataService = new PersonBaseDataService(factory);
        roleService = new RoleService(factory);
        billTemplateService = new BillTemplateService(factory);
        financialYearService = new FinancialYearService(factory);
        billService = new BillService(factory);
        billBaseDataService = new BillBaseDataService(factory);
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
        // trackable entity
        BillTemplate temp = new BillTemplate(template);

        temp.setId(0);
        temp.setVersion(0);

        temp.setChangeTxt(null);
        temp.setFollowingVersion(null);
        temp.setPreviousVersion(template);

        return temp;
    }

    public void storeBillTemplate(BillTemplate template) throws PersistanceException {
        try {
            billTemplateService.add(template);

            if (template.getPreviousVersion() != null) {
                // change the entry for the following version
                template.getPreviousVersion().setFollowingVersion(template);
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
    
    // financial year
    public FinancialYear createFinancialYear() {
        return new FinancialYear();
    }

    public FinancialYear editFinancialYear(FinancialYear year) {
        // trackable entity
        FinancialYear temp = new FinancialYear(year);

        temp.setId(0);
        temp.setVersion(0);

        temp.setChangeTxt(null);
        temp.setFollowingVersion(null);
        temp.setPreviousVersion(year);

        return temp;
    }

    public void storeFinancialYear(FinancialYear year) throws PersistanceException {
        try {
            financialYearService.add(year);

            if (year.getPreviousVersion() != null) {
                // change the entry for the following version
                year.getPreviousVersion().setFollowingVersion(year);
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
        
        persBaseData.setBirthday(new Date());
        
        Person pers = new Person();
        pers.setPersonBaseData(persBaseData);

        return pers;
    }

    /**
     * Create a copy of the handed person-object (including a copy of the base
     * data object)
     *
     * @param person
     * @return
     */
    public Person editPerson(Person person) {
        // trackable entity
        PersonBaseData tempBaseData = new PersonBaseData(person.getPersonBaseData());

        tempBaseData.setId(0);
        tempBaseData.setVersion(0);

        tempBaseData.setChangeTxt("");
        tempBaseData.setFollowingVersion(null);
        tempBaseData.setPreviousVersion(person.getPersonBaseData());
        
        tempBaseData.setDateAdded(new Date());
        tempBaseData.setVersionNr(person.getPersonBaseData().getVersionNr()+1);
        
        Person temp = new Person(person);
        temp.setPersonBaseData(tempBaseData);

        return temp;
    }

    /**
     * Stores the base data and the person itself
     * @param person
     * @throws PersistanceException 
     */
    public void storePersonBaseDataAndPerson(Person person) throws PersistanceException {
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

    public void storePerson(Person person) throws PersistanceException {
        try {
            if (person.getId() == 0) {
                // new person
                personService.add(person);
            }
            else {
                // existing person
                personService.update(person);
                
                
                // handle positions in basket
            }
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }

    public List<Person> getAllPersons() {
        return personService.getContainer().findAll();
    }
    
    // bill
    public Bill createBill() {
        Bill temp = new Bill();
        temp.setBillBaseData(new BillBaseData());
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
        // trackable entity
        BillBaseData tempBaseData = new BillBaseData(origBill.getBillBaseData());
        tempBaseData.setId(0);
        tempBaseData.setVersion(0);
        tempBaseData.setFollowingVersion(null);
        tempBaseData.setPreviousVersion(origBill.getBillBaseData());
        
        // duplicate all positions
        for(Position pos : origBill.getBillBaseData().getPositions()){
            tempBaseData.getPositions().add(new Position(pos));
        }
        
        Bill temp = new Bill(origBill);
        temp.setBillBaseData(tempBaseData);

        return temp;
    }

    /**
     * Stores only the base data (without the bill) into the db.
     * @param bill
     * @throws PersistanceException 
     */
    public void storeBillBaseData(Bill bill) throws PersistanceException {
        try {
            BillBaseData temp = bill.getBillBaseData();

            billBaseDataService.add(temp);

            // update previous version
            if (temp.getPreviousVersion() != null) {
                temp.getPreviousVersion().setFollowingVersion(temp);
                billBaseDataService.update(temp.getPreviousVersion());
            }

            // update with new base data
            billService.update(bill);
        }
        catch (ServiceException ex) {
            throw new PersistanceException(ex);
        }
    }

    /**
     * Stores only the bill (without the base data) into the db.
     * @param bill
     * @throws PersistanceException 
     */
    public void storeBill(Bill bill) throws PersistanceException {
        try {
            if (bill.getId() == 0) {
                // new bill
                try {
                    // check: base data allready stored?
                    personBaseDataService.getContainer().findByID(bill.getBillBaseData().getId());
                    billService.add(bill);
                }
                catch (EntityNotFoundException ex) {
                    throw new PersistanceException(ex);
                } 
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

    public List<Bill> getAllBills() {
        return billService.getContainer().findAll();
    }
    
    
}
