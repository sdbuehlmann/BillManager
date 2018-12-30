
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.data.CopiedDataObjectContainer;
import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.EditBillBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class EditBillController extends AFormController<GUIBill> implements IDefaultMaskListener<GUIBill>
{
    private final EditBillBuilder builder;
    
    private final CopiedDataObjectContainer<GUIBill> copiedDataContainer;

    public EditBillController(IGUIFramework framework, DataHandler dataHandler, GUIBill bill) {
        super(framework, dataHandler, framework.getStringCollections().getBillStringCollection());
        
        copiedDataContainer = dataHandler.getBillViewModel().copyBill(bill);
        builder = new EditBillBuilder(copiedDataContainer.getCopiedDataObject(), this);
        
        displayEditMask(builder.getView(), copiedDataContainer.getCopiedDataObject());
    }

    @Override
    public void save(GUIBill entity) {
        try {
            dataHandler.getBillViewModel().updateBill(copiedDataContainer);
            
            tabHandle.close();
            displayEditedInfoHint(entity);
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
