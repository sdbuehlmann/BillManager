package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIArticle;
import javafx.scene.control.TableColumn;

/**
 *
 * @author simon
 */
public class ArticleTableContainer extends ATableContainer<GUIArticle>
{
    private final TableColumn<GUIArticle, Number> dbIdColumn;
    private final TableColumn<GUIArticle, Number> dbVersionColumn;

    private final TableColumn<GUIArticle, Number> trackedEntityVersionColumn;
    private final TableColumn<GUIArticle, String> categorieColumn;
    private final TableColumn<GUIArticle, String> titleColumn;
    private final TableColumn<GUIArticle, String> descriptionColumn;
    private final TableColumn<GUIArticle, Number> prizeColumn;

    public ArticleTableContainer() {
        dbIdColumn = new TableColumn(GUIStringCollection.DB_ID);
        dbIdColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        this.addColumn(dbIdColumn);
        this.getTechnicalColumns().add(dbIdColumn);

        dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
        dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
        this.addColumn(dbVersionColumn);
        this.getTechnicalColumns().add(dbVersionColumn);

        trackedEntityVersionColumn = new TableColumn(GUIStringCollection.TRACKED_ENRITY_VERSION_NR);
        trackedEntityVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getVersionNr());
        this.addColumn(trackedEntityVersionColumn);

        categorieColumn = new TableColumn(GUIStringCollection.ART_CATEGORIE);
        categorieColumn.setCellValueFactory(cellData -> cellData.getValue().getInternalCategorie());
        this.addColumn(categorieColumn);

        titleColumn = new TableColumn(GUIStringCollection.ART_TITLE);
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        this.addColumn(titleColumn);

        descriptionColumn = new TableColumn(GUIStringCollection.ART_DESCRIPTION);
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        this.addColumn(descriptionColumn);

        prizeColumn = new TableColumn(GUIStringCollection.ART_PRICE);
        prizeColumn.setCellValueFactory(cellData -> cellData.getValue().getCosts());
        this.addColumn(prizeColumn);
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
}
