
package ch.buhls.billmanager.gui.view.listener;

import ch.buhls.billmanager.gui.data.AGUITrackedData;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IListVersionsListener<T extends AGUITrackedData>
{
    public void show(T data);
}
