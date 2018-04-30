
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.entities.AEntity;


/**
 *
 * @author buhls7
 * @param <T>
 */
public interface IService<T extends AEntity>
{
    public void add(T entity) throws ServiceException;
    
    public void update(T entity) throws ServiceException;
    
    public void remove(T entity) throws ServiceException;
}
