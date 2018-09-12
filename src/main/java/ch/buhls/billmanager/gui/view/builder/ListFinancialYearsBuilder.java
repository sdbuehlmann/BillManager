
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.view.builder.listener.IListFinancialYearsBuilderListener;
import ch.buhls.billmanager.gui.view.container.menues.ListFinancialYearsMenuContainer;
import ch.buhls.billmanager.gui.view.container.table.FinancialYearTableContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 *
 * @author simon
 */
public class ListFinancialYearsBuilder extends AListBuilder<GUIFinancialYear>
{
    private final IListFinancialYearsBuilderListener listener;
    
    // containers
    private final FinancialYearTableContainer tableContainer;
    private final ListFinancialYearsMenuContainer menuContainer;
    
    // data
    private final ObservableList<GUIFinancialYear> entites;


    public ListFinancialYearsBuilder(IListFinancialYearsBuilderListener listener, ObservableList<GUIFinancialYear> entites) {
        super(new FinancialYearTableContainer());
        tableContainer = (FinancialYearTableContainer)super.tableContainer;
        
        this.listener = listener;
        this.entites = entites;
        
        menuContainer = new ListFinancialYearsMenuContainer();
        tableContainer.getTable().setContextMenu(menuContainer.getContextMenu());
        
        bindData();
        bindListener();
    }
    
    private void bindData(){
        tableContainer.getTable().setItems(entites);
    }
    
    private void bindListener(){
        menuContainer.getItemNew().setOnAction((ActionEvent event) -> {
            listener.create();
        });
        
        menuContainer.getItemEdit().setOnAction((ActionEvent event) -> {
            listener.edit(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
    }

    @Override
    public Node getView() {
        return tableContainer.getTable();
    }
    
    
}
