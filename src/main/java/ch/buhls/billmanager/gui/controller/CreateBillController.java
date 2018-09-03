
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
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
public class CreateBillController extends AController implements ICreateBillMaskListener
{
    private final CreateBillMaskBuilder builder;
    
    public CreateBillController(IGUIFramework framework, DataHandler dataHandler, List<GUIPerson> persons) {
        super(framework, dataHandler, GUIStringCollection.getTitleForCreateBill());
        
        GUICreateBillsData data = dataHandler.createBills(persons);
        
        builder = new CreateBillMaskBuilder(data, this);
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void create(GUICreateBillsData billsData) {
        framework.confirmToAddArticle(); // TEMP! Not "add article"
        
        try {
            dataHandler.storeBills(billsData);
            tabHandle.close();
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
