package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.container.AContainer;
import ch.buhls.billmanager.persistance.database.container.EntityNotFoundException;
import ch.buhls.billmanager.persistance.database.container.IContainer;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import ch.buhls.billmanager.persistance.database.entities.ATrackedEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;

/**
 * Service für die Manipulation (hinzufügen, ändern, entfernen) von Entities in
 * der Datenbank. Alle Verbundenen Entities (@OneToMany) müssen im Vorfeld vom
 * entsprechenden Service bearbeitet werden!
 *
 * @author buhls7
 * @param <T> Datenbank Entity
 */
public abstract class AService<T extends AEntity> implements IService<T>
{

    protected ContainerFactory factory;
    protected IContainer<T> container;

    public AService(ContainerFactory factory, IContainer<T> container)
    {
        this.factory = factory;
        this.container = container;
    }

    /**
     * Adjusting the managed list to the srcList.
     *
     * @param <X>
     * @param srcList
     * @param managedList
     * @param container
     * @return returns the number of detected and handled deltas (add & remove =
     * delta)
     * @throws ServiceException
     */
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    protected <X extends AEntity> int handleDeltasInList(List<X> srcList, List<X> managedList, AContainer<X> container) throws ServiceException
    {
        List<X> tempRemoveBuffer = new ArrayList<>();
        int countDeltas = 0;
        // searching deltas
        for (X entry : srcList)
        {
            if (!managedList.contains(entry))
            {
                // New entry detected --> Add it
                X newManagedEntity;
                try
                {
                    newManagedEntity = container.findByID(entry.getId());
                    managedList.add(newManagedEntity);
                    countDeltas++;
                }
                catch (EntityNotFoundException ex)
                {
                    throw new ServiceException(ServiceException.Type.ENTITY_NOT_FOUND);
                }
            }
        }

        for (X entry : managedList)
        {
            if (!srcList.contains(entry))
            {
                // Deleted entry detected --> Delete it
                tempRemoveBuffer.add(entry);
                countDeltas++;
            }
        }

        for (X entry : tempRemoveBuffer)
        {
            managedList.remove(entry);
        }

        return countDeltas;
    }

    public <X extends AEntity> ListDeltasTO<X> getDeltasInList(List<X> srcList, List<X> managedList)
    {
        ListDeltasTO<X> deltas = new ListDeltasTO<>();

        // searching deltas
        for (X entry : srcList)
        {
            if (!managedList.contains(entry))
            {
                // New entry detected --> Add it
                deltas.getNewElements().add(entry);
            }
            else if (srcList.contains(entry))
            {
                for (X srcEntry : srcList)
                {
                    if (srcEntry.equals(entry))
                    {
                        deltas.getExistingElements().add(new UpdatableEntityPaire<>(srcEntry, entry));
                    }
                }
            }
        }

        for (X entry : managedList)
        {
            if (!srcList.contains(entry))
            {
                // Deleted entry detected --> Delete it
                deltas.getRemovedElements().add(entry);
            }
        }

        return deltas;
    }

    /**
     * Detect Deltas in the two handed lists
     *
     * @param listA
     * @param listB
     * @return
     */
    protected boolean listContainsDeltas(List listA, List listB)
    {
        for (Object o : listA)
        {
            if (!listB.contains(o))
            {
                return true;
            }
        }

        for (Object o : listB)
        {
            if (!listA.contains(o))
            {
                return true;
            }
        }

        return false;
    }

    // Abstract Methods
    /**
     * Template: 1. Check Parameters 2. Save entity 3. Check 4. Update Relations
     *
     * @param entity
     * @throws ServiceException
     */
    @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(T entity) throws ServiceException
    {
        // start transaction
        EntityTransaction transaction = factory.getEntityManager().getTransaction();
        transaction.begin();

        // add entity to container
        T managedEntity = this.container.add(entity);

        transaction.commit();
    }

    /**
     * Template: 1. Check ID 2. treat entries
     *
     * @param entity
     * @throws ServiceException
     */
    @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(T entity) throws ServiceException
    {
        // start transaction
        EntityTransaction transaction = factory.getEntityManager().getTransaction();
        transaction.begin();

        // add entity to container
        T managedEntity;

        try
        {
            managedEntity = this.container.findByID(entity.getId());
        }
        catch (EntityNotFoundException ex)
        {
            transaction.rollback();
            throw new ServiceException(ServiceException.Type.ENTITY_NOT_FOUND);
        }

        // update attributes
        this.updateManagedEntity(managedEntity, entity);

        transaction.commit();
    }

    /**
     * Template: 1. Check ID 2. Remove
     *
     * @param entity
     * @throws ServiceException
     */
    @Override
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(T entity) throws ServiceException
    {
        // start transaction
        EntityTransaction transaction = factory.getEntityManager().getTransaction();
        transaction.begin();

        // find entity to remove
        T managedEntity;
        try
        {
            managedEntity = this.container.findByID(entity.getId());
        }
        catch (EntityNotFoundException ex)
        {
            transaction.rollback();
            throw new ServiceException(ServiceException.Type.ENTITY_NOT_FOUND);
        }

        container.delete(managedEntity);
        transaction.commit();
    }

    protected abstract void updateManagedEntity(T managedEntity, T entity) throws ServiceException;
    
    // getter
    public IContainer<T> getContainer() {
        return container;
    }

    public ContainerFactory getFactory() {
        return factory;
    }
}
