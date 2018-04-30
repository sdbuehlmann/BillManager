
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.Role;

/**
 *
 * @author simon
 */
public class RoleService extends AService<Role>
{

    public RoleService(ContainerFactory factory) {
        super(factory, factory.getRoleContainer());
    }

    @Override
    protected void updateManagedEntity(Role managedEntity, Role entity) throws ServiceException {
        managedEntity.setName(entity.getName());
        managedEntity.setActive(entity.isActive());
    }
    
}
