
package ch.buhls.billmanager.model.services;

import ch.buhls.billmanager.model.data.filter.AgePersonFilter;
import ch.buhls.billmanager.model.data.filter.IFilterHandle;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author simon
 */
public class PersonDataManager implements IPersonDataManager
{
    private final IDataBuffer<Person> buffer;
    private final IPersonPersistanceService persistanceService;
    
    // filters
    private final List<AgePersonFilter> ageFilters;

    public PersonDataManager(IDataBuffer<Person> buffer, IPersonPersistanceService persistanceService) {
        this.buffer = buffer;
        this.persistanceService = persistanceService;
        
        this.ageFilters = new LinkedList<>();
    }

    @Override
    public void reloadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IFilterHandle addRoleFilter(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IFilterHandle addAgeFilter(AgePersonFilter filter) {
        this.ageFilters.add(filter);
        
        return new IFilterHandle()
        {
            AgePersonFilter filterToBeDeleted = filter;
            
            @Override
            public void delete() {
                ageFilters.remove(filterToBeDeleted);
            }
        };
    }

    @Override
    public IFilterHandle addBillFilter(Bill bill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person createPerson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storePerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person editPerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person editPersonData(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePersonData(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
