
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUIImportedPerson;

/**
 *
 * @author simon
 */
public interface IImportPersonsBuilderListener
{
    public void removePerson(GUIImportedPerson pers);
    public void readFile();
    public void store();
}
