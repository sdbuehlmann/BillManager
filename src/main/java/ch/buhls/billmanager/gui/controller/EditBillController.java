
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.EditBillBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class EditBillController extends AFormController<GUIBill> implements IDefaultMaskListener<GUIBill>
{
    private final EditBillBuilder builder;

    public EditBillController(IGUIFramework framework, DataHandler dataHandler, GUIBill bill) {
        super(framework, dataHandler, framework.getStringCollections().getBillStringCollection());
        
        GUIBill copyBill = dataHandler.copyBill(bill);
        builder = new EditBillBuilder(dataHandler.copyBill(bill), this);
        
        displayEditMask(builder.getView(), copyBill);
    }

    @Override
    public void save(GUIBill entity) {
        try {
            dataHandler.updateBill(entity);
            
            tabHandle.close();
            displayEditedInfoHint(entity);
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
