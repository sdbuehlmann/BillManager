
package ch.buhls.billmanager.model.services;

import ch.buhls.billmanager.gui.data.GUIRole;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;

/**
 *
 * @author simon
 */
public class RolesBufferService
{
    public Role create(){
        return new Role();
    }
    
    public Role copy(Role orig){
        return new Role(orig);
    }
    
    public void store(Role role, List<Role> roles){
        
    }
    
    public void update(GUIRole role, List<Role> roles){
        
    }
}
