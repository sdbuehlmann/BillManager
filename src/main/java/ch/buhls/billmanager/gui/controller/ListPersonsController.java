package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.view.builder.AListBuilder;
import ch.buhls.billmanager.gui.view.builder.ListPersonsBuilder;
import ch.buhls.billmanager.gui.view.listener.IListPersonsListener;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import ch.buhls.billmanager.persistance.PersistanceException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ListPersonsController extends AController implements IListPersonsListener
{

    private final ListPersonsBuilder builder;

    public ListPersonsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, "Mitglieder");

        builder = new ListPersonsBuilder(this, dataHandler.getPersonsBuffer());
        framework.displayMask(builder.getView(), tabName, IGUIFramework.Area.LEFT).focus();
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIPerson> selected) {
        if (selected.size() > 1) {
            builder.setMenuSelectionMode(AListBuilder.MenuSelectionMode.MULTIPLE);
        }
        else {
            builder.setMenuSelectionMode(AListBuilder.MenuSelectionMode.SINGLE);
        }

        builder.enableToAddArticle(dataHandler.getMarkedArticleProperty().get() != null);
        builder.enableToAddRole(dataHandler.getMarkedRole().get() != null);
    }

    @Override
    public void create() {
        new CreatePersonController(framework, dataHandler);
    }

    @Override
    public void edit(List<GUIPerson> persons) {
        new EditPersonController(framework, dataHandler, persons.get(0));
    }

    @Override
    public void show(List<GUIPerson> persons) {
        new ShowPersonController(persons.get(0).getBaseData(), framework);
    }

    @Override
    public void createNewBill(ObservableList<GUIPerson> persons) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showBusket(ObservableList<GUIPerson> persons) {
        new ManageBusketController(framework, dataHandler, persons.get(0));
    }

    @Override
    public void addArticleToBusket(ObservableList<GUIPerson> persons, int nr) {
        if (framework.confirmToAddArticle()) {
            GUIArticle marked = dataHandler.getMarkedArticleProperty().get();

            for (GUIPerson person : persons) {
                try {
                    GUIPosition guiPosition = dataHandler.createPosition(marked, dataHandler.getNextPositionNr(person), nr);
                    dataHandler.addPositionAndStoreBusket(person, guiPosition);
                }
                catch (PersistanceException ex) {
                    framework.showExceptionDialoque(ex);
                }
            }
        }
    }

    @Override
    public void addRole(ObservableList<GUIPerson> persons) {
        if (framework.confirmToAddRole()) {
            GUIRole markedRole = dataHandler.getMarkedRole().get();

            for (GUIPerson person : persons) {
                try {
                    dataHandler.addRoleToPerson(person, markedRole);
                }
                catch (PersistanceException ex) {
                    framework.showExceptionDialoque(ex);
                }
            }
        }
    }

    @Override
    public void showRoles(ObservableList<GUIPerson> persons) {
        new ManageRolesController(framework, dataHandler, persons.get(0));
    }

    @Override
    public void showVersions(ObservableList<GUIPerson> persons) {
        new ListVersionsController<GUIPersonBaseData>(
                framework,
                dataHandler.getPersonVersions(persons.get(0)),
                GUIStringCollection.getTitleForListPersonVersions(persons.get(0).getBaseData()),
                new IListVersionsListener<GUIPersonBaseData>()
        {
            @Override
            public void show(GUIPersonBaseData data) {
                new ShowPersonController(data, framework);
            }
        });
    }

}
