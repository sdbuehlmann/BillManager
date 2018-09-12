package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.AgePersonFilter.AgeFilterType;
import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.FilterHandle;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.PersonsDataHandler;
import ch.buhls.billmanager.gui.RolePersonFilter;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.IHintHandle;
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

    private final PersonsDataHandler personsDataHandler;

    private IHintHandle showRoleFilterHint;
    private IHintHandle hideRoleFilterHint;

    public ListPersonsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, "Mitglieder");

        personsDataHandler = dataHandler.getPersonsDataHandler();
        personsDataHandler.reloadPersonsBuffer();

        builder = new ListPersonsBuilder(this, personsDataHandler.getPersonsBuffer());
        this.bindBuilder(builder);

        this.display(builder.getView(), IGUIFramework.Area.LEFT);
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIPerson> selected) {
        if (selected.size() > 1) {
            builder.enableMultiplePersonsSelectedInteractions();
        }
        else {
            builder.enableSinglePersonSelectedInteractions();
        }

        builder.enableToAddArticle(dataHandler.getMarkedArticleProperty().get() != null);
        builder.enableRoleInteractions(dataHandler.getMarkedRole().get() != null);
        builder.enableYearInteractions(dataHandler.getMarkedYear().get() != null);
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
        new CreateBillController(framework, dataHandler, persons);
    }

    @Override
    public void showBusket(ObservableList<GUIPerson> persons) {
        new ManageBusketController(framework, dataHandler, persons.get(0));
    }

    @Override
    public void addArticleToBusket(ObservableList<GUIPerson> persons, int nr) {
        if (nr == -1) {
            try {
                String nrAsString = framework.showTextInputDialoque(GUIStringCollection.PERSON_ADD_ART_TO_BILL, "Beliebige Anzahl Artikel spezifizieren.", "Anzahl Artikel");
                if (nrAsString == null) {
                    return;
                }
                nr = Integer.parseInt(nrAsString);
            }
            catch (Exception ex) {
                framework.showExceptionDialoque(ex);
                return;
            }
        }

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

            framework.displayInfoHint(GUIStringCollection.getHintTxt_artAdded(marked, persons.size(), nr));
        }
    }

    @Override
    public void addRole(ObservableList<GUIPerson> persons) {
        if (framework.confirmToAddRole()) {
            GUIRole markedRole = dataHandler.getMarkedRole().get();

            for (GUIPerson person : persons) {
                try {
                    dataHandler.getPersonsDataHandler().addRoleToPerson(person, markedRole);
                }
                catch (PersistanceException ex) {
                    framework.showExceptionDialoque(ex);
                }
            }

            framework.displayInfoHint(GUIStringCollection.getHintTxt_roleAdded(markedRole, persons.size()));
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
                dataHandler,
                GUIStringCollection.getTitleForListPersonVersions(persons.get(0).getBaseData()),
                dataHandler.getPersonsDataHandler().getPersonVersions(persons.get(0)),
                new IListVersionsListener<GUIPersonBaseData>()
        {
            @Override
            public void show(GUIPersonBaseData data) {
                new ShowPersonController(data, framework);
            }
        });
    }

    @Override
    public void filterMembersByMarkedRole() {
        GUIRole role = dataHandler.getMarkedRole().get();
        
        FilterHandle filterHandle = personsDataHandler.addRoleFilter(RolePersonFilter.RoleFilterType.SHOW, role);
        personsDataHandler.reloadPersonsBuffer();
        
        new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getHintTxt_showRoleFilter(role));
    }

    @Override
    public void hideMarkedRoleMembers() {
        GUIRole role = dataHandler.getMarkedRole().get();
        
        FilterHandle filterHandle = personsDataHandler.addRoleFilter(RolePersonFilter.RoleFilterType.HIDE, role);
        personsDataHandler.reloadPersonsBuffer();
        
        new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getHintTxt_showRoleFilter(role));
    }

    @Override
    public void filterMembersByAge(AgeFilterType ageFilterType) {      
        try {
            String ageAsString = framework.showTextInputDialoque(GUIStringCollection.PERSON_AGE_FILTER, "Bitte spezifiziere das Alter für den Filter.", "Alter");
            if (ageAsString == null) {
                return;
            }
            int age = Integer.parseInt(ageAsString);
            
            GUIFinancialYear year = dataHandler.getMarkedYear().get();
            
            FilterHandle filterHandle = personsDataHandler.addAgeFilter(ageFilterType, year, age);
            personsDataHandler.reloadPersonsBuffer();
            
            switch(ageFilterType){
                case EQUAL:
                    new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getFilterHintTxt_Equal(year, age));
                    break;
                case OLDER:
                    new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getFilterHintTxt_Older(year, age));
                    break;
                case YOUNGER:
                    new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getFilterHintTxt_Younger(year, age));
                    break;
                case OLDER_OR_EQUAL:
                    new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getFilterHintTxt_OlderOrEqual(year, age));
                    break;
                case YOUNGER_OR_EQUAL:
                    new FilterHintController(builder, personsDataHandler, filterHandle, GUIStringCollection.getFilterHintTxt_YoungerOrEqual(year, age));
                    break;
            }
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
            return;
        }
    }

}
