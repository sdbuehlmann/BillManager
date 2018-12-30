
package ch.buhls.billmanager.model.data.filter;

import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public class FilterHandle<T> implements IFilterHandle
{
    private final IFilter<T> filter;
    private final ICriteriaContainer<T> container;
    private final List<IFilter<T>> filters;

    /**
     * @deprecated 
     * @param personFilter
     * @param filters 
     */
    public FilterHandle(IFilter<T> personFilter, List<IFilter<T>> filters) {
        this.filter = personFilter;
        this.filters = filters;
        this.container = null;
    }

    public FilterHandle(IFilter<T> personFilter, ICriteriaContainer<T> container) {
        this.filter = personFilter;
        this.filters = null;
        this.container = container;
    }

    @Override
    public void delete() {
        if(this.filters != null){
            this.filters.remove(filter);
        }
        if(this.container != null){
            this.container.deleteCriteria(filter);
        }
    }
    
}
