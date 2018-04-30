
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class RoleContainer extends AContainer<Role>
{

    public RoleContainer(EntityManager em) {
        super(Role.class, "Role", em);
    }

}
