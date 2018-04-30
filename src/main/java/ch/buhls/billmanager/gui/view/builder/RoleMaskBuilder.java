package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.IMaskBuilder;
import ch.buhls.billmanager.gui.view.container.form.RoleFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.listener.IDefaultMaskListener;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class RoleMaskBuilder implements IMaskBuilder
{

    // data
    private final GUIRole role;

    // view
    private final RoleFormContainer formContainer;

    // listener
    private final IDefaultMaskListener<GUIRole> listener;

    public RoleMaskBuilder(GUIRole role, IDefaultMaskListener<GUIRole> listener) {
        this.role = role;
        this.listener = listener;

        this.formContainer = new RoleFormContainer();
        
        bindListeners();
        bindProperties();
    }

    @Override
    public final void bindProperties() {
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfIdDBContainer(), role.getDb_id(), true);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionDBContainer(), role.getDb_version(), true);

        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfNameContainer(), role.getName(), true);
    }

    @Override
    public final void bindListeners() {
        formContainer.getbSave().setOnAction((event)
                -> {
            this.listener.save(role);
        });
    }

    @Override
    public void changeToEditMode() {
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToSwitchableState(formContainer.getTfNameContainer());
        
        formContainer.getbSave().setDisable(false);
    }

    @Override
    public void changeToShowMode() {
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfNameContainer());
        
        formContainer.getbSave().setDisable(true);
    }

    @Override
    public void changeToCreateMode() {
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());

        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfNameContainer());
        
        formContainer.getbSave().setDisable(false);
    }

    @Override
    public VBox getView() {
        return formContainer.getView();
    }

}
