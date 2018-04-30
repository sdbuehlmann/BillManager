package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import ch.buhls.billmanager.gui.data.AGUITrackedData;
import ch.buhls.billmanager.gui.view.builder.ListVersionsBuilder;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import javafx.collections.ObservableList;


/**
 *
 * @author simon
 * @param <T>
 */
public class ListVersionsController<T extends AGUITrackedData>
{
    public ListVersionsController(IGUIFramework framework, ObservableList<T> list, String title, IListVersionsListener<T> listener) {
        ListVersionsBuilder builer = new ListVersionsBuilder(listener, list);

        ITabHandle tabHandle = framework.displayMask(builer.getView(), title, IGUIFramework.Area.RIGHT);
        tabHandle.focus();
    }
}
