
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class FinancialYearContainer extends AContainer<FinancialYear>
{
    
    public FinancialYearContainer(EntityManager em) {
        super(FinancialYear.class, "FinancialYear", em);
    }
    
}
