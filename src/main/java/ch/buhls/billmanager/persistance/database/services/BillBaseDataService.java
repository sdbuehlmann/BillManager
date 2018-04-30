
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.BillBaseData;

/**
 *
 * @author simon
 */
public class BillBaseDataService extends AService<BillBaseData>
{

    public BillBaseDataService(ContainerFactory factory) {
        super(factory, factory.getBillBaseDataContainer());
    }

    @Override
    protected void updateManagedEntity(BillBaseData managedEntity, BillBaseData entity) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
