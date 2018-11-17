
package ch.buhls.billmanager.model.data.filter;

import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class BillRoleFilter implements IFilter<Bill>
{
    private final Role role;
    
    public BillRoleFilter(Role role) {
        this.role = role;
    }

    @Override
    public void filterList(List<Bill> bills) {
        bills.removeAll(this.getElementsToRemoveSublist(bills));
    }

    @Override
    public List<Bill> getElementsToRemoveSublist(List<Bill> bills) {
        List<Bill> elementsToRemove = new ArrayList<>();
        
        // ToDo: Datenmodell anpassen: Hier muss ich zugriff auf die Rollenzugehörigkeiten der Personen haben.
        /*for(Bill bill : bills){
            if(bill!= status){
                elementsToRemove.add(bill);
            }
        }*/
        
        return elementsToRemove;
    }
    
}
