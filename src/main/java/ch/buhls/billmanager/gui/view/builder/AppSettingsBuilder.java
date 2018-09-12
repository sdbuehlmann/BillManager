
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIAppSettings;
import ch.buhls.billmanager.gui.view.builder.listener.IAppSettingsMaskListener;
import ch.buhls.billmanager.gui.view.container.form.AppSettingsFormContainer;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 *
 * @author simon
 */
public class AppSettingsBuilder
{
    private final IAppSettingsMaskListener listener;
    
    private final AppSettingsFormContainer formContainer;
    
    private final GUIAppSettings data;

    public AppSettingsBuilder(IAppSettingsMaskListener listener, GUIAppSettings data) {
        this.listener = listener;
        this.data = data;
        
        formContainer = new AppSettingsFormContainer();
        
        bindProperties();
        bindListener();
    }
    
    private void bindProperties(){
        formContainer.getTfPathToInkscape().textProperty().bindBidirectional(data.getInkscapePathProperty());
        formContainer.getCbShowDBInfos().selectedProperty().bindBidirectional(data.getShowDBInfosProperty());
    }
    
    private void bindListener(){
        formContainer.getBSave().setOnAction((ActionEvent event) -> {
            listener.save(data);
        });
        
        formContainer.getBSelectPathToInkscape().setOnAction((ActionEvent event) -> {
            listener.selectPathToInkscape();
        });
    }
    
    // getter
    
    public Node getView(){
        return formContainer.getView();
    }
    
}
