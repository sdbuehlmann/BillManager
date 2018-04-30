
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class BillTemplateContainer extends AContainer<BillTemplate>
{
    
    public BillTemplateContainer(EntityManager em) {
        super(BillTemplate.class, "BillTemplate", em);
    }
    
}
