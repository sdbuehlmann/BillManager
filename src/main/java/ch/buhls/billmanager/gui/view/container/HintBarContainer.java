
package ch.buhls.billmanager.gui.view.container;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class HintBarContainer
{
    private final VBox view;

    public HintBarContainer() {
        view = new VBox();
    }
    
    public void addHint(HintContainer hintContainer){
        view.getChildren().add(hintContainer.getView());
    }
    
    public void removeHint(HintContainer hintContainer){
        view.getChildren().remove(hintContainer.getView());
    }

    public VBox getView() {
        return view;
    }
    
}
