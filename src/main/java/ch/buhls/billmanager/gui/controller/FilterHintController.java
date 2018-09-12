
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.FilterHandle;
import ch.buhls.billmanager.gui.PersonsDataHandler;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;
import ch.buhls.billmanager.gui.view.container.IHintBar;

/**
 *
 * @author simon
 */
public class FilterHintController implements IHintListener
{
    private final IHintBar hintBar;
    private final IHintHandle hintHandle;
    
    private final PersonsDataHandler dataHandler;
    
    private final FilterHandle filterHandle;

    public FilterHintController(IHintBar hintBar, PersonsDataHandler dataHandler, FilterHandle filterHandle, String hintTxt) {
        this.hintBar = hintBar;
        this.dataHandler = dataHandler;
        this.filterHandle = filterHandle;
        
        hintHandle = hintBar.addHint(hintTxt, this);
    }

    @Override
    public void hintClosed() {
        filterHandle.delete();
        hintHandle.close();
        dataHandler.reloadPersonsBuffer();
    }
}
