
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIRegisterBillData;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.RegisterBillMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IRegisterBillMaskListener;
import ch.buhls.billmanager.model.App;
import java.io.File;

/**
 *
 * @author simon
 */
public class RegisterBillController extends AFormController<GUIBill> implements IRegisterBillMaskListener
{
    private final RegisterBillMaskBuilder builder;
    
    private final GUIRegisterBillData data;
    
    private File selectedFile;
    
    public RegisterBillController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, framework.getStringCollections().getBillStringCollection());
        
        data = dataHandler.getBillViewModel().createRegisterBillData(person);
        
        builder = new RegisterBillMaskBuilder(data, this);
        displayMask(builder.getView(), framework.getStringCollections().getBillStringCollection().getTabTitle_Register());
    }

    @Override
    public void register(GUIRegisterBillData bill) {
        if(framework.showConfirmDialoque(framework.getStringCollections().getBillStringCollection().getConfirmTxt_ImportBill())){
            try {
                dataHandler.getBillViewModel().registerBill(data, selectedFile);
            }
            catch (Exception ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void selectBillFile() {
        selectedFile = framework.openFileChooser(GUIStringCollection.BILL_SELECT_BILL_TO_REGISTER, App.INSTANCE.getLastPath());
        if (selectedFile != null) {
            App.INSTANCE.setLastPath(selectedFile);
            
            data.getFile().set(selectedFile.getPath());
        }
    }
    
}
