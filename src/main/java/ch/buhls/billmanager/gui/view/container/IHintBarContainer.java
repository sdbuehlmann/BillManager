
package ch.buhls.billmanager.gui.view.container;

import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.framework.IHintListener;

/**
 *
 * @author simon
 */
public interface IHintBarContainer
{
    public IHintHandle addHint(String text, IHintListener listener);
}
