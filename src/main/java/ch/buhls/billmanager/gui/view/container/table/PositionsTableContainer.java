
package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIPosition;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class PositionsTableContainer
{
    private final TableView<GUIPosition> table;

    public PositionsTableContainer() {
        table = new TableView<>();

        {
            TableColumn<GUIPosition, Number> column = new TableColumn(GUIStringCollection.POSITION_POS);
            column.setCellValueFactory(cellData -> cellData.getValue().getPosition());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPosition, Number> column = new TableColumn(GUIStringCollection.POSITION_AMOUNT);
            column.setCellValueFactory(cellData -> cellData.getValue().getAmount());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPosition, String> column = new TableColumn(GUIStringCollection.ART_TITLE);
            column.setCellValueFactory(cellData -> cellData.getValue().getGuiArticle().getTitle());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPosition, String> column = new TableColumn(GUIStringCollection.ART_DESCRIPTION);
            column.setCellValueFactory(cellData -> cellData.getValue().getGuiArticle().getDescription());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIPosition, Number> column = new TableColumn(GUIStringCollection.ART_PRICE);
            column.setCellValueFactory(cellData -> cellData.getValue().getGuiArticle().getCosts());
            table.getColumns().add(column);
        }
    }

    public TableView<GUIPosition> getTable() {
        return table;
    }
}
