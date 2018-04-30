
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class PersonBaseDataContainer extends AContainer<PersonBaseData>
{
    
    public PersonBaseDataContainer(EntityManager em) {
        super(PersonBaseData.class, "PersonBaseData", em);
    }
    
}
