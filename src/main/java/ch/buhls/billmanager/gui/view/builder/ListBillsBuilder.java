package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.menues.ListBillsMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.BillsTableContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import ch.buhls.billmanager.gui.view.container.IHintBarContainer;
import java.time.LocalDate;

/**
 *
 * @author simon
 */
public class ListBillsBuilder extends AListBuilder<GUIBill> implements IHintBarContainer
{
    private final VBox view;

    private final IListBillsBuilderListener listener;

    // containers
    private final HintBarContainer hintBarContainer;
    private final ListBillsMenuContainer menuContainer;
    private final BillsTableContainer tableContainer;

    // data
    private final ObservableList<GUIBill> entites;
    private LocalDate paidAtDate;

    public ListBillsBuilder(IListBillsBuilderListener listener, ObservableList<GUIBill> entites) {
        super(new BillsTableContainer());
        this.tableContainer = (BillsTableContainer) super.tableContainer;

        this.listener = listener;
        this.entites = entites;

        view = new VBox();
        hintBarContainer = new HintBarContainer();
        menuContainer = new ListBillsMenuContainer();
        
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());
        
        view.getChildren().add(hintBarContainer.getView());
        view.getChildren().add(tableContainer.getTable());

        bindData();
        bindListener();
        
        this.setPaidAtDate(LocalDate.now());
    }

    public final void setPaidAtDate(LocalDate date){
        this.paidAtDate = date;
        
        // update menu item
        menuContainer.getItemStateToPaid().setText(GUIStringCollection.getItemText_setStateToPaid(this.paidAtDate));
    }
    
    private void bindData() {
        tableContainer.getTable().setItems(entites);
    }

    private void bindListener() {
        // edit
        menuContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        menuContainer.getItemStateToPaid().setOnAction((ActionEvent event) -> {
            listener.changeStateToPaid(tableContainer.getTable().getSelectionModel().getSelectedItem(), paidAtDate);
        });

        // pdf handling
        menuContainer.getItemShowPDF().setOnAction((ActionEvent event) -> {
            listener.showPDF(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        menuContainer.getItemPrint().setOnAction((ActionEvent event) -> {
            listener.printPDFs(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

        // filter
        menuContainer.getItemStatusCanceled().setOnAction((ActionEvent event) -> {
            listener.filterByStatus(GUIBill.GUIBillStatus.STORNO);
        });
        menuContainer.getItemStatusPaid().setOnAction((ActionEvent event) -> {
            listener.filterByStatus(GUIBill.GUIBillStatus.PAID);
        });
        menuContainer.getItemStatusSendet().setOnAction((ActionEvent event) -> {
            listener.filterByStatus(GUIBill.GUIBillStatus.SENDET);
        });
        menuContainer.getItemShowBillsOfRoleMembers().setOnAction((ActionEvent event) -> {
            listener.showOnlyBillsFromRoleMembers();
        });
        
        // other
        menuContainer.getContextMenu().setOnShowing((event) -> {
            handleMenuOpened(tableContainer.getTable().getSelectionModel().getSelectedItems().size());
        });
    }

    private void handleMenuOpened(int nrSelectedBills) {
        switch (nrSelectedBills) {
            case 0:
                menuContainer.getItemEdit().setDisable(true);
                menuContainer.getItemPrint().setDisable(true);
                menuContainer.getItemShowPDF().setDisable(true);
                break;
            case 1:
                menuContainer.getItemEdit().setDisable(false);
                menuContainer.getItemPrint().setDisable(false);
                menuContainer.getItemShowPDF().setDisable(false);
                break;
            default:
                menuContainer.getItemEdit().setDisable(true);
                menuContainer.getItemPrint().setDisable(false);
                menuContainer.getItemShowPDF().setDisable(true);
                break;
        }
    }
    
    @Override
    public Node getView() {
        return view;
    }

    @Override
    public IHintHandle addHint(String text, IHintListener listener) {
        return hintBarContainer.addFilterHint(text, listener);
    }

}
