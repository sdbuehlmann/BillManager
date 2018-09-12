package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ManagePositionsMaskBuilder;
import ch.buhls.billmanager.gui.view.listener.IManagePositionsListener;
import ch.buhls.billmanager.persistance.PersistanceException;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ManageBusketController extends AController implements IManagePositionsListener
{

    private final ManagePositionsMaskBuilder builder;

    private final ObservableList<GUIPosition> busket;

    private final GUIPerson person;
    private final GUIPosition emptyPosition;
    private GUIPosition origPositionToEdit;

    public ManageBusketController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, String.format("WK %s %s", person.getBaseData().getPrename().get(), person.getBaseData().getName().get()));

        this.person = person;

        busket = dataHandler.getPersonsDataHandler().getCopyOfPersonBusket(person);

        emptyPosition = dataHandler.createPosition(dataHandler.createArticle()); // create empty position
        builder = new ManagePositionsMaskBuilder(this, busket, emptyPosition);

        builder.enableToAddMarkedPos(dataHandler.getMarkedArticleProperty().get() != null);
        dataHandler.getMarkedArticleProperty().addListener((ObservableValue<? extends GUIArticle> observable, GUIArticle oldValue, GUIArticle newValue) -> {
            builder.enableToAddMarkedPos(newValue != null);
        });

        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save() {
        try {
            if (framework.confirmToStore()) {
                dataHandler.mergeAndStoreBusket(person, busket);
            }

            tabHandle.close();
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

    @Override
    public void editPos(GUIPosition pos) {
        origPositionToEdit = pos;
        busket.remove(pos);

        builder.setEditedPos(dataHandler.duplicatePosition(pos));
        builder.enableToChangePosProperties(true);
    }

    @Override
    public void savePos(GUIPosition pos) {
        busket.add(pos);

        builder.setEditedPos(emptyPosition);
        builder.enableToChangePosProperties(false);

        origPositionToEdit = null;
    }

    @Override
    public void removePos(GUIPosition pos) {
        busket.remove(pos);
    }

    @Override
    public void addMarkedArticle() {
        GUIArticle marked = dataHandler.getMarkedArticleProperty().get();
        GUIPosition pos = dataHandler.createPosition(marked, dataHandler.getNextPositionNr(busket), 1);

        busket.add(pos);
    }

    @Override
    public void selectionChanged(GUIPosition pos) {
        builder.enableToEditOrRemovePos(pos != null);
    }

    @Override
    public void abortEditPos() {
        busket.add(origPositionToEdit);
        origPositionToEdit = null;

        builder.setEditedPos(emptyPosition);
        builder.enableToChangePosProperties(false);
    }

}
