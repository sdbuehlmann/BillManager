
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author simon
 */
public class PersonBaseDataContainer extends AContainer<PersonBaseData>
{
    
    public PersonBaseDataContainer(EntityManager em) {
        super(PersonBaseData.class, "PersonBaseData", em);
    }
    
    
    public List<PersonBaseData> findByPrename(String prename){
        TypedQuery<PersonBaseData> query = this.em.createQuery("SELECT p From PersonBaseData p WHERE p.prename LIKE " + prename, PersonBaseData.class);
        List<PersonBaseData> temp = query.getResultList();
        return temp;
    }
    
    public PersonBaseData findHighestPersonalID(){
        TypedQuery<PersonBaseData> query = this.em.createQuery("SELECT MAX(p.personalID) FROM PersonBaseData p", PersonBaseData.class);
        return query.getSingleResult();
    }
}
