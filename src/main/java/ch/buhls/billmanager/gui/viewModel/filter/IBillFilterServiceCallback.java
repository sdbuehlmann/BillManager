
package ch.buhls.billmanager.gui.viewModel.filter;

import ch.buhls.billmanager.persistance.database.container.EntityNotFoundException;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;

/**
 *
 * @author simon
 */
@FunctionalInterface
public interface IBillFilterServiceCallback
{
    public Person getPersonByBaseData(PersonBaseData baseData) throws EntityNotFoundException;
}
