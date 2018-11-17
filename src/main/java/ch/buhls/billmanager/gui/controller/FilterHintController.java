
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.model.data.filter.FilterHandle;
import ch.buhls.billmanager.gui.IDataBufferContainer;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;
import ch.buhls.billmanager.gui.view.container.IHintBarContainer;
import ch.buhls.billmanager.model.data.filter.IFilterHandle;

/**
 *
 * @author simon
 */
public class FilterHintController implements IHintListener
{
    private final IHintBarContainer hintBar;
    private final IHintHandle hintHandle;
    
    private final IDataBufferContainer bufferContainer;
    
    private final IFilterHandle filterHandle;

    public FilterHintController(IHintBarContainer hintBar, IDataBufferContainer bufferContainer, IFilterHandle filterHandle, String hintTxt) {
        this.hintBar = hintBar;
        this.bufferContainer = bufferContainer;
        this.filterHandle = filterHandle;
        
        hintHandle = hintBar.addHint(hintTxt, this);
    }

    @Override
    public void hintClosed() {
        filterHandle.delete();
        hintHandle.close();
        bufferContainer.reloadBuffer();
    }
}
