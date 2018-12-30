
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.data.GUICreateBillsData;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.CreateBillMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.ICreateBillMaskListener;
import java.util.List;

/**
 *
 * @author simon
 */
public class CreateBillController extends AFormController implements ICreateBillMaskListener
{
    private final CreateBillMaskBuilder builder;
    
    public CreateBillController(IGUIFramework framework, DataHandler dataHandler, List<GUIPerson> persons) {
        super(framework, dataHandler, framework.getStringCollections().getBillStringCollection());
        
        GUICreateBillsData data = dataHandler.getBillViewModel().createBills(persons);
        
        builder = new CreateBillMaskBuilder(data, this);
        displayCreateMask(builder.getView());
    }

    @Override
    public void create(GUICreateBillsData billsData) {
        try {
            if (displayConfirmToStoreDialoque(billsData)) {
                dataHandler.getBillViewModel().storeBills(billsData);
                tabHandle.close();
                displayCreatedInfoHint(billsData);
            }
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void removePerson(GUIPerson person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
