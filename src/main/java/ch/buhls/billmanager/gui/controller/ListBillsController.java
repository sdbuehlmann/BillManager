package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ListBillsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.gui.viewModel.criteria.IFilterHandle;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import ch.buhls.billmanager.persistance.PersistanceException;
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

        builder = new ListBillsBuilder(this, dataHandler.getBillViewModel().getBuffer());
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
            dataHandler.getBillViewModel().showPDF(selected);
        }
        catch (ModelException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void printPDFs(List<GUIBill> bills) {
        try {
            dataHandler.getBillViewModel().printPDFs(bills);
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

        statusFilterHandle = dataHandler.getBillViewModel().setBillStatusFilter(status);
        statusHintController = new FilterHintController(builder, statusFilterHandle, GUIStringCollection.getHintTxt_showBillStatus(status));
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

        this.roleFilterHandle = dataHandler.getBillViewModel().setBillRoleFilter(markedRole);
        this.roleFilterHintController = new FilterHintController(
                builder,
                roleFilterHandle,
                GUIStringCollection.getHintTxt_showBillsByRoleFilter(markedRole));
    }

    @Override
    public void exportSelected(List<GUIBill> bills) {
        File file = framework.openPathChooser("Pfad für den Rechnungs-Export selektieren", null);
        if(file != null){
            try {
                dataHandler.getBillViewModel().exportBills(bills, file);
                framework.displayInfoHint(String.format("%d Rechnungen erfolgreich exportiert nach %s", bills.size(), file.getPath()));
            }
            catch (Exception ex) {
                framework.showExceptionDialoque(ex);
            }
        }
    }

    @Override
    public void changeStateToPaid(GUIBill selected, LocalDate date) {
        try {
            this.dataHandler.getBillViewModel().setStateToPaid(selected, date);
            framework.displayInfoHint(GUIStringCollection.getHintTxt_changedStateOfBillToPaid(selected, date));
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIBill> selected) {
        this.builder.handleMenuOpened(selected.size());
        this.builder.setPaidAtDate(this.dataHandler.getBillViewModel().getLastPaidDate());
    }

}
