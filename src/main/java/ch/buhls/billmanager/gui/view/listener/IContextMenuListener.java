
package ch.buhls.billmanager.gui.view.listener;

import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public interface IContextMenuListener<T>
{
    public void contextMenuOpened(ObservableList<T> selected);
}
