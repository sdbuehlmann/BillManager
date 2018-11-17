
package ch.buhls.billmanager.model.services;

import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;

/**
 *
 * @author simon
 */
public interface IBillsBufferService
{
    // data handling
    public void storeBill(Bill bill, Person person) throws Exception;
    public void updateBill(Bill bill, Person person)  throws Exception;
    public Bill copyBill(Bill bill);

    // buffer
    public List<Bill> getBillsBuffer();
    public void reloadBillsBuffer();
    
    // filter
    public void setStatusFilter(EStatus status);
    public void setRoleFilter(Role role);
    public void resetRoleFilter();
    
    public enum EStatus{
        SENDET,
        PAID,
        STORNO,
        ALL
    }
}
