
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ListBillsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import java.util.List;

/**
 *
 * @author simon
 */
public class ListBillsController extends AController implements IListBillsBuilderListener
{
    private final ListBillsBuilder builder;
    
    public ListBillsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.BILLS);
        
        builder = new ListBillsBuilder(this, dataHandler.getBillsBuffer());
        
        display(builder.getView(), IGUIFramework.Area.LEFT);
    }

    
    @Override
    public void edit(GUIBill selected) {
        new EditBillController(framework, dataHandler, selected);
    }

    @Override
    public void showPDF(GUIBill selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPDFs(List<GUIBill> bills) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}