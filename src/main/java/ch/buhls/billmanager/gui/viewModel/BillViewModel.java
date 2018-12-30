package ch.buhls.billmanager.gui.viewModel;

import ch.buhls.billmanager.gui.data.CopiedDataObjectContainer;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUICreateBillsData;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIRegisterBillData;
import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.gui.viewModel.buffer.BufferDataService;
import ch.buhls.billmanager.gui.viewModel.criteria.BillRoleCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.BillStatusCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.CriteriaHandle;
import ch.buhls.billmanager.model.App;
import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.ModelFascade;
import ch.buhls.billmanager.model.Project;
import ch.buhls.billmanager.gui.viewModel.criteria.IFilterHandle;
import ch.buhls.billmanager.gui.viewModel.dataLoader.BillDataLoader;
import ch.buhls.billmanager.gui.viewModel.filter.BillFilterService;
import ch.buhls.billmanager.gui.viewModel.wrappers.BillWrapper;
import ch.buhls.billmanager.gui.viewModel.wrappers.FinancialYearWrapper;
import ch.buhls.billmanager.gui.viewModel.wrappers.TemplateWrapper;
import ch.buhls.billmanager.model.services.DocumentService;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class BillViewModel
{
    private static final Logger LOGGER = Logger.getLogger(BillViewModel.class.getName());

    private final App app;
    private final Project project;
    
    private final ModelFascade modelFascade;
    private final PersistanceFascade persistanceFascade;
    
    private final BufferDataService<Bill, GUIBill> bufferService;
    private final DocumentService docService;

    public BillViewModel(Project project) {
        this.project = project;
        this.app = App.INSTANCE;
        this.persistanceFascade = project.getDb();
        this.modelFascade = new ModelFascade();

        this.bufferService = new BufferDataService<>(
                new BillFilterService((baseData) -> {
                     return this.persistanceFascade.getPersonContainer().findByID(baseData.getPersonId());
                }),
                new BillDataLoader(persistanceFascade),
                new BillWrapper());
        
        this.docService = new DocumentService(modelFascade, project);
    }

    public GUIRegisterBillData createRegisterBillData(GUIPerson person) {
        GUIRegisterBillData data = new GUIRegisterBillData(
                new FinancialYearWrapper().wrapEntities(persistanceFascade.getAllFinancialYears()), 
                person);

        data.getPaymentDeadlineInDays().set(app.getLastPaymentDeadlineInDays());
        data.getLocation().set(app.getLastLocation());

        return data;
    }

    public GUICreateBillsData createBills(List<GUIPerson> persons) {
        GUICreateBillsData data = new GUICreateBillsData(
                new TemplateWrapper().wrapEntities(persistanceFascade.getAllBillTemplates()), 
                new FinancialYearWrapper().wrapEntities(persistanceFascade.getAllFinancialYears()), 
                persons);

        data.getPaymentDeadlineInDays().set(app.getLastPaymentDeadlineInDays());
        data.getLocation().set(app.getLastLocation());

        return data;
    }

    public CopiedDataObjectContainer<GUIBill> copyBill(GUIBill bill) {
        GUIBill billCopy = new BillWrapper().wrapEntity(new Bill(bill.getData()));

        return new CopiedDataObjectContainer<>(bill, billCopy);
    }

    public void storeBills(GUICreateBillsData data) throws Exception{
        for (GUIPerson person : data.getPersons()) {
            // create db entry
            Bill bill = new Bill();

            bill.setBillState(Bill.BillState.SENDET);
            bill.setPaymentPeriodInDays(data.getPaymentDeadlineInDays().get());
            bill.setDateSendet(Date.from(data.getDate().get().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            bill.setLocation(data.getLocation().get());
            bill.setTemplate(data.getSelectedTemplate().get().getData());
            bill.setFinancialYear(data.getSelectedYear().get().getData());
            bill.getPositions().addAll(person.getData().getBusket());
            bill.setPersonBaseData(person.getBaseData().getData());

            this.persistanceFascade.storeBill(bill);
            this.bufferService.add(bill);

            this.docService.createDoc(
                    data.getSelectedTemplate().get().getData(),
                    person.getData(),
                    data.getLocation().get(),
                    data.getDate().get(),
                    bill.getId());

            person.getData().getBills().add(bill);
            person.getData().getBusket().clear();

            persistanceFascade.updatePerson(person.getData());
        }
    }

    public void updateBill(CopiedDataObjectContainer<GUIBill> copiedDataContainer) throws Exception {
        if (copiedDataContainer.getCopiedDataObject().getClosedDate().get() != null) {
            this.app.setLastUsedDate(copiedDataContainer.getCopiedDataObject().getClosedDate().get());
        }
        
        this.persistanceFascade.updateBill(copiedDataContainer.getCopiedDataObject().getData());
        this.bufferService.update(copiedDataContainer);
    }

    public ObservableList<GUIBill> getBuffer() {
        return this.bufferService.getBuffer();
    }

    public void reloadBuffer() {
        this.bufferService.reloadData();
    }

    // criterias
    
    public IFilterHandle setBillStatusFilter(GUIBill.GUIBillStatus status) {
        BillStatusCriteria criteria;
        
        switch (status) {
            case PAID:
                criteria = new BillStatusCriteria(BillStatusCriteria.Status.PAID);
                break;
            case SENDET:
                criteria = new BillStatusCriteria(BillStatusCriteria.Status.SENDET);
                break;
            case STORNO:
                criteria = new BillStatusCriteria(BillStatusCriteria.Status.STORNO);
                break;
                
            default:
                criteria = new BillStatusCriteria(BillStatusCriteria.Status.ALL);
        }
        this.bufferService.addCriteria(criteria);
        
        return new CriteriaHandle(criteria, bufferService);
    }

    public IFilterHandle setBillRoleFilter(GUIRole role) {
        BillRoleCriteria criteria = new BillRoleCriteria(role.getData());
        this.bufferService.addCriteria(criteria);
        
        return new CriteriaHandle(criteria, bufferService);
    }

    // utils
    
    public void registerBill(GUIRegisterBillData data, File billPDF) throws ModelException, PersistanceException {
        GUIPerson person = data.getPerson().get();
        // create db entry
        Bill bill = persistanceFascade.createBill();

        bill.setBillState(Bill.BillState.SENDET);
        bill.setPaymentPeriodInDays(data.getPaymentDeadlineInDays().get());
        bill.setDateSendet(Date.from(data.getDate().get().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bill.setLocation(data.getLocation().get());
        bill.setFinancialYear(data.getSelectedYear().get().getData());
        bill.getPositions().addAll(person.getData().getBusket());
        bill.setPersonBaseData(person.getBaseData().getData());

        persistanceFascade.storeBill(bill);

        person.getData().getBills().add(bill);
        person.getData().getBusket().clear();

        persistanceFascade.updatePerson(person.getData());

        File dest = new File(project.getLocationBills(), ModelFascade.createBillFilename(bill.getId(), ".pdf"));
        modelFascade.copyPDF(billPDF, dest);
    }

    public void showPDF(GUIBill bill) throws ModelException {
        this.docService.showPDF(bill.getData());
    }

    public void printPDFs(List<GUIBill> bills) throws ModelException {
        List<Bill> billDatas = new ArrayList<>(bills.size());

        for (GUIBill bill : bills) {
            billDatas.add(bill.getData());
        }

        this.docService.printPDFs(billDatas);
    }

    public void setStateToPaid(GUIBill billView, LocalDate date) throws Exception{
        CopiedDataObjectContainer<GUIBill> copiedDataContainer = this.copyBill(billView);
        copiedDataContainer.getCopiedDataObject().getState().set(GUIBill.GUIBillStatus.PAID);
        copiedDataContainer.getCopiedDataObject().getClosedDate().set(date);
        
        this.updateBill(copiedDataContainer);
    }
    
    public LocalDate getLastPaidDate(){
        return this.app.getLastUsedDate();
    }
}
