
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.AGUIData;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 * @param <T>
 * @param <G>
 */
public class UnwrapListIterator<T extends AEntity<T>,G extends AGUIData<T>>  implements Iterator<T>
{
    private final ObservableList<G> list;
    private final List<G> originalList;
    
    private int activeElement;
    
    public UnwrapListIterator(ObservableList<G> list){
        this.list = list;
        this.originalList = new ArrayList<>(list);
        
        this.activeElement = -1;
    }
    
    @Override
    public boolean hasNext() {
        return(activeElement + 1 < originalList.size());
    }

    @Override
    public T next() {
        activeElement++;
        return this.originalList.get(activeElement).getData();
    }

    @Override
    public void remove() {
        this.list.remove(this.originalList.get(activeElement));
    }
    
}
