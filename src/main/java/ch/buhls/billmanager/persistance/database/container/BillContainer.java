
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Bill;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    
    public List<Bill> findByFinancialYearID(int yearID){
        TypedQuery<Bill> query = this.em.createQuery("SELECT b From Bill b JOIN b.financialYear y WHERE y.id LIKE "+yearID, Bill.class);
        List<Bill> temp = query.getResultList();
        return temp;
    }
}
