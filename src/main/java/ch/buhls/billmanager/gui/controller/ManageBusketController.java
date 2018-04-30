package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ManagePositionsMaskBuilder;
import ch.buhls.billmanager.gui.view.listener.IManagePositionsListener;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ManageBusketController extends AController implements IManagePositionsListener
{

    private final ManagePositionsMaskBuilder builder;

    private final ObservableList<GUIPosition> tempBusket;

    private final GUIPerson person;
    private final GUIPosition emptyPosition;

    public ManageBusketController(IGUIFramework framework, DataHandler dataHandler, GUIPerson person) {
        super(framework, dataHandler, "Warenkorb bearbeiten");

        this.person = person;

        tempBusket = dataHandler.getCopyOfPersonBusket(person);

        emptyPosition = dataHandler.createPosition(dataHandler.createArticle()); // create empty position
        builder = new ManagePositionsMaskBuilder(this, tempBusket, emptyPosition);

        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save() {
        
    }

    @Override
    public void editPos(GUIPosition pos) {
        tempBusket.remove(pos);

        builder.setEditedPos(pos);
        builder.enableToChangePosProperties(true);
    }

    @Override
    public void savePos(GUIPosition pos) {
        tempBusket.add(pos);
        
        builder.setEditedPos(emptyPosition);
        builder.enableToChangePosProperties(false);
    }

    @Override
    public void removePos(GUIPosition pos) {
        tempBusket.remove(pos);
    }

    @Override
    public void addMarkedArticle() {
        GUIArticle marked = dataHandler.getMarkedArticle().get();
        GUIPosition pos = dataHandler.createPositionForPerson(marked, person, 1);
        
        tempBusket.add(pos);
    }

    @Override
    public void selectionChanged(GUIPosition pos) {
        builder.enableToEditOrRemovePos(pos != null);
    }

}
