
package ch.buhls.billmanager.persistance.database.services;

/**
 *
 * @author buhls7
 */
public class ServiceException extends Exception
{
    public ServiceException(Type type)
    {
        super(type.toString());
    }
    
    public ServiceException(String msg)
    {
        super(msg);
    }
    
    public enum Type
    {
        ADDING_NOT_SUCCESFULLY,
        WRONG_PARAMETERS_HANDED,
        ENTITY_NOT_FOUND,
        INVALID_RELATION,
        ENTITY_IS_STILL_IN_USE
    }
}
