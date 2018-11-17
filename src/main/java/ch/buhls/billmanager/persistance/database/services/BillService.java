package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import java.util.List;
import javax.persistence.Query;



/**
 *
 * @author simon
 */
public class BillService extends AService<Bill>
{
    private final PersonService personService;
    
    
    
    public BillService(ContainerFactory factory)
    {
        super(factory, factory.getBillContainer());
        
        this.personService = new PersonService(factory);
    }
    
    public List<Bill> getBillsByState(Bill.BillState state){
        Query query = this.factory.getEntityManager().createQuery(
                "SELECT b FROM Bill b WHERE b.billState = :state", 
                Bill.class);
        query.setParameter("state", state);
        List<Bill> list = query.getResultList();

        return list;
    }

    @Override
    protected void updateManagedEntity(Bill managedEntity, Bill entity) throws ServiceException{
        managedEntity.setComment(entity.getComment());
        managedEntity.setDateClosed(entity.getDateClosed());
        managedEntity.setBillState(entity.getBillState());
    }
}
