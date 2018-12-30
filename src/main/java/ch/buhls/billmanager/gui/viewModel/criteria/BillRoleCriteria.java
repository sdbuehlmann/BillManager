
package ch.buhls.billmanager.gui.viewModel.criteria;

import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Role;

/**
 *
 * @author simon
 */
public class BillRoleCriteria implements ICriteria<Bill>
{
    private final Role role;
    
    public BillRoleCriteria(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
}
