
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author simon
 */
public class PersonContainer extends AContainer<Person>
{

    public PersonContainer(EntityManager em)
    {
        super(Person.class, "Person", em);
    }
    
    public List<Person> getPersonsByRole(Role role) {
        Query query = this.em.
                createQuery("SELECT p FROM Person p JOIN p.roles r WHERE r.id = " + role.getId(), Person.class);
        List<Person> list = query.getResultList();

        return list;
    }

    public Person getPersonByBaseData(PersonBaseData baseData){
        TypedQuery<Person> personQuery = this.em.
                createQuery("SELECT p FROM Person p WHERE p.personBaseData.id = " + baseData.getId(), Person.class);
        List<Person> list = personQuery.getResultList();
        
        if(!list.isEmpty()){
            return list.get(0);
        }
        
        // is a old bas data -> find newest
        TypedQuery<PersonBaseData> baseDataQuery = this.em.
                createQuery("SELECT pbd FROM PersonBaseData pbd WHERE pbd.previousVersion.id = " + baseData.getId(), PersonBaseData.class);
        
        PersonBaseData newest = null;
        
        for(PersonBaseData dbEntry : baseDataQuery.getResultList()){
            if(dbEntry.getFollowingVersion() == null){
                // newest version
                newest = dbEntry;
                break;
            }
        }
        
        personQuery = this.em.
                createQuery("SELECT p FROM Person p WHERE p.personBaseData.id = " + newest.getId(), Person.class);
        
        return personQuery.getSingleResult();
    }

}
