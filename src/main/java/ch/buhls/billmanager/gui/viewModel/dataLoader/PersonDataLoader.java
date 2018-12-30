
package ch.buhls.billmanager.gui.viewModel.dataLoader;

import ch.buhls.billmanager.gui.viewModel.criteria.AgePersonCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.RolePersonCriteria;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Person;
import java.util.ArrayList;
import java.util.List;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;

/**
 *
 * @author simon
 */
public class PersonDataLoader implements IDataLoader<Person>
{
    private final PersistanceFascade persistanceFascade;

    public PersonDataLoader(PersistanceFascade persistanceFascade) {
        this.persistanceFascade = persistanceFascade;
    }
    
    @Override
    public List<Person> loadData(ICriteria<Person> criteria){
        if(criteria instanceof AgePersonCriteria){
            return loadData((AgePersonCriteria)criteria);
        }
        if(criteria instanceof RolePersonCriteria){
            return loadData((RolePersonCriteria)criteria);
        }
        
        return new ArrayList();
    }
    
    @Override
    public List<Person> loadData() {
        return this.persistanceFascade.getAllPersons();
    }
    
    // private methods
    
    private List<Person> loadData(AgePersonCriteria criteria) {
        return this.persistanceFascade.getAllPersons();
    }
    
    private List<Person> loadData(RolePersonCriteria criteria) {
        return this.persistanceFascade.getAllPersons();
    }

}
