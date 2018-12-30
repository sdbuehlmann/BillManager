
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.AGUIData;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.Iterator;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 * @param <T>
 * @param <G>
 */
public interface IDataWrapper<T extends AEntity<T>,G extends AGUIData<T>>
{
    public G wrapEntity(T entity);
    
    default Iterator<T> unwrapListElements(ObservableList<G> list){
        return new UnwrapListIterator<>(list);
    }
}
