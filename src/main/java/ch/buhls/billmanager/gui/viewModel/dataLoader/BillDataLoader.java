
package ch.buhls.billmanager.gui.viewModel.dataLoader;

import ch.buhls.billmanager.gui.viewModel.criteria.BillRoleCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.BillStatusCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class BillDataLoader implements IDataLoader<Bill>
{
    private final PersistanceFascade persistanceFascade;

    public BillDataLoader(PersistanceFascade persistanceFascade) {
        this.persistanceFascade = persistanceFascade;
    }
    
    @Override
    public List<Bill> loadData(ICriteria<Bill> criteria) {
        if(criteria instanceof BillRoleCriteria){
            return loadData((BillRoleCriteria)criteria);
        }
        if(criteria instanceof BillStatusCriteria){
            return loadData((BillStatusCriteria)criteria);
        }
        
        return new ArrayList();
    }

    @Override
    public List<Bill> loadData() {
        return this.persistanceFascade.getAllBills();
    }
    
    // private methods
    
    private List<Bill> loadData(BillRoleCriteria criteria) {
        return this.persistanceFascade.getBillsByRole(criteria.getRole());
    }
    
    private List<Bill> loadData(BillStatusCriteria criteria) {
        List<Bill> bills;
        
        switch(criteria.getStatus()){
            case ALL:
                bills = this.persistanceFascade.getAllBills();
                break;
            case PAID:
                bills = this.persistanceFascade.getBillsByState(Bill.BillState.PAID);
                break;
            case SENDET:
                bills = this.persistanceFascade.getBillsByState(Bill.BillState.SENDET);
                break;
            case STORNO:
                bills = this.persistanceFascade.getBillsByState(Bill.BillState.CANCELED);
                break;
            default:
                bills = new ArrayList<>();
                break;
        }
        
        return bills;
    }
}
