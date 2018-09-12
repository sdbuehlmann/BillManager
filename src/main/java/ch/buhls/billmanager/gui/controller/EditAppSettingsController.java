package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.AppSettingsData;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.AppSettingsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IAppSettingsMaskListener;
import ch.buhls.billmanager.model.App;
import ch.buhls.billmanager.model.AppException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class EditAppSettingsController extends AController implements IAppSettingsMaskListener
{

    private final AppSettingsBuilder builder;

    private final AppSettingsData data;

    public EditAppSettingsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.getTitleForEditAppSettings());

        data = dataHandler.getAppSettingsData();
        builder = new AppSettingsBuilder(this, data);

        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(AppSettingsData data) {
        if (framework.confirmToStore()) {
            dataHandler.storeAppSettingsData(data);

            framework.displayInfoHint(GUIStringCollection.getHintTxt_appSettingsStored());
            close();
        }
    }

    @Override
    public void selectPathToInkscape() {
        File file;
        if (data.getInkscapePathProperty().get() == null) {
            file = framework.openFileChooser(tabName, App.INSTANCE.getLastPath());
        }
        else {
            file = framework.openFileChooser(tabName, null);
        }

        if (file != null) {
            data.getInkscapePathProperty().set(file.getPath());
        }
    }
}
