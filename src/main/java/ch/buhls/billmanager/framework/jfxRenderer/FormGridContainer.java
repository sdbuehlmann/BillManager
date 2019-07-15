
package ch.buhls.billmanager.framework.jfxRenderer;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author buhls
 */
public class FormGridContainer
{
    private final GridPane grid;
    private int currentRow;

    public FormGridContainer()
    {
        grid = new GridPane();
        currentRow = 0;
    }
    
    public void addCategoryRow(String text)
    {
        Label label = new Label(text);
        grid.add(label, 0, currentRow, 2, 1);
        currentRow++;
    }
    
    public void addPropertyRow(String text, TextField child)
    {
        Label label = new Label(text);
        grid.add(label, 0, currentRow);
        grid.add(child, 1, currentRow);
        currentRow++;
    }

    public GridPane getGrid()
    {
        return grid;
    }
    
    
}
