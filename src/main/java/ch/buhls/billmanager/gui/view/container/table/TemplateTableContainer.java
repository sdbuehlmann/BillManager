
package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUITemplate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class TemplateTableContainer
{
    private final TableView<GUITemplate> table;

    public TemplateTableContainer()
    {
        table = new TableView<>();

        {
            TableColumn<GUITemplate, Number> column = new TableColumn(GUIStringCollection.DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUITemplate, Number> column = new TableColumn(GUIStringCollection.DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUITemplate, String> column = new TableColumn(GUIStringCollection.TEMPLATE_NAME);
            column.setCellValueFactory(cellData -> cellData.getValue().getName());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUITemplate, Number> column = new TableColumn(GUIStringCollection.TEMPLATE_MAX_POS);
            column.setCellValueFactory(cellData -> cellData.getValue().getMaxNrPositions());
            table.getColumns().add(column);
        }
    }

    public TableView<GUITemplate> getTable()
    {
        return table;
    }
    
}
