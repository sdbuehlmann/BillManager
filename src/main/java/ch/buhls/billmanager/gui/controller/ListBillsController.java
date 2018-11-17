
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.model.data.filter.FilterHandle;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.model.data.filter.RolePersonFilter;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ListBillsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.data.filter.IFilterHandle;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author simon
 */
public class ListBillsController extends AController implements IListBillsBuilderListener
{
    private final ListBillsBuilder builder;
    
    private IFilterHandle statusFilterHandle;
    private FilterHintController statusHintController;
    
    public ListBillsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.BILLS);
        
        builder = new ListBillsBuilder(this, dataHandler.getBillsBuffer());
        this.bindBuilder(builder);
        
        this.display(builder.getView(), IGUIFramework.Area.LEFT);
    }

    
    @Override
    public void edit(GUIBill selected) {
        new EditBillController(framework, dataHandler, selected);
    }

    @Override
    public void showPDF(GUIBill selected) {
        try {
            dataHandler.showPDF(selected);
        }
        catch (ModelException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void printPDFs(List<GUIBill> bills) {
        try {
            dataHandler.printPDFs(bills);
        }
        catch (ModelException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void filterByStatus(GUIBill.GUIBillStatus status) {
        if(statusFilterHandle!=null){
            statusFilterHandle.delete();
        }
        if(statusHintController != null){
            statusHintController.hintClosed();
        }
        
        statusFilterHandle = dataHandler.setBillStatusFilter(status);
        statusHintController = new FilterHintController(builder, dataHandler, statusFilterHandle, GUIStringCollection.getHintTxt_showBillStatus(status));
    }

    @Override
    public void changeStateToPaid(GUIBill selected, LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showOnlyBillsFromRoleMembers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}