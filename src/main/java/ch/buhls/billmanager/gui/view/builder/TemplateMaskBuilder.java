
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.view.container.form.TemplatesFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class TemplateMaskBuilder implements IMaskBuilder
{
    // data
    private final GUITemplate template;

    // view
    private final TemplatesFormContainer formContainer;

    // listener
    private final IDefaultMaskListener<GUITemplate> listener;

    public TemplateMaskBuilder(GUITemplate template, IDefaultMaskListener<GUITemplate> listener) {
        this.template = template;
        this.listener = listener;
        
        this.formContainer = new TemplatesFormContainer();
        
        bindListeners();
        bindProperties();
    }

    
    
    @Override
    public final void bindProperties() {
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfIdDBContainer(), template.getDb_id(), true);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionDBContainer(), template.getDb_version(), true);

        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfNameContainer(), template.getName(), true);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfMaxPositionsContainer(), template.getMaxNrPositions(), true);
    }

    @Override
    public final void bindListeners() {
        formContainer.getbSave().setOnAction((event)
                -> {
            this.listener.save(template);
        });
    }

    @Override
    public void changeToEditMode() {
       LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
       LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

       LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfNameContainer());
       LabledSwitchableControlContainer.changeToEditableState(formContainer.getNfMaxPositionsContainer());
        
       formContainer.getbSave().setDisable(false);
    }

    @Override
    public void changeToShowMode() {
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfNameContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfMaxPositionsContainer());
        
        formContainer.getbSave().setDisable(true);
    }

    @Override
    public void changeToCreateMode() {
       LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
       LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

       LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfNameContainer());
       LabledSwitchableControlContainer.changeToEditableState(formContainer.getNfMaxPositionsContainer());
        
       formContainer.getbSave().setDisable(false);
    }

    @Override
    public VBox getView() {
        return formContainer.getView();
    }
    
}
