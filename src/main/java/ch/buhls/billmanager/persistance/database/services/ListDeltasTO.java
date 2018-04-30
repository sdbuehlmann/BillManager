
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buhls7
 */
public class ListDeltasTO<T extends AEntity>
{
    private List<T> newElements;
    private List<T> removedElements;
    private List<UpdatableEntityPaire<T>> existingElements;
    
    // Getter & Setter
    public List<T> getNewElements()
    {
        if(this.newElements == null)
        {
            this.newElements = new ArrayList<>();
        }
        return newElements;
    }

    public ListDeltasTO<T> setNewElements(List<T> newElements)
    {
        this.newElements = newElements;
        return this;
    }

    public List<T> getRemovedElements()
    {
        if(this.removedElements == null)
        {
            this.removedElements = new ArrayList<>();
        }
        return removedElements;
    }

    public ListDeltasTO setRemovedElements(List<T> removedElements)
    {
        this.removedElements = removedElements;
        return this;
    }

    public List<UpdatableEntityPaire<T>> getExistingElements()
    {
        if(this.existingElements == null)
        {
            this.existingElements = new ArrayList<>();
        }
        return existingElements;
    }

    public ListDeltasTO setExistingElements(List<UpdatableEntityPaire<T>> existingElements)
    {
        this.existingElements = existingElements;
        return this;
    }
    
    
}
