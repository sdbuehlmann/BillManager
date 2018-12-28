package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.TemplateMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class EditTemplateController extends AController implements IDefaultMaskListener<GUITemplate>
{

    private TemplateMaskBuilder builder;

    public EditTemplateController(IGUIFramework framework, DataHandler dataHandler, GUITemplate template) {
        super(framework, dataHandler, "Rechnungsvorlage erstellen");

        template = dataHandler.editTemplate(template);

        builder = new TemplateMaskBuilder(template, this);
        builder.changeToEditMode();
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);

    }

    @Override
    public void save(GUITemplate entity) {
        try {
            dataHandler.updateTemplate(entity);
            tabHandle.close();
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

}
