package ch.buhls.billmanager.framework.jfxRenderer;

import ch.buhls.billmanager.framework.jfxRenderer.containers.TableContainer;
import ch.buhls.billmanager.framework.propertyDescription.CategoryDescriptor;
import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import ch.buhls.billmanager.framework.propertyDescription.PropertyDescriptor;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Date;

public class TableRenderer {
    public static <TEntity> TableView<TEntity> render(PropertiesView<TEntity> properties, ObservableList<TEntity> entities)
    {
        TableContainer<TEntity> tableContainer = new TableContainer<>();

        for (CategoryDescriptor category : properties.getCategories()){
            for (PropertyDescriptor property : category.getProperties()) {
                if (property.getTypeProperty() == String.class) {
                    // string table column
                    TableColumn<TEntity, String> column = new TableColumn(property.getKey());
                    column.setCellValueFactory(cellData -> ObservablePropertyAdapterFactory.createStringAdapter(cellData.getValue(), property));
                    // ToDo: Do Buffer objects

                    tableContainer.addColumn(column);
                }
                else if(property.getTypeProperty() == Integer.class){
                    // number table column
                    TableColumn<TEntity, Number> column = new TableColumn(property.getKey());
                    column.setCellValueFactory(cellData -> ObservablePropertyAdapterFactory.createIntegerAdapter(cellData.getValue(), property));

                    tableContainer.addColumn(column);
                }
                else if(property.getTypeProperty() == Date.class){
                    // date table column
                    TableColumn<TEntity, LocalDate> column = new TableColumn(property.getKey());
                    column.setCellValueFactory(cellData -> ObservablePropertyAdapterFactory.createLocalDateAdapter(cellData.getValue(), property));

                    tableContainer.addColumn(column);
                }
            }
        }

        tableContainer.getTable().setItems(entities);
        VBox.setVgrow(tableContainer.getTable(), Priority.ALWAYS);

        return tableContainer.getTable();
    }
}
