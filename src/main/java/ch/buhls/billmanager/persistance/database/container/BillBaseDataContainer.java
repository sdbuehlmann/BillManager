
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.BillBaseData;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class BillBaseDataContainer extends AContainer<BillBaseData>
{
    
    public BillBaseDataContainer(EntityManager em) {
        super(BillBaseData.class, "BillBaseData", em);
    }
    
}
