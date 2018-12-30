package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ListBillsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.data.filter.IFilterHandle;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ListBillsController extends AController implements IListBillsBuilderListener
{

    private final ListBillsBuilder builder;

    private IFilterHandle statusFilterHandle;
    private FilterHintController statusHintController;

    private IFilterHandle roleFilterHandle;
    private FilterHintController roleFilterHintController;

    public ListBillsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.BILLS);

        builder = new ListBillsBuilder(this, dataHandler.getBillsBuffer());
        this.bindBuilder(builder);

        this.filterByStatus(GUIBill.GUIBillStatus.SENDET);

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
    public final void filterByStatus(GUIBill.GUIBillStatus status) {
        if (statusFilterHandle != null) {
            statusFilterHandle.delete();
        }
        if (statusHintController != null) {
            statusHintController.hintClosed();
        }

        statusFilterHandle = dataHandler.setBillStatusFilter(status);
        statusHintController = new FilterHintController(builder, dataHandler, statusFilterHandle, GUIStringCollection.getHintTxt_showBillStatus(status));
    }

    @Override
    public void showOnlyBillsFromRoleMembers() {
        GUIRole markedRole = dataHandler.getMarkedRole().get();

        if (this.roleFilterHandle != null) {
            this.roleFilterHandle.delete();
        }
        if (this.roleFilterHintController != null) {
            this.roleFilterHintController.hintClosed();
        }

        this.roleFilterHandle = dataHandler.setBillRoleFilter(markedRole);
        this.roleFilterHintController = new FilterHintController(
                builder,
                dataHandler,
                roleFilterHandle,
                GUIStringCollection.getHintTxt_showBillsByRoleFilter(markedRole));
    }

    @Override
    public void changeStateToPaid(GUIBill selected, LocalDate date) {
        try {
            selected.getState().set(GUIBill.GUIBillStatus.PAID);
            selected.getClosedDate().set(date);

            this.dataHandler.updateBill(selected);
            
            framework.displayInfoHint(GUIStringCollection.getHintTxt_changedStateOfBillToPaid(selected, date));
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIBill> selected) {
        this.builder.handleMenuOpened(selected.size());
        this.builder.setPaidAtDate(this.dataHandler.getLastUsedDate());
    }

}
