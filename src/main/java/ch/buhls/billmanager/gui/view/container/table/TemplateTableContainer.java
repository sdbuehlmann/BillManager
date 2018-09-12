package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUITemplate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 */
public class TemplateTableContainer extends ATableContainer<GUITemplate>
{

    private final TableColumn<GUITemplate, Number> dbIDColumn;
    private final TableColumn<GUITemplate, Number> dbVersionColumn;

    private final TableColumn<GUITemplate, String> nameColumn;
    private final TableColumn<GUITemplate, Number> maxNrPositionsColumn;

    public TemplateTableContainer() {
        dbIDColumn = new TableColumn(GUIStringCollection.DB_ID);
        dbIDColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_id());
        this.addColumn(dbIDColumn);
        this.getTechnicalColumns().add(dbIDColumn);

        dbVersionColumn = new TableColumn(GUIStringCollection.DB_VERSION);
        dbVersionColumn.setCellValueFactory(cellData -> cellData.getValue().getDb_version());
        this.addColumn(dbVersionColumn);
        this.getTechnicalColumns().add(dbVersionColumn);

        nameColumn = new TableColumn(GUIStringCollection.TEMPLATE_NAME);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        this.addColumn(nameColumn);

        maxNrPositionsColumn = new TableColumn(GUIStringCollection.TEMPLATE_MAX_POS);
        maxNrPositionsColumn.setCellValueFactory(cellData -> cellData.getValue().getMaxNrPositions());
        this.addColumn(maxNrPositionsColumn);
    }

    public TableColumn<GUITemplate, Number> getDbIDColumn() {
        return dbIDColumn;
    }

    public TableColumn<GUITemplate, Number> getDbVersionColumn() {
        return dbVersionColumn;
    }

    public TableColumn<GUITemplate, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<GUITemplate, Number> getMaxNrPositionsColumn() {
        return maxNrPositionsColumn;
    }

}
