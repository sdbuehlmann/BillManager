
package ch.buhls.billmanager.gui.viewModel.dataLoader;

import ch.buhls.billmanager.model.data.filter.AgePersonFilter;
import ch.buhls.billmanager.model.data.filter.IFilter;
import ch.buhls.billmanager.model.data.filter.RolePersonFilter;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.database.entities.Person;
import java.util.ArrayList;
import java.util.List;

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
    public List<Person> loadData(IFilter<Person> criteria){
        if(criteria instanceof AgePersonFilter){
            return loadData((AgePersonFilter)criteria);
        }
        if(criteria instanceof RolePersonFilter){
            return loadData((RolePersonFilter)criteria);
        }
        
        return new ArrayList();
    }
    
    private List<Person> loadData(AgePersonFilter criteria) {
        return this.persistanceFascade.getAllPersons();
    }
    
    private List<Person> loadData(RolePersonFilter criteria) {
        return this.persistanceFascade.getAllPersons();
    }

    @Override
    public List<Person> loadData() {
        return this.persistanceFascade.getAllPersons();
    }
    
}
