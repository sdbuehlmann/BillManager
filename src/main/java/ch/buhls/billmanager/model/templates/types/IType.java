
package ch.buhls.billmanager.model.templates.types;

/**
 *
 * @author sdb
 */
public interface IType<T>
{
    public void setContent(T content);
    
    public String getLabel();
}
