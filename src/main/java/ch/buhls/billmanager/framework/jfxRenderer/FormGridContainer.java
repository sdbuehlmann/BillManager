
package ch.buhls.billmanager.framework.jfxRenderer;
import ch.buhls.billmanager.gui.framework.GUIFramework;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author buhls
 */
public class FormGridContainer
{
    // style classes
    public static final String STYLECLASS_PROPERTY_ROW = "property-grid-property-row";
    public static final String STYLECLASS_TITLE_ROW = "property-grid-title-row";
    
    public static final String STYLECLASS_COLLAPSE_BUTTON = "property-grid-collapse-button";
    public static final String STYLECLASS_TITLE = "property-grid-title";
    public static final String STYLECLASS_KEY = "property-grid-key";
    
    private final GridPane grid;
    private int currentRow;
    
    private ColumnConstraints keyColumnConstraints;
    private ColumnConstraints childColumnConstraints;

    public FormGridContainer()
    {
        grid = new GridPane();
        
        // handle constraints
        ColumnConstraints insertionColumnConstraints = new ColumnConstraints(20);
        
        keyColumnConstraints = new ColumnConstraints();
        keyColumnConstraints.setMinWidth(200);
        keyColumnConstraints.setPrefWidth(200);
        keyColumnConstraints.setMaxWidth(200);
        
        ColumnConstraints resizingColumnConstraints = new ColumnConstraints(2);
        
        childColumnConstraints = new ColumnConstraints();
        childColumnConstraints.setPercentWidth(100);
        
        grid.getColumnConstraints().addAll(insertionColumnConstraints, keyColumnConstraints, resizingColumnConstraints, childColumnConstraints);
        
        grid.setStyle("-fx-background-color: -fx-background1-color;");
        currentRow = 0;
    }
    
    public void addCategoryRow(String text)
    {
        Button collapseButton = new Button();
        collapseButton.getStyleClass().add(STYLECLASS_COLLAPSE_BUTTON);
        
        grid.add(collapseButton, 0, currentRow);
        
        Label label = new Label(text);
        label.getStyleClass().add(STYLECLASS_TITLE);
        
        grid.add(label, 1, currentRow, 3, 1);
        currentRow++;
    }
    
    public void addPropertyRow(String text, Node child)
    {
        Label label = new Label(text);
        HBox labelBox = new HBox(label);
        labelBox.setAlignment(Pos.CENTER_LEFT);
        labelBox.setPadding(new Insets(0, 0, 0, 10));
        labelBox.getStyleClass().add(STYLECLASS_PROPERTY_ROW);
        labelBox.setStyle("-fx-border-width: 1px 0px 0px 1px; -fx-border-color: -fx-background1-color;");
        
        Pane resizingPane = new Pane();
        resizingPane.getStyleClass().add(STYLECLASS_PROPERTY_ROW);
        resizingPane.setStyle("-fx-border-width: 1px 1px 0px 0px; -fx-border-color: -fx-background1-color;");

        resizingPane.setOnMouseEntered((event) -> {
            GUIFramework.INSTANCE.setCursorType(IGUIFramework.CursorType.HORIZONTAL_SCALING);
        });
        resizingPane.setOnMouseExited((event) -> {
            GUIFramework.INSTANCE.setCursorType(IGUIFramework.CursorType.DEFAULT);
        });
        
        //HBox childBox = new HBox(child);
        child.getStyleClass().add(STYLECLASS_PROPERTY_ROW);
        child.setStyle("-fx-border-width: 1px 1px 0px 0px; -fx-border-color: -fx-background1-color;");
        
        grid.add(labelBox, 1, currentRow);
        grid.add(resizingPane, 2, currentRow);
        grid.add(child, 3, currentRow);
        
        //grid.getRowConstraints().add(new RowConstraints(20));
        
        currentRow++;
    }

    public GridPane getGrid()
    {
        return grid;
    }
    
    private HBox Wrapp(Node node){
        HBox box = new HBox(node);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(0, 0, 0, 10));
        
        return box;
    }
}
