
package ch.buhls.billmanager.model.services;

import ch.buhls.billmanager.model.data.filter.AgePersonFilter;
import ch.buhls.billmanager.model.data.filter.IFilterHandle;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;

/** Is responsible for connection to the model (and persistence) for all persons data.
 *
 * @author simon
 */
public interface IPersonDataManager
{
    public void reloadData();
    
    // data filters
    public IFilterHandle addRoleFilter(Role role);
    public IFilterHandle addAgeFilter(AgePersonFilter filter);
    public IFilterHandle addBillFilter(Bill bill);

    // persons management
    public Person createPerson();
    public void storePerson(Person person);
    
    public Person editPerson(Person person);
    public void updatePerson(Person person);
    
    public Person editPersonData(Person person);
    public void updatePersonData(Person person);
}
