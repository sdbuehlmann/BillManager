
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Person;

/**
 *
 * @author simon
 */
public class PersonWrapper implements IDataWrapper<Person, GUIPerson>
{
    @Override
    public GUIPerson wrapEntity(Person entity) {
        return new GUIPerson(entity, new GUIPersonBaseData(entity.getPersonBaseData()));
    }
}
