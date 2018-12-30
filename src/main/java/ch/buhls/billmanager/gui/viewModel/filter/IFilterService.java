package ch.buhls.billmanager.gui.viewModel.filter;

import java.util.Iterator;
import java.util.List;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IFilterService<T>
{
    default void filter(List<ICriteria<T>> criterias, Iterator<T> elements) {
        while(elements.hasNext()){
            if(!this.doesElementMatchTheCriterias(criterias, elements.next())){
                elements.remove();
            }
        }
    }
    
    default boolean doesElementMatchTheCriterias(List<ICriteria<T>> criterias, T element) {
        for(ICriteria<T> criteria : criterias){
            if(!this.doesElementMatchTheCriteria(criteria, element)){
                return false;
            }
        }
        
        return true;
    }
    
    public boolean doesElementMatchTheCriteria(ICriteria<T> criteria, T element);
    
}
