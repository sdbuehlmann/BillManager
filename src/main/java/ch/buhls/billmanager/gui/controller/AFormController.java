
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import javafx.scene.Node;
import ch.buhls.billmanager.gui.framework.IStringCollection;

/**
 *
 * @author simon
 * @param <T>
 */
public abstract class AFormController<T>
{
    protected final DataHandler dataHandler;
    protected final IGUIFramework framework;
    protected final IStringCollection<T> stringCollection;
    protected ITabHandle tabHandle;

    public AFormController(IGUIFramework framework, DataHandler dataHandler, IStringCollection<T> stringCollection) {
        this.dataHandler = dataHandler;
        this.framework = framework;
        this.stringCollection = stringCollection;
    }
    
    protected final void displayCreateMask(Node node){
        tabHandle = framework.displayMask(node, stringCollection.getTabTitle_Create(), IGUIFramework.Area.RIGHT);
        tabHandle.focus();
    }
    protected final void displayEditMask(Node node, T data){
        tabHandle = framework.displayMask(node, stringCollection.getTabTitle_Edit(data), IGUIFramework.Area.RIGHT);
        tabHandle.focus();
    }
    protected final void displayShowMask(Node node, T data){
        tabHandle = framework.displayMask(node, stringCollection.getTabTitle_Show(data), IGUIFramework.Area.RIGHT);
        tabHandle.focus();
    }
    
    protected final void closeMask(){
        tabHandle.close();
    }
    
    // dialoques
    protected final boolean displayConfirmToStoreDialoque(T data){
        return framework.showConfirmToStoreDialoque(stringCollection.getConfirmTxt_Save(data));
    }
    
    // hints
    protected final void displayCreatedInfoHint(T data){
        framework.displayInfoHint(stringCollection.getHintTxt_Created(data));
    }
    
    protected final void displayEditedInfoHint(T data){
        framework.displayInfoHint(stringCollection.getHintTxt_Edited(data));
    }
}
