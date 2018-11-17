
package ch.buhls.billmanager.model.data.filter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class ListFiltersContainer<T>
{
    private final List<IFilter<T>> filters;

    public ListFiltersContainer() {
        filters = new ArrayList<>();
    }
    
    public void filterList(List<T> list){
        for(IFilter filter : filters){
            filter.filterList(list);
        }
    }
    
    // getter
    public List<IFilter<T>> getFilters() {
        return filters;
    }
}
