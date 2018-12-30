
package ch.buhls.billmanager.gui.viewModel.criteria;

import ch.buhls.billmanager.persistance.database.entities.Bill;

/**
 *
 * @author simon
 */
public class BillStatusCriteria implements ICriteria<Bill>
{

    private final Status status;
    
    public BillStatusCriteria(Status status) {
        this.status = status;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public enum Status{
        SENDET,
        PAID,
        STORNO,
        ALL
    }
}
