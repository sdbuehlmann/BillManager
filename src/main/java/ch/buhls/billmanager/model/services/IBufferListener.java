
package ch.buhls.billmanager.model.services;

import java.util.List;

/**
 *
 * @author simon
 */
public interface IBufferListener<E>
{
    public void added(E entity);
    public void removed(E entity);
    public void bufferCleared();
}
