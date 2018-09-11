
package ch.buhls.billmanager.gui.view.container;

import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;
import javafx.scene.control.Label;
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
    
    public void addHintOnTop(HintContainer hintContainer){
        view.getChildren().add(0,hintContainer.getView());
    }
    
    public IHintHandle addHint(String text, IHintListener listener) {
        HintContainer hc = new HintContainer(new Label(text), listener);
        addHint(hc);
        
        return () -> {
            removeHint(hc);
        };
    }
    
    public void removeHint(HintContainer hintContainer){
        view.getChildren().remove(hintContainer.getView());
    }

    public VBox getView() {
        return view;
    }
    
}
