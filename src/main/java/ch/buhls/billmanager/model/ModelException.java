
package ch.buhls.billmanager.model;

/**
 *
 * @author simon
 */
public class ModelException extends Exception
{

    public ModelException(String msg) {
        super(msg);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
