
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
    private final List<IFilter<T>> filters;

    public FilterHandle(IFilter<T> personFilter, List<IFilter<T>> filters) {
        this.filter = personFilter;
        this.filters = filters;
    }

    @Override
    public void delete() {
        filters.remove(filter);
    }
    
}
