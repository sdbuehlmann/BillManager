package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.data.GUIImportedPerson;
import ch.buhls.billmanager.gui.view.builder.listener.IImportPersonsBuilderListener;
import ch.buhls.billmanager.gui.view.container.form.ImportPersonsFormContainer;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 *
 * @author simon
 */
public class ImportPersonsBuilder
{

    private final ImportPersonsFormContainer viewContainer;

    private final IImportPersonsBuilderListener listener;

    private final ObservableList<GUIImportedPerson> entites;

    public ImportPersonsBuilder(IImportPersonsBuilderListener listener, ObservableList<GUIImportedPerson> entites) {
        this.listener = listener;
        this.entites = entites;

        viewContainer = new ImportPersonsFormContainer();

        bindData();
        bindListener();
    }

    private void bindData() {
        viewContainer.getTableContainer().getTable().setItems(entites);
    }

    private void bindListener() {
        viewContainer.getbImportData().setOnAction((event)
                -> {
            this.listener.store();
        });
        viewContainer.getbReadFile().setOnAction((event)
                -> {
            this.listener.readFile();
        });
        viewContainer.getMiRemove().setOnAction((event)
                -> {
            this.listener.removePerson(viewContainer.getTableContainer().getTable().getSelectionModel().getSelectedItem());
        });
    }

    public Node getView() {
        return viewContainer.getView();
    }
}
