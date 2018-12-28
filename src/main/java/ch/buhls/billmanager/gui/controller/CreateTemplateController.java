
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.TemplateMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;
import java.io.File;

/**
 *
 * @author simon
 */
public class CreateTemplateController extends AController implements IDefaultMaskListener<GUITemplate>
{
    private TemplateMaskBuilder builder;
    private File templateFile;
    
    public CreateTemplateController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, "Rechnungsvorlage erstellen");
        
        templateFile = framework.openFileChooser("Template.svg auswählen", null);
        
        if(templateFile != null){
            GUITemplate template = dataHandler.createTemplate();
            template.getName().set(templateFile.getName());
            
            builder = new TemplateMaskBuilder(template, this);
            builder.changeToCreateMode();
            display(builder.getView(), IGUIFramework.Area.RIGHT);
        }else{
            framework.showExceptionDialoque(new Exception("Template kann nicht erzeugt werden"));
        }
    }

    @Override
    public void save(GUITemplate entity) {
        try {
            dataHandler.storeTemplate(entity, templateFile);
            tabHandle.close();
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
    
}
