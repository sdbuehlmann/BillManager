
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.EditBillBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class EditBillController extends AController implements IDefaultMaskListener<GUIBill>
{
    private final EditBillBuilder builder;

    public EditBillController(IGUIFramework framework, DataHandler dataHandler, GUIBill bill) {
        super(framework, dataHandler, framework.getStringCollections().getBillStringCollection().getTabTitle_Edit(bill));
        
        builder = new EditBillBuilder(dataHandler.copyBill(bill), this);
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIBill entity) {
        try {
            dataHandler.updateBill(entity);
            close();
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
