package ch.buhls.billmanager.gui.viewModel.criteria;

import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;

/**
 *
 * @author simon
 */
public class RolePersonCriteria implements ICriteria<Person>
{ 
    
    private final RoleFilterType roleFilterType;
    private final Role role;

    public RolePersonCriteria(RoleFilterType roleFilterType, Role role) {
        this.roleFilterType = roleFilterType;
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        
        if(!(obj instanceof RolePersonCriteria)){
            return false;
        }
        
        RolePersonCriteria other = (RolePersonCriteria)obj;
        
        if(!this.getRole().equals(other.getRole()) ||
                this.getRoleFilterType() != other.getRoleFilterType()){
            return false;
        }
        
        return true;
    }

    public RoleFilterType getRoleFilterType() {
        return roleFilterType;
    }

    public Role getRole() {
        return role;
    }
    
    public enum RoleFilterType
    {
        SHOW,
        HIDE
    }
}
