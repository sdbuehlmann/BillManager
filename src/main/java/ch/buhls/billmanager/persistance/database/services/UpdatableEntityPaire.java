
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.entities.AEntity;



/**
 *
 * @author sdb
 */
public class UpdatableEntityPaire<T extends AEntity>
{
    private T srcEntity;
    private T managedEntity;
    
    public UpdatableEntityPaire(T srcEntity, T managedEntity)
    {
        this.srcEntity = srcEntity;
        this.managedEntity = managedEntity;
    }
    
    // getter & setter

    public T getSrcEntity()
    {
        return srcEntity;
    }

    public void setSrcEntity(T srcEntity)
    {
        this.srcEntity = srcEntity;
    }

    public T getManagedEntity()
    {
        return managedEntity;
    }

    public void setManagedEntity(T managedEntity)
    {
        this.managedEntity = managedEntity;
    }
    
}
