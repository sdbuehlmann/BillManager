
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.container.BillTemplateContainer;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;

/**
 *
 * @author simon
 */
public class BillTemplateService extends AService<BillTemplate>
{

    public BillTemplateService(ContainerFactory factory) {
        super(factory, new BillTemplateContainer(factory.getEntityManager()));
    }

    @Override
    protected void updateManagedEntity(BillTemplate managedEntity, BillTemplate entity) throws ServiceException {
        managedEntity.setName(entity.getName());
        managedEntity.setMaxPositions(entity.getMaxPositions());
        managedEntity.setTypePaimentSlip(entity.getTypePaimentSlip());
    }
    
}
