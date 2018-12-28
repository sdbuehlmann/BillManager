
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.IGUIFramework.Area;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import ch.buhls.billmanager.gui.view.builder.AListBuilder;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import ch.buhls.billmanager.gui.framework.IStringCollection;

/**
 *
 * @author simon
 */
public abstract class AController
{

    protected final DataHandler dataHandler;
    protected final IGUIFramework framework;
    
    protected final String tabName;
    
    protected ITabHandle tabHandle;

    public AController(IGUIFramework framework, DataHandler dataHandler, String tabName) {
        this.dataHandler = dataHandler;
        this.framework = framework;
        this.tabName = tabName;
    }
    
    protected final void display(Node node, Area area){
        tabHandle = framework.displayMask(node, tabName, area);
        tabHandle.focus();
    }
    
    protected final void close(){
        tabHandle.close();
    }

    protected final void bindBuilder(AListBuilder builder){
        builder.enableDBInfos(dataHandler.getShowDBInfosProperty().get());
        dataHandler.getShowDBInfosProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            builder.enableDBInfos(newValue);
        });
    }
}
