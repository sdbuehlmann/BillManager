
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.AGUIData;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
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
    
    default ObservableList<G> wrapEntities(List<T> entities){
        ObservableList<G> observableList = FXCollections.observableArrayList();
        for(T entity : entities){
            observableList.add(this.wrapEntity(entity));
        }
        
        return observableList;
    }
    
    default Iterator<T> unwrapListElements(ObservableList<G> list){
        return new UnwrapListIterator<>(list);
    }
}
