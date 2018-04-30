
package ch.buhls.billmanager.gui.view.container;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author simon
 */
public class SplitScreen extends SplitPane
{
    private final BorderPane leftPane;
    private final BorderPane rightPane;
    
    private final TabPane mainTabPane;
    private final TabPane detailTabPane;
    
    public SplitScreen()
    {
        mainTabPane = new TabPane();
        detailTabPane = new TabPane();
        
        leftPane = new BorderPane(mainTabPane);
        rightPane = new BorderPane(detailTabPane);
        
        this.getItems().add(leftPane);
        this.getItems().add(rightPane);
        
        this.setDividerPositions(0.8);
    }

    public TabPane getMainTabPane()
    {
        return mainTabPane;
    }

    public TabPane getDetailTabPane()
    {
        return detailTabPane;
    }

    
}
