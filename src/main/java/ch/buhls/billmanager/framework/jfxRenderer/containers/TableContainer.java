package ch.buhls.billmanager.framework.jfxRenderer.containers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.List;

public class TableContainer<TEntity> {

    private final TableView<TEntity> table;

    public TableContainer() {
        this.table = new TableView<>();
    }

    public void addColumn(TableColumn<TEntity, ?> column) {
        if (!table.getColumns().contains(column)) {
            table.getColumns().add(column);
        }
    }

    public void removeColumn(TableColumn<TEntity, ?> column) {
        table.getColumns().remove(column);
    }

    public void removeColumns(List<TableColumn<TEntity, ?>> columns) {
        for (TableColumn<TEntity, ?> column : columns) {
            table.getColumns().remove(column);
        }
    }

    public void removeAllColumns() {
        table.getColumns().clear();
    }

    public TableView<TEntity> getTable() {
        return table;
    }
}
