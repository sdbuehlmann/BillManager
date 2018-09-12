package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class RolePersonFilter implements IPersonFilter
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
    public List<Person> filterList(List<Person> persons) {
        List<Person> filteredPersons = new ArrayList<>();
        for (Person pers : persons) {
            switch (roleFilterType) {
                case HIDE:
                    if (!pers.getRoles().contains(role)) {
                        filteredPersons.add(pers);
                    }
                    break;
                case SHOW:
                    if (pers.getRoles().contains(role)) {
                        filteredPersons.add(pers);
                    }
                    break;
            }
        }
        return filteredPersons;
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
