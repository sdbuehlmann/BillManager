package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.Bill;


/**
 *
 * @author simon
 */
public class BillService extends AService<Bill>
{

    public BillService(ContainerFactory factory)
    {
        super(factory, factory.getBillContainer());
    }

    @Override
    protected void updateManagedEntity(Bill managedEntity, Bill entity) throws ServiceException{
        managedEntity.setComment(entity.getComment());
        managedEntity.setDateClosed(entity.getDateClosed());
        managedEntity.setBillState(entity.getBillState());
    }
}
