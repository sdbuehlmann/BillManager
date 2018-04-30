
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.view.builder.PersonMaskBuilder;
import ch.buhls.billmanager.gui.view.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class ShowPersonController implements IDefaultMaskListener<GUIPersonBaseData>
{
    
    public ShowPersonController(GUIPersonBaseData person, IGUIFramework framework)
    {
        PersonMaskBuilder builder = new PersonMaskBuilder(person, this);
        builder.changeToReadOnlyMode();
        
        framework.displayMask(builder.getView(), GUIStringCollection.getTitleForShowPerson(person), IGUIFramework.Area.RIGHT);
    }
    
    // private methods
    @Override
    public void save(GUIPersonBaseData data)
    {
        throw new UnsupportedOperationException();
    }
    
}
