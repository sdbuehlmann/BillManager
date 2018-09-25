
package ch.buhls.billmanager.gui;

import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IFilter<T>
{
    public void filterList(List<T> orig);
    public List<T> getElementsToRemoveSublist(List<T> orig);
}
