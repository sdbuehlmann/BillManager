
package ch.buhls.billmanager.persistance.database.container;

/**
 *
 * @author sdb
 */
public class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(String entity)
    {
        super("Entity " + entity + " not found in DB");
    }
}
