
package ch.buhls.billmanager.gui.data;

/**
 *
 * @author simon
 */
public interface IGUIData<T extends IGUIData>
{
    public T duplicate();
    
    public void assumeValues(T data);
}
