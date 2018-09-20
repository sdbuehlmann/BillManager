
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIRegisterBillData;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.RegisterBillMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IRegisterBillMaskListener;

/**
 *
 * @author simon
 */
public class RegisterBillController extends AFormController<GUIBill> implements IRegisterBillMaskListener
{
    private final RegisterBillMaskBuilder builder;
    
    public RegisterBillController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, framework.getStringCollections().getBillStringCollection());
        
        GUIRegisterBillData data = dataHandler.createRegisterBillData(person);
        
        builder = new RegisterBillMaskBuilder(data, this);
        displayMask(builder.getView(), framework.getStringCollections().getBillStringCollection().getTabTitle_Register());
    }

    @Override
    public void register(GUIRegisterBillData bill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectBillFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
