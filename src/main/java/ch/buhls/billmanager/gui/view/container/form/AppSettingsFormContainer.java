
package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabeledNodeContainer;
import ch.buhls.billmanager.gui.view.elements.SelectFileField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class AppSettingsFormContainer
{
    private final VBox view;
    
    private final ButtonBar bbContolButtonsBar;
    private final Button bSave;
    
    private final LabeledNodeContainer<SelectFileField> sffInkscapeExePath;
    private final CheckBox cbShowDBInfos;

    public AppSettingsFormContainer() {
        bbContolButtonsBar = new ButtonBar();
        bSave = new Button(GUIStringCollection.SAVE);
        bbContolButtonsBar.getButtons().add(bSave);
        
        sffInkscapeExePath = new LabeledNodeContainer<>(GUIStringCollection.APP_SETTINGS_INKSCAPE_PATH, new SelectFileField());
        cbShowDBInfos = new CheckBox(GUIStringCollection.APP_SETTINGS_SHOW_DB_INFOS);
        
        view = new VBox(
                bbContolButtonsBar, 
                sffInkscapeExePath.getView(), 
                cbShowDBInfos);
    }

    public VBox getView() {
        return view;
    }

    public ButtonBar getBbContolButtonsBar() {
        return bbContolButtonsBar;
    }

    public Button getBSave() {
        return bSave;
    }

    public Button getBSelectPathToInkscape(){
        return sffInkscapeExePath.getControl().getBtnSearch();
    }
    
    public LabeledNodeContainer<SelectFileField> getSffInkscapeExePath() {
        return sffInkscapeExePath;
    }
    
    public TextField getTfPathToInkscape(){
        return sffInkscapeExePath.getControl().getTfPath();
    }

    public CheckBox getCbShowDBInfos() {
        return cbShowDBInfos;
    }
    
    
}
