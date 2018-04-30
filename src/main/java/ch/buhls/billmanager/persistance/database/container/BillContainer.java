
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Bill;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class BillContainer extends AContainer<Bill>
{

    public BillContainer(EntityManager em)
    {
        super(Bill.class, "Bill", em);
    }
}
