package ch.buhls.billmanager.gui.view.container.table;

import ch.buhls.billmanager.gui.GUIStringCollection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.util.Callback;

/**
 *
 * @author simon
 */
public class TablesUtils
{

    static public <S> TableColumn<S, LocalDate> createDateColumn(Callback<TableColumn.CellDataFeatures<S, LocalDate>, ObservableValue<LocalDate>> value, String title) {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        TableColumn<S, LocalDate> dateColumn = new TableColumn(title);
        dateColumn.setCellValueFactory(value);

        dateColumn.setCellFactory((TableColumn<S, LocalDate> param) -> {
            return new TableCell<S, LocalDate>()
            {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    }
                    else {
                        // Format date.
                        setText(myDateFormatter.format(item));
                    }
                }
            };
        });

        return dateColumn;
    }

    static public <S> TableColumn<S, Boolean> createMarkedColumn(Callback<TableColumn.CellDataFeatures<S, Boolean>, ObservableValue<Boolean>> value) {
        TableColumn<S, Boolean> column = new TableColumn(GUIStringCollection.IS_MARKED);

        column.setCellValueFactory(value);
        column.setCellFactory((TableColumn<S, Boolean> param)
                -> {
            final TableCell<S, Boolean> cell = new TableCell<S, Boolean>()
            {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    TableRow currentRow = getTableRow();
                    if (currentRow != null && item != null) {
                        if (item) {
                            currentRow.setStyle("-fx-background-color: yellow");
                            setText("x");
                        }
                        else {
                            currentRow.setStyle("");
                            setText("");
                        }
                    }
                }

            };
            return cell;
        });
        return column;
    }
}
