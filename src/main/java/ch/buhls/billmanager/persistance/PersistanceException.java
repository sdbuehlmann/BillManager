
package ch.buhls.billmanager.persistance;

/**
 *
 * @author simon
 */
public class PersistanceException extends Exception
{
    public PersistanceException(Throwable cause) {
        super(cause);
    }
    
    public PersistanceException(String message) {
        super(message);
    }
}
