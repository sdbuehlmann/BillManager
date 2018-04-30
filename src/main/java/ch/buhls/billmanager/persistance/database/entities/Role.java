
package ch.buhls.billmanager.persistance.database.entities;

import javax.persistence.Entity;

/**
 *
 * @author simon
 */
@Entity
public class Role extends AEntity<Role>
{
    private String name;
    
    private boolean active;

    public Role() {
    }
    
    public Role(Role other){
        other.copyData(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void copyData(Role other) {
        other.name = name;
        other.active = active;
        
        super.copyData(other);
    }

}
