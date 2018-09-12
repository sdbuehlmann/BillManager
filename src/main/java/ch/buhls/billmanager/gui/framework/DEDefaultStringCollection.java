
package ch.buhls.billmanager.gui.framework;

/**
 *
 * @author simon
 */
public class DEDefaultStringCollection<T> implements IStringCollection<T>
{

    @Override
    public String getTabTitle_Create() {
        return "Neu erzeugen";
    }

    @Override
    public String getTabTitle_Edit(T data) {
        return "Bearbeiten";
    }

    @Override
    public String getTabTitle_Show(T data) {
        return "Anzeigen";
    }

    @Override
    public String getHintTxt_Created(T data) {
        return "Erfolgreich erfasst";
    }

    @Override
    public String getHintTxt_Edited(T data) {
        return "Erfolgreich bearbeited";
    }

    @Override
    public String getConfirmTxt_Save(T data) {
        return "";
    }
    
}
