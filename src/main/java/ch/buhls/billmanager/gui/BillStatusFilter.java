
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Bill.BillState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class BillStatusFilter implements IFilter<Bill>
{

    private final BillState status;
    
    public BillStatusFilter(BillState status) {
        this.status = status;
    }
    

    @Override
    public void filterList(List<Bill> bills) {
        bills.removeAll(this.getElementsToRemoveSublist(bills));
    }

    @Override
    public List<Bill> getElementsToRemoveSublist(List<Bill> bills) {
        List<Bill> elementsToRemove = new ArrayList<>();
        
        for(Bill bill : bills){
            if(bill.getBillState() != status){
                elementsToRemove.add(bill);
            }
        }
        
        return elementsToRemove;
    }
    
}
