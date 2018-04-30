package ch.buhls.billmanager.gui.view.container.table;


import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIArticle;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class ArticleTableContainer
{

    private final TableView<GUIArticle> table;

    public ArticleTableContainer() {
        table = new TableView<>();

        {
            TableColumn<GUIArticle, Number> column = new TableColumn(GUIStringCollection.DB_ID);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIArticle, Number> column = new TableColumn(GUIStringCollection.DB_VERSION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIArticle, Number> column = new TableColumn(GUIStringCollection.TRACKED_ENRITY_VERSION_NR);
            column.setCellValueFactory(cellData -> cellData.getValue().getVersionNr());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIArticle, String> column = new TableColumn(GUIStringCollection.ART_CATEGORIE);
            column.setCellValueFactory(cellData -> cellData.getValue().getInternalCategorie());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIArticle, String> column = new TableColumn(GUIStringCollection.ART_TITLE);
            column.setCellValueFactory(cellData -> cellData.getValue().getTitle());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIArticle, String> column = new TableColumn(GUIStringCollection.ART_DESCRIPTION);
            column.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            table.getColumns().add(column);
        }
        {
            TableColumn<GUIArticle, Number> column = new TableColumn(GUIStringCollection.ART_PRICE);
            column.setCellValueFactory(cellData -> cellData.getValue().getCosts());
            table.getColumns().add(column);
        }

        {
            TableColumn<GUIArticle, Boolean> column = new TableColumn("marked (intern)");
            column.setCellValueFactory(cellData -> cellData.getValue().getMarked());
            //table.getColumns().add(column);

            column.setCellFactory((TableColumn<GUIArticle, Boolean> param)
                    -> {
                final TableCell<GUIArticle, Boolean> cell = new TableCell<GUIArticle, Boolean>()
                {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);

                        TableRow currentRow = getTableRow();
                        if (currentRow != null && item != null) {
                            if (item.booleanValue()) {
                                currentRow.setStyle("-fx-background-color: yellow");
                            }
                            else {
                                currentRow.setStyle("");
                            }
                        }
                    }

                };
                return cell;
            });
        }
    }

    public TableView<GUIArticle> getTable() {
        return table;
    }
}
