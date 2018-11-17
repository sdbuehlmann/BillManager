package ch.buhls.billmanager.model.data.filter;

import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class RolePersonFilter implements IFilter<Person>
{ 
    public enum RoleFilterType
    {
        SHOW,
        HIDE
    }

    private final RoleFilterType roleFilterType;
    private final Role role;

    public RolePersonFilter(RoleFilterType roleFilterType, Role role) {
        this.roleFilterType = roleFilterType;
        this.role = role;
    }

    @Override
    public void filterList(List<Person> persons) {
        persons.removeAll(this.getElementsToRemoveSublist(persons));
    }
    
    @Override
    public List<Person> getElementsToRemoveSublist(List<Person> list) {
        List<Person> personsToRemove = new ArrayList<>();
        for (Person pers : list) {
            switch (roleFilterType) {
                case HIDE:
                    if (pers.getRoles().contains(role)) {
                        personsToRemove.add(pers);
                    }
                    break;
                case SHOW:
                    if (!pers.getRoles().contains(role)) {
                        personsToRemove.add(pers);
                    }
                    break;
            }
        }
        
        return personsToRemove;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        
        if(!(obj instanceof RolePersonFilter)){
            return false;
        }
        
        RolePersonFilter other = (RolePersonFilter)obj;
        
        if(!this.getRole().equals(other.getRole()) ||
                this.getRoleFilterType() != other.getRoleFilterType()){
            return false;
        }
        
        return true;
    }
    
    // getter

    public RoleFilterType getRoleFilterType() {
        return roleFilterType;
    }

    public Role getRole() {
        return role;
    }
}
