package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import ch.buhls.billmanager.gui.view.container.menues.ListBillsMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.BillsTableContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author simon
 */
public class ListBillsBuilder extends AListBuilder<GUIBill>
{

    private final IListBillsBuilderListener listener;

    // containers
    private final ListBillsMenuContainer menuContainer;
    private final BillsTableContainer tableContainer;

    // data
    private final ObservableList<GUIBill> entites;

    public ListBillsBuilder(IListBillsBuilderListener listener, ObservableList<GUIBill> entites) {
        super(new BillsTableContainer());
        this.tableContainer = (BillsTableContainer) super.tableContainer;

        this.listener = listener;
        this.entites = entites;

        menuContainer = new ListBillsMenuContainer();
        tableContainer.getTable().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());

        bindData();
        bindListener();
    }

    private void bindData() {
        tableContainer.getTable().setItems(entites);
    }

    private void bindListener() {
        menuContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });

        menuContainer.getItemShowPDF().setOnAction((ActionEvent event) -> {
            listener.showPDF(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });

        menuContainer.getItemPrint().setOnAction((ActionEvent event) -> {
            listener.printPDFs(tableContainer.getTable().getSelectionModel().getSelectedItems());
        });

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
        return tableContainer.getTable();
    }

}
