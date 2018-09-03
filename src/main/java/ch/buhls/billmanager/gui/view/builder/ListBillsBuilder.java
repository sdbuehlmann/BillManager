
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.view.builder.listener.IListBillsBuilderListener;
import ch.buhls.billmanager.gui.view.container.form.ListBillsFormContainer;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author simon
 */
public class ListBillsBuilder
{
    private final IListBillsBuilderListener listener;
    
    // containers
    private final ListBillsFormContainer formContainer;
    
    // data
    private final ObservableList<GUIBill> entites;


    public ListBillsBuilder(IListBillsBuilderListener listener, ObservableList<GUIBill> entites) {
        this.listener = listener;
        this.entites = entites;
        
        formContainer = new ListBillsFormContainer();
        formContainer.getTableContainer().getTable().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        bindData();
        bindListener();
    }
    
    private void bindData(){
        formContainer.getTableContainer().getTable().setItems(entites);
    }
    
    private void bindListener(){        
        formContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(formContainer.getTableContainer().getTable().getSelectionModel().getSelectedItem());
        });
        
        formContainer.getItemShowPDF().setOnAction((ActionEvent event) -> {
            listener.showPDF(formContainer.getTableContainer().getTable().getSelectionModel().getSelectedItem());
        });
        
        formContainer.getItemPrint().setOnAction((ActionEvent event) -> {
            listener.printPDFs(formContainer.getTableContainer().getTable().getSelectionModel().getSelectedItems());
        });
        
        formContainer.getContextMenu().setOnShowing((event) -> {
            handleMenuOpened(formContainer.getTableContainer().getTable().getSelectionModel().getSelectedItems());
        });
    }
    
    private void handleMenuOpened(List<GUIBill> selectedBills){
        if(selectedBills.isEmpty()){
            formContainer.getItemEdit().setDisable(true);
            formContainer.getItemPrint().setDisable(true);
            formContainer.getItemShowPDF().setDisable(true);
        }else if(selectedBills.size() == 1){
            formContainer.getItemEdit().setDisable(false);
            formContainer.getItemPrint().setDisable(false);
            formContainer.getItemShowPDF().setDisable(false);
        }else{
            formContainer.getItemEdit().setDisable(true);
            formContainer.getItemPrint().setDisable(false);
            formContainer.getItemShowPDF().setDisable(true);
        }
    }
    
    public Node getView() {
        return formContainer.getView();
    }
}
