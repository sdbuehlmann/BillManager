
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
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
        super(framework, dataHandler, GUIStringCollection.getTitle_editBill(bill));
        
        builder = new EditBillBuilder(dataHandler.editBill(bill), this);
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIBill entity) {
        try {
            dataHandler.storeBill(entity);
            close();
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
