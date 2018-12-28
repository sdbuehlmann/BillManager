
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.List;

/**
 *
 * @author Simon Bühlmann
 * @param <T>
 */
public interface IContainer<T extends AEntity>
{

    /**
     * Adds an entity to the data tier
     *
     * @param entity entity, which should be added
     * @param transaction
     * @return entity which was added or null
     */
    public T add(T entity);

    /**
     * Deletes an entity in the data tier
     *
     * @param entity entity, which should be deleted
     * @param transaction
     * @return deleted entity
     */
    public T delete(T entity);

    /**
     * Returns all entities in the data tier
     *
     * @return List of all entities or empty List
     */
    public List<T> findAll();

    /**
     * Find entities in the data tier
     *
     * @param query The named Query to find the entities
     * @return List of entities, defined by the selected Query or an empty list.
     */
    public List<T> findByQuery(String query);

    /**
     * Search an entity by his data tier id
     *
     * @param id The entities ID
     * @return the desired entity or null if the entity was not found
     * @throws persistance.database.container.EntityNotFoundException
     */
    public T findByID(int id) throws EntityNotFoundException;

    /**
     * Counts all entities
     *
     * @return count The maximal Number of entities from this type
     */
    public int getCount();
}
