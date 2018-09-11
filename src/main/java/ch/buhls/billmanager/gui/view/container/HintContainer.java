
package ch.buhls.billmanager.gui.view.container;

import ch.buhls.billmanager.gui.framework.IHintListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author simon
 */
public class HintContainer
{
    private final GridPane view;
    private final Node content;
    private final Hyperlink hlClose;

    public HintContainer(Node content, IHintListener listener) {
        this.content = content;
        
        view = new GridPane();
        view.setPadding(new Insets(5));
        ColumnConstraints column1 = new ColumnConstraints(100,100,Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        ColumnConstraints column2 = new ColumnConstraints(100);
        view.getColumnConstraints().addAll(column1, column2); // first column gets any extra width
        
        view.add(content, 0, 0);
        hlClose = new Hyperlink("schliessen");
        hlClose.setOnAction((ActionEvent event) -> {
            listener.hintClosed();
        });
        
        view.add(hlClose, 1, 0);
    }

    public Node getContent() {
        return content;
    }

    public Hyperlink getHlClose() {
        return hlClose;
    }
    
    public GridPane getView() {
        return view;
    }
}
