
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.view.builder.PersonMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class ShowPersonController extends AFormController<GUIPersonBaseData> implements IDefaultMaskListener<GUIPersonBaseData>
{
    
    public ShowPersonController(GUIPersonBaseData person, IGUIFramework framework, DataHandler dataHandler){
        super(framework, dataHandler, framework.getStringCollections().getPersonBaseDataStringCollection());
        
        PersonMaskBuilder builder = new PersonMaskBuilder(person, this);
        builder.changeToReadOnlyMode();
        
        displayShowMask(builder.getView(), person);
    }
    
    // private methods
    @Override
    public void save(GUIPersonBaseData data)
    {
        throw new UnsupportedOperationException();
    }
    
}
