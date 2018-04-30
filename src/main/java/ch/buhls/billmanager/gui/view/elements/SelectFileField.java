
package ch.buhls.billmanager.gui.view.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author simon
 */
public class SelectFileField extends HBox
{

    private Button btnSearch;
    private TextField tfPath;

    public SelectFileField()
    {
        super(10);
        btnSearch = new Button("...");
        tfPath = new TextField();
        tfPath.setPrefWidth(800);
        
        this.getChildren().add(tfPath);
        this.getChildren().add(btnSearch);
    }

    public Button getBtnSearch()
    {
        return btnSearch;
    }

    public TextField getTfPath()
    {
        return tfPath;
    }
}
