
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.AGUITrackedData;
import ch.buhls.billmanager.gui.view.container.form.TrackableEntityFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 * @param <T>
 */
public class TrackedEntityMaskBuilder<T extends AGUITrackedData>
{
    
    // data
    private final T data;
    
    // view
    private final TrackableEntityFormContainer formContainer;
    
    public TrackedEntityMaskBuilder(T data, TrackableEntityFormContainer formContainer) {
        this.data = data;
        this.formContainer = formContainer;
    }
    
    protected void bindProperties()
    {
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfIdDBContainer(), data.getDb_id(), true);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionDBContainer(), data.getDb_version(), true);
        
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionContainer(), data.getVersionNr(), true);
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfChangeTxtContainer(), data.getChangeTxt(), true);
    }
    
    // methods to enable/disable functions from the view
    public void changeToEditMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfChangeTxtContainer());
    }
    
    public void changeToReadOnlyMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfChangeTxtContainer());
    }
    
    public void changeToCreateMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfChangeTxtContainer());
    }
    
    // getter & setter
    public Node getView() {
        return formContainer.getView();
    }

    public TrackableEntityFormContainer getFormContainer() {
        return formContainer;
    }
}
