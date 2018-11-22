package ch.buhls.billmanager.model.services;

import ch.buhls.billmanager.model.ModelException;
import ch.buhls.billmanager.model.data.filter.ListFiltersContainer;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author simon
 */
public class BillsBufferService implements IBillsBufferService
{

    // data buffers
    private final ListFiltersContainer<Bill> filtersContainer;
    private final List<Bill> buffer;
    
    // listener
    private IBufferListener<Bill> listener;

    // filters
    private EStatus filterStatus;
    private Role filterRole;

    // services
    private PersistanceFascade persistanceFascade;

    public BillsBufferService(PersistanceFascade persistanceFascade, IBufferListener<Bill> listener) {
        this.persistanceFascade = persistanceFascade;
        this.listener = listener;
        
        filtersContainer = new ListFiltersContainer<>();
        buffer = FXCollections.observableArrayList();

        filterStatus = EStatus.ALL;
        filterRole = null;
    }

    @Override
    public void storeBill(Bill bill, Person person) throws Exception {
        // check person and bill
        if(person.getPersonBaseData().equals(bill.getPersonBaseData())){
            throw new ModelException("Buffer error: The handed bill and person were not matching");
        }
        
        persistanceFascade.storeBill(bill);
        
        if(this.doesTheEntityPassingTheFilterCriteria(bill, person)){
            buffer.add(bill);
            listener.added(bill);
        }
    }

    @Override
    public void updateBill(Bill bill, Person person) throws Exception {
        // check person and bill
        if(!person.getPersonBaseData().equals(bill.getPersonBaseData())){
            throw new ModelException("Buffer error: The handed bill and person were not matching");
        }
        
        persistanceFascade.storeBill(bill);
        
        if(this.buffer.contains(bill) && !this.doesTheEntityPassingTheFilterCriteria(bill, person)){
            buffer.remove(bill);
            listener.removed(bill);
        }
        if(!this.buffer.contains(bill) && this.doesTheEntityPassingTheFilterCriteria(bill, person)){
            buffer.add(bill);
            listener.added(bill);
        }
    }
    
    @Override
    public Bill copyBill(Bill bill) {
        Bill copy = new Bill(bill);
        return copy;
    }

    @Override
    public List<Bill> getBillsBuffer() {
        return buffer;
    }

    @Override
    public void reloadBillsBuffer() {
        buffer.clear();

        if (this.filterRole != null) {
            switch (this.filterStatus) {
                case ALL:
                    buffer.addAll(this.persistanceFascade.getBillsByRole(filterRole));
                    break;
                case PAID:
                    buffer.addAll(this.persistanceFascade.getBillsByRoleAndState(filterRole, Bill.BillState.PAID));
                    break;
                case SENDET:
                    buffer.addAll(this.persistanceFascade.getBillsByRoleAndState(filterRole, Bill.BillState.SENDET));
                    break;
                case STORNO:
                    buffer.addAll(this.persistanceFascade.getBillsByRoleAndState(filterRole, Bill.BillState.CANCELED));
                    break;
            }
        }
        else {
            switch (this.filterStatus) {
                case ALL:
                    buffer.addAll(this.persistanceFascade.getAllBills());
                    break;
                case PAID:
                    buffer.addAll(this.persistanceFascade.getBillsByState(Bill.BillState.PAID));
                    break;
                case SENDET:
                    buffer.addAll(this.persistanceFascade.getBillsByState(Bill.BillState.SENDET));
                    break;
                case STORNO:
                    buffer.addAll(this.persistanceFascade.getBillsByState(Bill.BillState.CANCELED));
                    break;
            }
        }
        
        listener.bufferCleared();
        for(Bill bufferedBill : buffer){
            listener.added(bufferedBill);
        }
    }

    @Override
    public void setStatusFilter(EStatus status) {
        this.filterStatus = status;
    }

    @Override
    public void setRoleFilter(Role role) {
        this.filterRole = role;
    }

    @Override
    public void resetRoleFilter() {
        this.filterRole = null;
    }

    // private methods
    private boolean doesTheEntityPassingTheFilterCriteria(Bill bill, Person person) {
        // check role
        if (this.filterRole != null && !person.getRoles().contains(this.filterRole)) {
            // person is not filter role member.
            return false;
        }

        // check status
        switch(this.filterStatus){
            case ALL:
                return true;
            case PAID:
                return (bill.getBillState() == Bill.BillState.PAID);
            case SENDET:
                return (bill.getBillState() == Bill.BillState.SENDET);
            case STORNO:
                return (bill.getBillState() == Bill.BillState.CANCELED);
        }
        
        return false;
    }

}
