
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;

/**
 *
 * @author simon
 */
public class FinancialYearService extends AService<FinancialYear>
{

    public FinancialYearService(ContainerFactory factory) {
        super(factory, factory.getFinancialYearContainer());
    }

    @Override
    protected void updateManagedEntity(FinancialYear managedEntity, FinancialYear entity) throws ServiceException {
        managedEntity.setName(entity.getName());
        managedEntity.setFirstDay(entity.getFirstDay());
        managedEntity.setLastDay(entity.getLastDay());
    }
    
}
