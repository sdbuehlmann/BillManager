package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIAppSettings;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.AppSettingsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IAppSettingsMaskListener;
import ch.buhls.billmanager.model.App;
import java.io.File;

/**
 *
 * @author simon
 */
public class EditAppSettingsController extends AFormController<GUIAppSettings> implements IAppSettingsMaskListener
{

    private final AppSettingsBuilder builder;

    private final GUIAppSettings data;

    public EditAppSettingsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, framework.getStringCollections().getAppSettingsStringCollection());

        data = dataHandler.getAppSettingsData();
        builder = new AppSettingsBuilder(this, data);

        displayEditMask(builder.getView(), data);
    }

    @Override
    public void save(GUIAppSettings data) {
        if (displayConfirmToStoreDialoque(data)) {
            dataHandler.storeAppSettingsData(data);
            closeMask();
            displayEditedInfoHint(data);
        }
    }

    @Override
    public void selectPathToInkscape() {
        File file;
        if (data.getInkscapePathProperty().get() == null) {
            file = framework.openFileChooser(stringCollection.getTabTitle_Edit(data), App.INSTANCE.getLastPath());
        }
        else {
            file = framework.openFileChooser(stringCollection.getTabTitle_Edit(data), null);
        }

        if (file != null) {
            data.getInkscapePathProperty().set(file.getPath());
        }
    }
}
