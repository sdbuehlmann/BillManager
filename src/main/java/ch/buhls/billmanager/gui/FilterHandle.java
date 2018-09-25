
package ch.buhls.billmanager.gui;

import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public class FilterHandle<T>
{
    private final IFilter<T> filter;
    private final List<IFilter<T>> filters;

    public FilterHandle(IFilter<T> personFilter, List<IFilter<T>> filters) {
        this.filter = personFilter;
        this.filters = filters;
    }

    public void delete() {
        filters.remove(filter);
    }
    
}
