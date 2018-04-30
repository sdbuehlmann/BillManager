
package ch.buhls.billmanager.gui.data.properties;

/**
 *
 * @author simon
 */
public interface IPropertyData<T>
{
    public T get();
    public void set(T set);
    public Object getBean();
    public String getName();
}
