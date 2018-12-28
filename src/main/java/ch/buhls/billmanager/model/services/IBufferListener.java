
package ch.buhls.billmanager.model.services;

/**
 *
 * @author simon
 * @param <E>
 */
public interface IBufferListener<E>
{
    public void added(E entity);
    public void removed(E entity);
    public void bufferCleared();
}
