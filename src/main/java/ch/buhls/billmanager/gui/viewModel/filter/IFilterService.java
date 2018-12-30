package ch.buhls.billmanager.gui.viewModel.filter;

import ch.buhls.billmanager.model.data.filter.IFilter;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IFilterService<T>
{
    default void filter(List<IFilter<T>> criterias, Iterator<T> elements) {
        while(elements.hasNext()){
            if(!this.doesElementMatchTheCriterias(criterias, elements.next())){
                elements.remove();
            }
        }
    }
    
    default boolean doesElementMatchTheCriterias(List<IFilter<T>> criterias, T element) {
        for(IFilter<T> criteria : criterias){
            if(!this.doesElementMatchTheCriteria(criteria, element)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean doesElementMatchTheCriteria(IFilter<T> criteria, T element);
    
}
