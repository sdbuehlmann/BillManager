
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.view.builder.listener.IListFinancialYearsBuilderListener;
import ch.buhls.billmanager.gui.view.container.form.ListFinancialYearsFormContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 *
 * @author simon
 */
public class ListFinancialYearsBuilder
{
    private final IListFinancialYearsBuilderListener listener;
    
    // containers
    private final ListFinancialYearsFormContainer formContainer;
    
    // data
    private final ObservableList<GUIFinancialYear> entites;


    public ListFinancialYearsBuilder(IListFinancialYearsBuilderListener listener, ObservableList<GUIFinancialYear> entites) {
        this.listener = listener;
        this.entites = entites;
        
        formContainer = new ListFinancialYearsFormContainer();
        
        bindData();
        bindListener();
    }
    
    private void bindData(){
        formContainer.getTableContainer().getTable().setItems(entites);
    }
    
    private void bindListener(){
        formContainer.getItemNew().setOnAction((ActionEvent event) -> {
            listener.create();
        });
        
        formContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(formContainer.getTableContainer().getTable().getSelectionModel().getSelectedItem());
        });
    }
    
    public Node getView() {
        return formContainer.getView();
    }
}
