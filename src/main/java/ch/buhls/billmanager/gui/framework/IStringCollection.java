
package ch.buhls.billmanager.gui.framework;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IStringCollection<T>
{
    public String getTabTitle_Create();
    public String getTabTitle_Edit(T data);
    public String getTabTitle_Show(T data);
    
    public String getHintTxt_Created(T data);
    public String getHintTxt_Edited(T data);
    
    public String getConfirmTxt_Save(T data);
}
