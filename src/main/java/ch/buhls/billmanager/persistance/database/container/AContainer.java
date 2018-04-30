package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author sdb
 * @param <T>
 */
public abstract class AContainer<T extends AEntity> implements IContainer<T>
{
    protected EntityManager em;

    private Class<T> typeParameterClass;
    protected String tableName;

    public AContainer(Class<T> typeParameterClass, String tableName, EntityManager em)
    {
        this.em = em;
        this.typeParameterClass = typeParameterClass;
        this.tableName = tableName;
    }

    @Override
    public T add(T entity)
    {
        em.persist(entity);
        return entity;
    }

    @Override
    public T delete(T entity)
    {
        if (entity != null)
        {
            em.remove(entity);
        }
        return null;
    }

    @Override
    public List<T> findAll()
    {
        TypedQuery<T> query = this.em.createQuery("select x from " + this.tableName + " x", this.typeParameterClass);
        List<T> temp = query.getResultList();
        return temp;
    }

    @Override
    public List<T> findByQuery(String queryName)
    {
        Query query = this.em.createQuery(queryName, this.typeParameterClass);
        return query.getResultList();
    }

    @Override
    public T findByID(Integer id) throws EntityNotFoundException
    {
        T temp = this.em.find(typeParameterClass, id);

        if (temp == null)
        {
            throw new EntityNotFoundException("Entity ID " + id + " not found in Table " + this.tableName);
        }

        return temp;
    }

    @Override
    public int getCount()
    {
        return this.findAll().size();
    }
}
