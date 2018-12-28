
package ch.buhls.billmanager.model.services;

import java.util.Iterator;

/**
 *
 * @author simon
 * @param <E>
 */
public interface IDataBuffer<E> extends Iterator<E>
{
    public void add(E entity);
    public void remove(E entity);
    public void clear();
}
