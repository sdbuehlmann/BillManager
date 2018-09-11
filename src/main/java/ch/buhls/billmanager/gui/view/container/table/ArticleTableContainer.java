package ch.buhls.billmanager.gui.view.container.table;


import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.framework.GUIStyle;
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
    
    private final TableColumn<GUIArticle, Number> dbIdColumn;
    private final TableColumn<GUIArticle, Number> dbVersionColumn;
    
    private final TableColumn<GUIArticle, Number> trackedEntityVersionColumn;
    private final TableColumn<GUIArticle, String> categorieColumn;
    private final TableColumn<GUIArticle, String> titleColumn;
    private final TableColumn<GUIArticle, String> descriptionColumn;
    private final TableColumn<GUIArticle, Number> prizeColumn;
    
    private final TableColumn<GUIArticle, Boolean> markedColumn;

    public ArticleTableContainer() {
        table = new TableView<>();

            dbIdColumn = new TableColumn(GUIStringCollection.DB_ID);
            dbIdColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
            table.getColumns().add(dbIdColumn);

            dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
            dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
            table.getColumns().add(dbVersionColumn);

            trackedEntityVersionColumn = new TableColumn(GUIStringCollection.TRACKED_ENRITY_VERSION_NR);
            trackedEntityVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getVersionNr());
            table.getColumns().add(trackedEntityVersionColumn);

            categorieColumn = new TableColumn(GUIStringCollection.ART_CATEGORIE);
            categorieColumn.setCellValueFactory(cellData -> cellData.getValue().getInternalCategorie());
            table.getColumns().add(categorieColumn);
            
            titleColumn = new TableColumn(GUIStringCollection.ART_TITLE);
            titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
            table.getColumns().add(titleColumn);
            
            descriptionColumn = new TableColumn(GUIStringCollection.ART_DESCRIPTION);
            descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            table.getColumns().add(descriptionColumn);
            
            prizeColumn = new TableColumn(GUIStringCollection.ART_PRICE);
            prizeColumn.setCellValueFactory(cellData -> cellData.getValue().getCosts());
            table.getColumns().add(prizeColumn);
            
            markedColumn = TablesUtils.createMarkedColumn((TableColumn.CellDataFeatures<GUIArticle, Boolean> cellData) -> {
                return cellData.getValue().getMarked();
            });
            table.getColumns().add(markedColumn);
    }

    public TableView<GUIArticle> getTable() {
        return table;
    }

    public TableColumn<GUIArticle, Number> getDbIdColumn() {
        return dbIdColumn;
    }

    public TableColumn<GUIArticle, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<GUIArticle, Number> getTrackedEntityVersionColumn() {
        return trackedEntityVersionColumn;
    }

    public TableColumn<GUIArticle, String> getCategorieColumn() {
        return categorieColumn;
    }

    public TableColumn<GUIArticle, String> getTitleColumn() {
        return titleColumn;
    }

    public TableColumn<GUIArticle, String> getDescriptionColumn() {
        return descriptionColumn;
    }

    public TableColumn<GUIArticle, Number> getPrizeColumn() {
        return prizeColumn;
    }

    public TableColumn<GUIArticle, Boolean> getMarkedColumn() {
        return markedColumn;
    }
    
    
}
