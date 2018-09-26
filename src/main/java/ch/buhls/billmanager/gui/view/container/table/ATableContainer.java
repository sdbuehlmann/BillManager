package ch.buhls.billmanager.gui.view.container.table;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author simon
 * @param <T>
 */
public abstract class ATableContainer<T>
{

    private final TableView<T> table;

    private final List<TableColumn<T, ?>> technicalColumns;

    public ATableContainer() {
        this.table = new TableView<>();
        this.technicalColumns = new ArrayList<>();
    }

    public void addColumn(TableColumn<T, ?> column) {
        if (!table.getColumns().contains(column)) {
            table.getColumns().add(column);
        }
    }

    public void removeColumn(TableColumn<T, ?> column) {
        table.getColumns().remove(column);
    }

    public void removeColumns(List<TableColumn<T, ?>> columns) {
        for (TableColumn<T, ?> column : columns) {
            table.getColumns().remove(column);
        }
    }
    
    public void removeAllColumns() {
        table.getColumns().clear();
    }

    public TableView<T> getTable() {
        return table;
    }

    public List<TableColumn<T, ?>> getTechnicalColumns() {
        return technicalColumns;
    }
}
