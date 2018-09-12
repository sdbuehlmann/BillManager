
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.persistance.database.entities.Person;
import java.util.List;

/**
 *
 * @author simon
 */
public interface IPersonFilter
{
    public List<Person> filterList(List<Person> persons);
}
