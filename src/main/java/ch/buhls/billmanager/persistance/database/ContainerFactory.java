package ch.buhls.billmanager.persistance.database;

import ch.buhls.billmanager.persistance.database.container.ArticleContainer;
import ch.buhls.billmanager.persistance.database.container.BillBaseDataContainer;
import ch.buhls.billmanager.persistance.database.container.BillContainer;
import ch.buhls.billmanager.persistance.database.container.BillTemplateContainer;
import ch.buhls.billmanager.persistance.database.container.FinancialYearContainer;
import ch.buhls.billmanager.persistance.database.container.PersonBaseDataContainer;
import ch.buhls.billmanager.persistance.database.container.PersonContainer;
import ch.buhls.billmanager.persistance.database.container.PositionContainer;
import ch.buhls.billmanager.persistance.database.container.RoleContainer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author sdb
 */
public class ContainerFactory
{

    private static final String PERSISTENCE_UNIT_NAME = "riders";
    private EntityManager em;

    public ContainerFactory(File db) {
        Map addedOrOverridenProperties = new HashMap();

        addedOrOverridenProperties.put("javax.persistence.jdbc.url", "jdbc:sqlite:" + db.getAbsolutePath());
        addedOrOverridenProperties.put("eclipselink.ddl-generation", "create-tables");
        //addedOrOverridenProperties.put("eclipselink.ddl-generation", "drop-and-create-tables");

        this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, addedOrOverridenProperties).createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public PersonContainer getPersonContainer() {
        return new PersonContainer(em);
    }

    public PersonBaseDataContainer getPersonBaseDataContainer() {
        return new PersonBaseDataContainer(em);
    }

    public RoleContainer getRoleContainer() {
        return new RoleContainer(em);
    }

    public ArticleContainer getArticleContainer() {
        return new ArticleContainer(em);
    }

    public BillContainer getBillContainer() {
        return new BillContainer(em);
    }

    public BillBaseDataContainer getBillBaseDataContainer(){
        return new BillBaseDataContainer(em);
    }
    
    public PositionContainer getPositionContainer() {
        return new PositionContainer(em);
    }
    
    public BillTemplateContainer getBillTemplateContainer(){
        return new BillTemplateContainer(em);
    }
    
    public FinancialYearContainer getFinancialYearContainer(){
        return new FinancialYearContainer(em);
    }
}
