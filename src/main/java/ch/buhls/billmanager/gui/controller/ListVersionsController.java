package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.AGUITrackedData;
import ch.buhls.billmanager.gui.view.builder.ListVersionsBuilder;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import javafx.collections.ObservableList;


/**
 *
 * @author simon
 * @param <T>
 */
public class ListVersionsController<T extends AGUITrackedData> extends AController
{
    public ListVersionsController(IGUIFramework framework, DataHandler dataHandler, String tabName, ObservableList<T> list, IListVersionsListener<T> listener) {
        super(framework, dataHandler, tabName);
        
        ListVersionsBuilder builder = new ListVersionsBuilder(listener, list);
        this.bindBuilder(builder);

        this.display(builder.getView(), IGUIFramework.Area.RIGHT);
    }
}
