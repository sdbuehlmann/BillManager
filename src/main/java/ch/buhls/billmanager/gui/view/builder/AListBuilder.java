
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.view.container.table.ATableContainer;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public abstract class AListBuilder<T>
{
    protected final ATableContainer<T> tableContainer;
    
    
    public AListBuilder(ATableContainer<T> tableContainer) {
        this.tableContainer = tableContainer;
        VBox.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
    }
    
    public final void enableDBInfos(boolean enable) {
        for (TableColumn<T, ?> techCol : tableContainer.getTechnicalColumns()) {
            if (enable) {
                tableContainer.addColumn(techCol);
            }
            else {
                tableContainer.removeColumn(techCol);
            }
        }
    }
    
    public abstract Node getView();
}
