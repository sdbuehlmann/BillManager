
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Person;
import java.util.List;
import javax.persistence.EntityManager;

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

}
