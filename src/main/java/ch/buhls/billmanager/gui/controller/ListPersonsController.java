package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.criteria.AgePersonCriteria.AgeFilterType;
import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.viewModel.criteria.CriteriaHandle;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.viewModel.PersonViewModel;
import ch.buhls.billmanager.gui.viewModel.criteria.RolePersonCriteria;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.framework.DEPersonStringCollection;
import ch.buhls.billmanager.gui.view.builder.ListPersonsBuilder;
import ch.buhls.billmanager.gui.view.listener.IListPersonsListener;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import ch.buhls.billmanager.persistance.PersistanceException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ListPersonsController extends AController implements IListPersonsListener
{

    private final ListPersonsBuilder builder;

    private final PersonViewModel personsDataHandler;
    
    private final DEPersonStringCollection stringCollection;

    public ListPersonsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, framework.getStringCollections().getPersonStringCollection().getTabTitle_ListPersons());

        stringCollection = framework.getStringCollections().getPersonStringCollection();
        
        personsDataHandler = dataHandler.getPersonsDataHandler();
        personsDataHandler.reloadPersonsBuffer();

        builder = new ListPersonsBuilder(this, personsDataHandler.getPersonsBufferHack());
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
        new ShowPersonController(persons.get(0).getBaseData(), framework, dataHandler);
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
    public void addArticleToBusket(ObservableList<GUIPerson> selected, int nr) {
        List<GUIPerson> persons = new ArrayList<>(selected);
        GUIArticle marked = dataHandler.getMarkedArticleProperty().get();
        
        if (nr == -1) {
            try {
                String nrAsString = framework.showTextInputDialoque(stringCollection.getDialoqueTxt_NrArticlesToAdd(marked));
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
        
        if (framework.showConfirmDialoque(stringCollection.getConfirmTxt_AddArticle(marked, persons.size(), nr))) {
            for (GUIPerson person : persons) {
                try {
                    GUIPosition guiPosition = dataHandler.createPosition(marked, dataHandler.getNextPositionNr(person), nr);
                    dataHandler.addPositionAndStoreBusket(person, guiPosition);
                }
                catch (PersistanceException ex) {
                    framework.showExceptionDialoque(ex);
                }
            }

            personsDataHandler.reloadPersonsBuffer();
            framework.displayInfoHint(stringCollection.getHintTxt_artAdded(marked, persons.size(), nr));
        }
    }

    @Override
    public void addRole(ObservableList<GUIPerson> selected) {
        List<GUIPerson> persons = new ArrayList<>(selected);
        GUIRole markedRole = dataHandler.getMarkedRole().get();
        
        if (framework.showConfirmDialoque(stringCollection.getConfirmTxt_AddRole(markedRole, persons.size()))) {
            for (GUIPerson person : persons) {
                try {
                    dataHandler.getPersonsDataHandler().addRoleToPerson(person, markedRole);
                }
                catch (PersistanceException ex) {
                    framework.showExceptionDialoque(ex);
                }
            }

            personsDataHandler.reloadPersonsBuffer();
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
                stringCollection.getTabTitle_ListVersions(persons.get(0)),
                dataHandler.getPersonsDataHandler().getPersonVersions(persons.get(0)),
                new IListVersionsListener<GUIPersonBaseData>()
        {
            @Override
            public void show(GUIPersonBaseData data) {
                new ShowPersonController(data, framework, dataHandler);
            }
        });
    }

    @Override
    public void filterMembersByMarkedRole() {
        GUIRole role = dataHandler.getMarkedRole().get();
        
        CriteriaHandle filterHandle = personsDataHandler.addRoleFilter(RolePersonCriteria.RoleFilterType.SHOW, role);
        personsDataHandler.reloadPersonsBuffer();
        
        new FilterHintController(builder, filterHandle, GUIStringCollection.getHintTxt_showRoleFilter(role));
    }

    @Override
    public void hideMarkedRoleMembers() {
        GUIRole role = dataHandler.getMarkedRole().get();
        
        CriteriaHandle filterHandle = personsDataHandler.addRoleFilter(RolePersonCriteria.RoleFilterType.HIDE, role);
        personsDataHandler.reloadPersonsBuffer();
        
        new FilterHintController(builder, filterHandle, GUIStringCollection.getHintTxt_showRoleFilter(role));
    }

    @Override
    public void filterMembersByAge(AgeFilterType ageFilterType) {      
        try {
            String ageAsString = framework.showTextInputDialoque(stringCollection.getDialoqueTxt_AgeFilter());
            if (ageAsString == null) {
                return;
            }
            int age = Integer.parseInt(ageAsString);
            
            GUIFinancialYear year = dataHandler.getMarkedYear().get();
            
            CriteriaHandle filterHandle = personsDataHandler.addAgeFilter(ageFilterType, year, age);
            personsDataHandler.reloadPersonsBuffer();
            
            switch(ageFilterType){
                case EQUAL:
                    new FilterHintController(builder, filterHandle, GUIStringCollection.getFilterHintTxt_Equal(year, age));
                    break;
                case OLDER:
                    new FilterHintController(builder, filterHandle, GUIStringCollection.getFilterHintTxt_Older(year, age));
                    break;
                case YOUNGER:
                    new FilterHintController(builder, filterHandle, GUIStringCollection.getFilterHintTxt_Younger(year, age));
                    break;
                case OLDER_OR_EQUAL:
                    new FilterHintController(builder, filterHandle, GUIStringCollection.getFilterHintTxt_OlderOrEqual(year, age));
                    break;
                case YOUNGER_OR_EQUAL:
                    new FilterHintController(builder, filterHandle, GUIStringCollection.getFilterHintTxt_YoungerOrEqual(year, age));
                    break;
            }
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
            return;
        }
    }

    @Override
    public void registerNewBill(ObservableList<GUIPerson> persons) {
        new RegisterBillController(framework, dataHandler, persons.get(0));
    }

}
